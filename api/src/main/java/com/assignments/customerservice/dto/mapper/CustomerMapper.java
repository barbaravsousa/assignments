package com.assignments.customerservice.dto.mapper;

import com.assignments.customerservice.domain.Customer;
import com.assignments.customerservice.domain.Location;
import com.assignments.customerservice.dto.NewCustomerDto;
import com.assignments.customerservice.dto.request.NewCustomerRequest;


public class CustomerMapper {


    public NewCustomerDto toNewCustomerDto(Customer customer) {
        Location location = customer.getLocation();
        return new NewCustomerDto(customer.getCustomerRef(), customer.getCustomerName(), customer.getAddressLine1(),
                customer.getAddressLine2(), location.getTown(), location.getCounty(), location.getCountry(), location.getPostcode());

    }

    public Customer toCustomer(NewCustomerRequest newCustomerRequest) {
        Customer customer = new Customer();
        customer.setCustomerRef(newCustomerRequest.getCustomerRef());
        customer.setCustomerName(newCustomerRequest.getCustomerName());
        customer.setAddressLine1(newCustomerRequest.getAddressLine1());
        customer.setAddressLine2(newCustomerRequest.getAddressLine2());
        return customer;
    }

    public Location toLocation(NewCustomerRequest newCustomerRequest) {
        Location location = new Location();
        location.setTown(newCustomerRequest.getTown());
        location.setCounty(newCustomerRequest.getCounty());
        location.setCountry(newCustomerRequest.getCountry());
        location.setPostcode(newCustomerRequest.getPostcode());

        return location;

    }


}
