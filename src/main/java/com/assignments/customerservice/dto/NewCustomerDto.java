package com.assignments.customerservice.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NewCustomerDto {
    private String customerRef;
    private String customerName;
    private String addressLine1;
    private String addressLine2;
    private String town;
    private String county;
    private String country;
    private String postcode;

}
