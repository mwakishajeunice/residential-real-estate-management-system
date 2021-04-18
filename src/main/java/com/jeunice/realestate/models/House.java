package com.jeunice.realestate.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer houseCode;

    @Enumerated
    private HouseTypes houseType;

    @NotBlank(message = "Location is Mandatory")
    private String location;
    @NotNull(message = "Price is Mandatory")
    private double price;
    @NotBlank(message = "Name is Mandatory")
    private String name;
    @NotBlank(message = "Status is Mandatory")
    private String status;
    @NotBlank(message = "Room No. is Mandatory")
    private String roomNo;

    @ManyToOne
    @JoinColumn(name = "agentId")
    private Agent agent;


}