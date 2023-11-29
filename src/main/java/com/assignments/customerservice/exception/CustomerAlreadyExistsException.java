package com.assignments.customerservice.exception;

public class CustomerAlreadyExistsException extends RuntimeException{

    public CustomerAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }

}
