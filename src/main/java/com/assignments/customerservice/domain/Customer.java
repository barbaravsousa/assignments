package com.assignments.customerservice.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(unique = true)
    String customerRef;
    String customerName;
    String addressLine1;
    String addressLine2;
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

}
