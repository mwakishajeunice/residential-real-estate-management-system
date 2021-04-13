package com.jeunice.realestate.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tenant {

    @Id
    private Long phoneNumber;
    @NotBlank(message = "First Name is Mandatory")
    private String firstName;
    @NotBlank(message = "Last Name is Mandatory")
    private String lastName;
    private String email;
    @NotBlank(message = "National ID is Mandatory")
    private long nationalId;


}
