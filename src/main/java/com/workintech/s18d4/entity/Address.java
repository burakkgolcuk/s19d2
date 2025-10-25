package com.workintech.s18d4.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "address")
@Data
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String street;

    private Integer no;

    private String city;

    private String country;

    @Column(nullable = true)
    private String description;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
