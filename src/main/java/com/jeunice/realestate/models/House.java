package com.jeunice.realestate.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer houseCode;

    @Enumerated
    private HouseTypes houseType;

    private String location;
    private double price;
    private String name;
    private String status;
    private String roomNo;

    @ManyToOne(targetEntity = Agent.class, cascade = CascadeType.ALL)
    private Agent agent;

}