package com.assignments.customerservice.dto.mapper;

import com.assignments.customerservice.domain.Customer;
import com.assignments.customerservice.domain.Location;
import com.assignments.customerservice.dto.NewCustomerDto;
import com.assignments.customerservice.dto.request.NewCustomerRequest;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {


    /**
     * Method that receives a Customer entity and maps the information to create an object of type NewCustomerDto.
     *
     * @param customer is the entity.
     * @return an object of type NewCustomerDto.
     */
    public NewCustomerDto toNewCustomerDto(Customer customer) {
        Location location = customer.getLocation();
        return new NewCustomerDto(customer.getCustomerRef(), customer.getCustomerName(), customer.getAddressLine1(),
                customer.getAddressLine2(), location.getTown(), location.getCounty(), location.getCountry(), location.getPostcode());

    }

    /**
     * Method that receives an object of type NewCustomerRequest and maps the information to create an entity.
     *
     * @param newCustomerRequest is the request body that is passed when there is a request to add a new customer.
     * @return an entity of type Customer.
     */
    public Customer toCustomer(NewCustomerRequest newCustomerRequest) {
        Customer customer = new Customer();
        customer.setCustomerRef(newCustomerRequest.getCustomerRef());
        customer.setCustomerName(newCustomerRequest.getCustomerName());
        customer.setAddressLine1(newCustomerRequest.getAddressLine1());
        customer.setAddressLine2(newCustomerRequest.getAddressLine2());
        return customer;
    }

    /**
     * Method that receives an object of type NewCustomerRequest and maps the information to create an entity.
     *
     * @param newCustomerRequest is the request body that is passed when there is a request to add a new customer.
     * @return an entity of type Location.
     */
    public Location toLocation(NewCustomerRequest newCustomerRequest) {
        Location location = new Location();
        location.setTown(newCustomerRequest.getTown());
        location.setCounty(newCustomerRequest.getCounty());
        location.setCountry(newCustomerRequest.getCountry());
        location.setPostcode(newCustomerRequest.getPostcode());

        return location;

    }


}
