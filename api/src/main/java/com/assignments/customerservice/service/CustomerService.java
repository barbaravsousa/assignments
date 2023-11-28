package com.assignments.customerservice.service;


import com.assignments.customerservice.domain.Customer;
import com.assignments.customerservice.domain.Location;
import com.assignments.customerservice.domain.repository.CustomerRepository;
import com.assignments.customerservice.domain.repository.LocationRepository;
import com.assignments.customerservice.dto.NewCustomerDto;
import com.assignments.customerservice.dto.request.NewCustomerRequest;
import com.assignments.customerservice.dto.mapper.CustomerMapper;
import com.assignments.customerservice.exception.CustomerDoesNotExistException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class CustomerService {

    private CustomerRepository customerRepository;

    private LocationRepository locationRepository;

    private CustomerMapper customerMapper;


    @Transactional
    public NewCustomerDto addNewCustomer(NewCustomerRequest newCustomerRequest) throws CustomerDoesNotExistException {
        Customer customer = customerMapper.toCustomer(newCustomerRequest);
        Location location = customerMapper.toLocation(newCustomerRequest);

        Set<Customer> customers = new HashSet<>();
        customers.add(customer);

        customer.setLocation(location);
        location.setCustomers(customers);

        customerRepository.save(customer);
        locationRepository.save(location);


        return getCustomer(newCustomerRequest.getCustomerRef());

    }

    public NewCustomerDto getCustomer(String customerRef) throws CustomerDoesNotExistException {

        Customer savedCustomer = customerRepository.findByCustomerRef(customerRef);

        if(savedCustomer==null){
            throw new CustomerDoesNotExistException("Can not find customer with" + customerRef);
        }

        return customerMapper.toNewCustomerDto(savedCustomer);

    }


}
