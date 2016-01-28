package com.smartbaedal.repository;

import com.smartbaedal.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by minkyoungsoo on 2016. 1. 26..
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
