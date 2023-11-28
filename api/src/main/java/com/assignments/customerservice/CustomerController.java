package com.assignments.customerservice;

import com.assignments.customerservice.dto.NewCustomerDto;
import com.assignments.customerservice.dto.NewCustomerRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;


    @RequestMapping(value="/new_customer")
    @PostMapping
    public ResponseEntity addCustomerToDatabase(@RequestBody NewCustomerRequest newCustomer){

        NewCustomerDto newCustomerDto = customerService.addNewCustomer(newCustomer);

        return new ResponseEntity<>(newCustomerDto,HttpStatus.CREATED);
    }




}
