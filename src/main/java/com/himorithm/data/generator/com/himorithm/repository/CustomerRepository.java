package com.himorithm.data.generator.com.himorithm.repository;

import com.himorithm.data.generator.com.himorithm.data.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
