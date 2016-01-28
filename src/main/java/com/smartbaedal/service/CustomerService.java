package com.smartbaedal.service;

import com.smartbaedal.domain.Customer;
import com.smartbaedal.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by minkyoungsoo on 2016. 1. 26..
 */
@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }
}
