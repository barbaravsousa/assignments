package com.assignments.customerservice.domain.repository;

import com.assignments.customerservice.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    /**
     * Query to find the customer given a certain customer reference.
     *
     * @param customerRef is a unique identifier for the customer.
     * @return customer entity.
     */
    Customer findByCustomerRef(String customerRef);
}
