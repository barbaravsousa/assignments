package com.assignments.customerservice.exception;

public class CustomerDoesNotExistException extends Exception{

    public CustomerDoesNotExistException(String errorMessage) {
        super(errorMessage);
    }
}
