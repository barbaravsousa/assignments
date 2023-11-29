package com.assignments.customerservice.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "location",
        indexes = {@Index(name = "idx_postcode", columnList = "postcode", unique = true)})
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String town;
    String county;
    String country;
    String postcode;

}
