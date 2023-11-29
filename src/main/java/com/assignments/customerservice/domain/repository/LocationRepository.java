package com.assignments.customerservice.domain.repository;


import com.assignments.customerservice.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {

    /**
     * Query to find a location given a certain postcode.
     *
     * @param postcode is a unique identifier for the location.
     * @return the location for the given postcode.
     */
    Optional<Location> findByPostcode(String postcode);
}
