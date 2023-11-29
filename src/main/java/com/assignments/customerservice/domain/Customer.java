package com.assignments.customerservice.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "customer",
        indexes = {@Index(name = "idx_customerRef", columnList = "customerRef", unique = true)})
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String customerRef;
    String customerName;
    String addressLine1;
    String addressLine2;
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

}
