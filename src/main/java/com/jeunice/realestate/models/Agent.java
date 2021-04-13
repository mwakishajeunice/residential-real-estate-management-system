package com.jeunice.realestate.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Agent {

    @Id
    private Long agentId;
    @NotNull(message = "Phone Number is Mandatory")
    private Long phoneNumber;
    @NotBlank(message = "First Name is Mandatory")
    private String firstName;
    @NotBlank(message = "Last Name is Mandatory")
    private String lastName;
    @Email
    private String email;
    @NotNull(message = "National ID is Mandatory")
    private Long nationalId;


}
