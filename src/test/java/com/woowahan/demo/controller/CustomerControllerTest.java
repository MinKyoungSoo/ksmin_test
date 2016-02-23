package com.woowahan.demo.controller;

import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.woowahan.SpringBootDemoApplication;
import com.woowahan.demo.domain.Customer;
import com.woowahan.demo.service.CustomerService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

/**
 * Created by jhhan on 2016-02-12.
 * 업주관련 컨트롤러 클래스
 * TODO : 기본 컨트롤러 기능
 * TODO : 업주조회 API
 * TODO : 업주목록 조회 API
 * TODO : 업주생성 API
 * TODO : 업주삭제 API
 * TODO : 업주갱신 API
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringBootDemoApplication.class)
@WebAppConfiguration
public class CustomerControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private CustomerService customerService;

    private static final MediaType mediaType
            = new MediaType(
                    MediaType.APPLICATION_JSON.getType(),
                    MediaType.APPLICATION_JSON.getSubtype(),
                    Charset.forName("utf8")
            );

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @After
    public void tearDown() {

    }

    @Test
    public void test_목업_기본동작_체크() throws Exception {
        mockMvc.perform(get("/customers")).andExpect(status().isOk());
    }

    /**
     * TODO : 고객조회를 위한 테스트 고객등록
     * TODO : 등록된 고객 id로 조회 목업작성
     * TODO : 리턴된 json과 값을 비교
     */
    @Test
    public void test_고객_한명을_ID로_조회한다()
            throws Exception {
        Customer customer = new Customer(null, "한", "재현");
        Customer createdCustomer = this.customerService.create(customer);

        mockMvc.perform(get("/customers/" + createdCustomer.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(mediaType))
                .andExpect(content().string(containsString("재현")))
                .andExpect(jsonPath("$.firstName").value(createdCustomer.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(createdCustomer.getLastName()))
                .andExpect(jsonPath("$.*", hasSize(3)))
                .andExpect(jsonPath("$.firstName", is(createdCustomer.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(createdCustomer.getLastName())))
                .andExpect(jsonPath("$.firstName", is(any(String.class))))
                .andDo(MockMvcResultHandlers.print());

    }

    /**
     * TODO : 고객등록
     * TODO : 등록된 고객의 정보를 체크
     */
    @Test
    public void test_고객_등록() throws Exception {
        Customer customer = new Customer(null, "최", "인화");

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonCustomer = objectMapper.writeValueAsString(customer);

        System.out.println(jsonCustomer);

        mockMvc.perform(post("/customers")
                    .content(jsonCustomer)
                    .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.firstName", is(customer.getFirstName())));
    }

    /**
     * TODO : 고객 정보 수정(고객 등록 포함)
     */
    @Test
    public void test_고객_수정() throws Exception {
        Customer customer = new Customer(null, "손", "현태");
        Customer createdCustomer = customerService.create(customer);
        Customer updatedCustomer = new Customer(null, "민", "경수");

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonCustomer = objectMapper.writeValueAsString(updatedCustomer);

        mockMvc.perform(put("/customers/" + createdCustomer.getId())
                    .content(jsonCustomer)
                    .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.firstName", is(updatedCustomer.getFirstName())));
    }

    /**
     * TODO : 고객정보 삭제
     */
    @Test
    public void test_고객_삭제() throws Exception {

        //given
        Customer customer = new Customer(null, "김", "민창");
        Customer createCustomer = customerService.create(customer);

        assertEquals("김", customer.getFirstName());

        Long createId = createCustomer.getId();

        //when
        ResultActions resultActions = mockMvc.perform(delete("/customers/" + createId));

        //then
        resultActions.andExpect(status().isNoContent())
                .andDo(MockMvcResultHandlers.print());
    }

}