package com.assignments.customerservice.controller;

import com.assignments.customerservice.domain.Customer;
import com.assignments.customerservice.domain.Location;
import com.assignments.customerservice.domain.repository.CustomerRepository;
import com.assignments.customerservice.domain.repository.LocationRepository;
import com.assignments.customerservice.dto.NewCustomerDto;
import com.assignments.customerservice.dto.mapper.CustomerMapper;
import com.assignments.customerservice.dto.request.NewCustomerRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class CustomerControllerIntegrationTest {

    private final String customerRef = "12AB";
    private final String customerName = "TestCustomer";
    private final String addressLine1 = "Commander Ave";
    private final String addressLine2 = "Flat 1";
    private final String town = "London";
    private final String county = null;
    private final String country = "UK";
    private final String postcode = "NW9";
    private final NewCustomerRequest newCustomer = new NewCustomerRequest(customerRef, customerName, addressLine1, addressLine2, town,
            county, country, postcode);
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private CustomerMapper customerMapper;

    @Test
    void addCustomer() throws Exception {
        MvcResult result = mockMvc.perform(post("/customers/newCustomer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newCustomer)))
                .andExpect(status().isCreated())
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();

        NewCustomerDto newCustomerDto = objectMapper.readValue(jsonResponse, NewCustomerDto.class);

        assertEquals(newCustomer.getCustomerRef(), newCustomerDto.getCustomerRef());

    }


    @Test
    @Transactional
    void getCustomer() throws Exception {
        Customer customer = customerMapper.toCustomer(newCustomer);
        Location location = customerMapper.toLocation(newCustomer);

        customer.setLocation(location);

        customerRepository.save(customer);
        locationRepository.save(location);


        MvcResult result = mockMvc.perform(get("/customers/findCustomer")
                        .param("customerRef", customerRef)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();

        NewCustomerDto newCustomerDto = objectMapper.readValue(jsonResponse, NewCustomerDto.class);

        assertEquals(newCustomer.getCustomerRef(), newCustomerDto.getCustomerRef());
        assertEquals(newCustomer.getCustomerName(), newCustomerDto.getCustomerName());
        assertEquals(newCustomer.getAddressLine1(), newCustomerDto.getAddressLine1());
        assertEquals(newCustomer.getAddressLine2(), newCustomerDto.getAddressLine2());
        assertEquals(newCustomer.getTown(), newCustomerDto.getTown());
        assertEquals(newCustomer.getCounty(), newCustomerDto.getCounty());
        assertEquals(newCustomer.getCountry(), newCustomerDto.getCountry());
        assertEquals(newCustomer.getPostcode(), newCustomerDto.getPostcode());

    }

    @Test
    void getCustomer_NotFound() throws Exception {
        mockMvc.perform(get("/customers/findCustomer")
                        .param("customerRef", "134")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
    }


}
