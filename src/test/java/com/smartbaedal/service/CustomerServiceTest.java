package com.smartbaedal.service;

import static org.junit.Assert.*;

import com.smartbaedal.DemosuperApplication;
import com.smartbaedal.domain.Customer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by minkyoungsoo on 2016. 1. 26..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemosuperApplication.class)
public class CustomerServiceTest {

    @Autowired
    CustomerService customerService;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testCustomerCreate(){
        Customer customer = new Customer(null, "강", "현구");
        Customer newCustomer = customerService.create(customer);
        assertEquals(new Customer(1L, "강", "현구"), newCustomer);
    }
}