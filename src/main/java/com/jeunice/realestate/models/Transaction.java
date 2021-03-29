package com.jeunice.realestate.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    private String transactionCode;

    private double amount;
    private LocalDate Date;
    private  String purpose;

    @ManyToOne(targetEntity = Tenant.class,cascade = CascadeType.ALL)
    private Tenant tenant;
}
