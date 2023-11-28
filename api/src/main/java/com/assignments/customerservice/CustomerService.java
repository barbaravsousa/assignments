package com.assignments.customerservice;


import com.assignments.customerservice.dto.NewCustomerDto;
import com.assignments.customerservice.dto.NewCustomerRequest;
import com.assignments.customerservice.dto.mapper.CustomerMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {

    private CustomerRepository customerRepository;

    private LocationRepository locationRepository;


    public NewCustomerDto addNewCustomer(NewCustomerRequest newCustomerRequest){

        customerRepository.save(CustomerMapper.INSTANCE.toCustomer(newCustomerRequest));

        locationRepository.save(CustomerMapper.INSTANCE.toLocation(newCustomerRequest));

        customerRepository.findByCustomerRef(newCustomerRequest.getCustomerRef());
         // Guardo da mesma maneira agora que cada entidade tem um set? Para ir buscar a bd como faco?


    }


}
