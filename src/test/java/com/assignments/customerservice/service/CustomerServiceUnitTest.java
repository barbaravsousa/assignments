package com.assignments.customerservice.service;

import com.assignments.customerservice.domain.Customer;
import com.assignments.customerservice.domain.Location;
import com.assignments.customerservice.domain.repository.CustomerRepository;
import com.assignments.customerservice.domain.repository.LocationRepository;
import com.assignments.customerservice.dto.NewCustomerDto;
import com.assignments.customerservice.dto.request.NewCustomerRequest;
import com.assignments.customerservice.dto.mapper.CustomerMapper;
import com.assignments.customerservice.exception.CustomerDoesNotExistException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceUnitTest {

    private final String customerRef = "12AB";
    private final String customerName = "TestCustomer";
    private final String addressLine1 = "Commander Ave";
    private final String addressLine2 = "Flat 1";
    private final String town = "London";
    private final String county = null;
    private final String country = "UK";
    private final String postcode = "NW9";

    private final NewCustomerRequest newCustomerRequest = new NewCustomerRequest(customerRef, customerName, addressLine1, addressLine2,
            town, county, country, postcode);

    private final NewCustomerDto expectedNewCustomerDto = new NewCustomerDto(customerRef, customerName, addressLine1, addressLine2, town,
            county, country, postcode);

    @InjectMocks
    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @Mock
    LocationRepository locationRepository;

    @Mock
    CustomerMapper customerMapper;

    @Test
    void addNewCustomer() throws CustomerDoesNotExistException {

        Customer customer = new Customer();
        customer.setCustomerRef(customerRef);
        customer.setCustomerName(customerName);
        customer.setAddressLine1(addressLine1);
        customer.setAddressLine2(addressLine2);

        Set<Customer> customers = new HashSet<>();
        customers.add(customer);

        Location location = new Location();
        location.setTown(town);
        location.setCounty(county);
        location.setCountry(country);
        location.setPostcode(postcode);

        when(customerMapper.toCustomer(newCustomerRequest)).thenReturn(customer);
        when(customerMapper.toLocation(newCustomerRequest)).thenReturn(location);

        customer.setLocation(location);
        location.setCustomers(customers);

        when(customerRepository.findByCustomerRef(customerRef)).thenReturn(customer);

        when(customerService.getCustomer(customerRef)).thenReturn(expectedNewCustomerDto);

        NewCustomerDto actualNewCustomerDto = customerService.addNewCustomer(newCustomerRequest);

        assertEquals(expectedNewCustomerDto, actualNewCustomerDto);
        verify(customerRepository, times(1)).save(customer);
        verify(locationRepository, times(1)).save(location);

    }

    @Test
    void getCustomer() throws CustomerDoesNotExistException {
        Customer customer = new Customer();
        customer.setCustomerRef(customerRef);
        customer.setCustomerName(customerName);
        customer.setAddressLine1(addressLine1);
        customer.setAddressLine2(addressLine2);

        Set<Customer> customers = new HashSet<>();
        customers.add(customer);

        Location location = new Location();
        location.setTown(town);
        location.setCounty(county);
        location.setCountry(country);
        location.setPostcode(postcode);

        customer.setLocation(location);
        location.setCustomers(customers);

        when(customerRepository.findByCustomerRef(customerRef)).thenReturn(customer);

        when(customerMapper.toNewCustomerDto(customer)).thenReturn(expectedNewCustomerDto);

        NewCustomerDto actualNewCustomerDto = customerService.getCustomer(customerRef);

        assertEquals(expectedNewCustomerDto, actualNewCustomerDto);

    }

    @Test
    void getCostumer_CustomerNotFound() {

        when(customerRepository.findByCustomerRef(customerRef)).thenReturn(null);

        Exception exception = assertThrows(CustomerDoesNotExistException.class, () -> customerService.getCustomer(customerRef));

        String expectedMessage = "Can not find customer with" + customerRef;

        String exceptionMessage = exception.getMessage();

        assertEquals(expectedMessage, exceptionMessage);
    }


}
