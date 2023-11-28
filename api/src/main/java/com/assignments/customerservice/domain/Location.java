package com.assignments.customerservice.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table( name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String Town;
    String County;
    String Country;
    String Postcode;

    @ManyToMany(mappedBy = "locations")
    Set<Customer> customers;


}
