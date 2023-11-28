package com.assignments.customerservice.dto.mapper;

import com.assignments.customerservice.domain.Customer;
import com.assignments.customerservice.domain.Location;
import com.assignments.customerservice.dto.NewCustomerDto;
import com.assignments.customerservice.dto.NewCustomerRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper( CustomerMapper.class );

     NewCustomerDto toNewCustomerDto(Customer customer, Location location);

     Customer toCustomer (NewCustomerRequest newCustomerRequest);

     Location toLocation (NewCustomerRequest newCustomerRequest);



}
