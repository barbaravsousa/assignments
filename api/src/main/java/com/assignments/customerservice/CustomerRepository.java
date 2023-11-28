package com.assignments.customerservice;

import com.assignments.customerservice.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository <Customer, Integer> {

    Customer findByCustomerRef(String customerRef);
}
