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
    @Column (name = "")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String CustomerRef;
    String CustomerName;
    String AddressLine1;
    String AddressLine2;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Customer_Location",
            joinColumns = { @JoinColumn(name = "customer_id") },
            inverseJoinColumns = { @JoinColumn(name = "location_id") }
    )
    Set<Location> locations;

}
