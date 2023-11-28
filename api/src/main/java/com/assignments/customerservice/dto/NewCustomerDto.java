package com.assignments.customerservice.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NewCustomerDto {
    private String CustomerRef;
    private String CustomerName;
    private String AddressLine1;
    private String AddressLine2;
    private String Town;
    private String County;
    private String Country;
    private String Postcode;

}
