package com.assignments.customerservice.service;


import com.assignments.customerservice.domain.Customer;
import com.assignments.customerservice.domain.Location;
import com.assignments.customerservice.domain.repository.CustomerRepository;
import com.assignments.customerservice.domain.repository.LocationRepository;
import com.assignments.customerservice.dto.NewCustomerDto;
import com.assignments.customerservice.dto.request.NewCustomerRequest;
import com.assignments.customerservice.dto.mapper.CustomerMapper;
import com.assignments.customerservice.exception.CustomerAlreadyExistsException;
import com.assignments.customerservice.exception.CustomerDoesNotExistException;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CustomerService {

    private CustomerRepository customerRepository;

    private LocationRepository locationRepository;

    private CustomerMapper customerMapper;


    /**
     * Method that receives the information about a new customer, it maps the information to create the necessary entities
     * to be saved in the database.
     *
     * @param newCustomerRequest is the object that contains the information about the new customer.
     * @return the information about the saved customer.
     * @throws CustomerDoesNotExistException is thrown if there is a problem during the process of saving a customer in the database.
     */
    @Transactional(noRollbackFor = {DataIntegrityViolationException.class})
    public NewCustomerDto addNewCustomer(NewCustomerRequest newCustomerRequest) throws CustomerDoesNotExistException, CustomerAlreadyExistsException {
        Customer customer = customerMapper.toCustomer(newCustomerRequest);
        Location location = customerMapper.toLocation(newCustomerRequest);

        Location savedLocation = getOrCreateLocation(location);

        customer.setLocation(savedLocation);

        try {
            customerRepository.save(customer);
        } catch (DataIntegrityViolationException e) {
            throw new CustomerAlreadyExistsException("Customer with " + customer.getCustomerRef() + " already exists in the database");
        }

        return getCustomer(newCustomerRequest.getCustomerRef());

    }

    /**
     * Method that will check if a given postcode already exists in the database, if it not exists it will create a new one and will save it.
     * If the location with the given postcode already exists, it will return it.
     *
     * @param location is an entity with the customers location information.
     * @return a location entity.
     */
    private Location getOrCreateLocation(Location location) {
        return locationRepository.findByPostcode(location.getPostcode())
                .orElseGet(() -> {
                    locationRepository.save(location);
                    return location;
                });
    }

    /**
     * Method that receives a customer reference and checks the database to see if the user exists.
     *
     * @param customerRef is a unique identifier.
     * @return the information about the customer with the given customer reference.
     * @throws CustomerDoesNotExistException is thrown if the customer reference does not exist in the database.
     */
    public NewCustomerDto getCustomer(String customerRef) throws CustomerDoesNotExistException {

        Customer savedCustomer = customerRepository.findByCustomerRef(customerRef);

        if (savedCustomer == null) {
            throw new CustomerDoesNotExistException("Can not find customer with" + customerRef);
        }

        return customerMapper.toNewCustomerDto(savedCustomer);

    }


}
