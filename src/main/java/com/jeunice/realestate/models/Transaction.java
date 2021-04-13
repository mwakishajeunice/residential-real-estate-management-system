package com.jeunice.realestate.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    private String transactionCode;

    @NotBlank(message = "Amount is Mandatory")
    private double amount;
    @NotBlank(message = "Date is Mandatory")
    private LocalDate Date;
    @NotBlank(message = "Purpose is Mandatory")
    private  String purpose;
    @ManyToOne(targetEntity = Tenant.class,cascade = CascadeType.ALL)
    private Tenant tenant;
}
