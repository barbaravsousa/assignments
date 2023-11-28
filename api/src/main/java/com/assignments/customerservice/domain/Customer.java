package com.assignments.customerservice.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table ( name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String CustomerRef;
    String CustomerName;
    String AddressLine1;
    String AddressLine2;
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

}
