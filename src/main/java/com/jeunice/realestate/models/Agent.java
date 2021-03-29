package com.jeunice.realestate.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Agent {

    @Id
    private Long agentId;

    private Long phoneNumber;
    private String firstName;
    private String lastName;
    private String email;
    private Long nationalId;


}
