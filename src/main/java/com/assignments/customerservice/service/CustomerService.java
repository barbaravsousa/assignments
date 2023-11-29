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


    /**
     * Method that receives the information about a new customer, it maps the information to create the necessary entities
     * to be saved in the database.
     *
     * @param newCustomerRequest is the object that contains the information about the new customer.
     * @return the information about the saved customer.
     * @throws CustomerDoesNotExistException is thrown if there is a problem during the process of saving a customer in the database.
     */
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

    /**
     * Method that receives a customer reference and checks the database to see if the user exists.
     *
     * @param customerRef is a unique identifier.
     * @return the information about the customer with the given customer reference.
     * @throws CustomerDoesNotExistException is thrown if the customer reference does not exist in the database.
     */
    public NewCustomerDto getCustomer(String customerRef) throws CustomerDoesNotExistException {

        Customer savedCustomer = customerRepository.findByCustomerRef(customerRef);

        if (savedCustomer == null) {
            throw new CustomerDoesNotExistException("Can not find customer with" + customerRef);
        }

        return customerMapper.toNewCustomerDto(savedCustomer);

    }


}
