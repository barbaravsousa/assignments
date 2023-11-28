package com.assignments.customerservice.controller;

import com.assignments.customerservice.exception.CustomerDoesNotExistException;
import com.assignments.customerservice.service.CustomerService;
import com.assignments.customerservice.dto.NewCustomerDto;
import com.assignments.customerservice.dto.request.NewCustomerRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customers")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;


    @RequestMapping(value = "/new_customer")
    @PostMapping
    public ResponseEntity addCustomerToDatabase(@RequestBody NewCustomerRequest newCustomer) {
        NewCustomerDto newCustomerDto;

        try {
            newCustomerDto = customerService.addNewCustomer(newCustomer);
        } catch (CustomerDoesNotExistException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(newCustomerDto, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/findCustomer")
    @GetMapping
    public ResponseEntity getCustomer(@RequestParam String customerRef) {
        NewCustomerDto newCustomerDto;

        try {
            newCustomerDto = customerService.getCustomer(customerRef);
        } catch (CustomerDoesNotExistException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(newCustomerDto, HttpStatus.OK);
    }


}
