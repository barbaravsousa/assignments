package com.assignments.customerservice.controller;

import com.assignments.customerservice.exception.CustomerAlreadyExistsException;
import com.assignments.customerservice.exception.CustomerDoesNotExistException;
import com.assignments.customerservice.service.CustomerService;
import com.assignments.customerservice.dto.NewCustomerDto;
import com.assignments.customerservice.dto.request.NewCustomerRequest;
import jakarta.validation.Valid;
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


    /**
     * Request to add a new customer.
     *
     * @param newCustomer is the request body which contains all the information about the costumer to be added.
     * @return the information of the added customer with a Created status if the process of saving the customer in the database
     * was successfully, or returns a Not Found status if a problem occurred during this process or returns a Bad request status if
     * the customer already exists in the database.
     */
    @RequestMapping(value = "/newCustomer")
    @PostMapping
    public ResponseEntity addCustomerToDatabase(@Valid @RequestBody NewCustomerRequest newCustomer) {
        NewCustomerDto newCustomerDto;

        try {
            newCustomerDto = customerService.addNewCustomer(newCustomer);
        } catch (CustomerDoesNotExistException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (CustomerAlreadyExistsException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(newCustomerDto, HttpStatus.CREATED);
    }

    /**
     * Request to get the information about a customer given a customer reference.
     *
     * @param customerRef is a unique identifier of a costumer.
     * @return the information about the customer with an Ok status if the costumer exists or returns a Not Found if
     * the customer does not exist in the database.
     */
    @RequestMapping(value = "/findCustomer")
    @GetMapping
    public ResponseEntity getCustomer(@RequestParam String customerRef) {
        NewCustomerDto newCustomerDto;

        try {
            newCustomerDto = customerService.getCustomer(customerRef);
        } catch (CustomerDoesNotExistException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(newCustomerDto, HttpStatus.OK);
    }


}
