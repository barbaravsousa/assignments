package com.assignments.customerservice;

import com.assignments.customerservice.domain.Customer;
import com.assignments.customerservice.domain.Location;
import com.assignments.customerservice.dto.NewCustomerDto;
import com.assignments.customerservice.dto.NewCustomerRequest;
import com.assignments.customerservice.dto.mapper.CustomerMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CustomerServiceUnitTest {

    @InjectMocks
    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @Mock
    LocationRepository locationRepository;

    @Mock
    CustomerMapper customerMapper;

    @Test
    void addNewCustomer() {
        String customerRef = "12AB";
        String customerName = "TestCustomer";
        String addressLine1 = "Commander Ave";
        String addressLine2 = "Flat 1";
        String town = "London";
        String county = null;
        String country = "UK";
        String postcode = "NW9";


        Customer customer = new Customer();
        customer.setCustomerRef(customerRef);
        customer.setCustomerName(customerName);
        customer.setAddressLine1(addressLine1);
        customer.setAddressLine2(addressLine2);

        Location location = new Location();
        location.setTown(town);
        location.setCounty(county);
        location.setCountry(country);
        location.setPostcode(postcode);

        verify(customerRepository, times(1)).save(customer);
        verify(locationRepository, times(1)).save(location);


        NewCustomerRequest newCustomerRequest = new NewCustomerRequest(customerRef, customerName, addressLine1, addressLine2,
                town, county, country, postcode);

        NewCustomerDto expectedNewCustomerDto = new NewCustomerDto(customerRef, customerName, addressLine1, addressLine2, town,
                county, country, postcode);

        when(customerMapper.toNewCustomerDto(customer, location)).thenReturn(expectedNewCustomerDto);

        NewCustomerDto actualNewCustomerDto = customerService.addNewCustomer(newCustomerRequest);

        assertEquals(expectedNewCustomerDto, actualNewCustomerDto);

    }
}
