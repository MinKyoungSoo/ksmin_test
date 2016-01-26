package com.smartbaedal.domain;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Created by minkyoungsoo on 2016. 1. 26..
 */
public class CustomerTest {

    @Test
    public void testFirst(){
        Customer customer = new Customer(null, "민", "경수");
        assertEquals(customer, new Customer(null, "민", "경수"));
    }
}