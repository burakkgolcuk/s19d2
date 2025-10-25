package com.workintech.s18d4.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "account")
@Data
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="account_name")
    private String accountName;

    @Column(name="money_amount")
    private Double moneyAmount;

    @ManyToOne
    @JoinColumn(name="customer_id")
    @JsonIgnore
    private Customer customer;
}
