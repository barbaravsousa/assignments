package com.assignments.customerservice.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class NewCustomerRequest {

    private String customerRef;
    private String customerName;
    private String addressLine1;
    private String addressLine2;
    private String town;
    private String county;
    private String country;
    private String postcode;
}
