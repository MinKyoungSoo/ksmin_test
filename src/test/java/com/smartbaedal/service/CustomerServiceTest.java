package com.smartbaedal.service;

import static org.junit.Assert.assertEquals;

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

    Customer newCustomer;

    @Before
    public void setUp() throws Exception {
        Customer customer = new Customer(null, "강", "현구");
        this.newCustomer = customerService.create(customer);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testCustomerCreate() {
        Customer created;
        created = customerService.create(new Customer(null, "민", "경수"));
        assertEquals(
                new Customer(this.newCustomer.getId() + 1, "민", "경수"),
                created);
    }

    @Test
    public void testCustomerRead() {
        Long id = this.newCustomer.getId();
        Customer readed = customerService.read(id);
        assertEquals(new Customer(id, "강", "현구"), readed);
    }

}