package com.jeunice.realestate.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contact {

    @Id
    @Email
    private String email;
    @NotBlank(message = "First Name is Mandatory")
    private String firstName;
    @NotBlank(message = "Last Name is Mandatory")
    private String lastName;
    @NotNull(message = "Phone Number is Mandatory")
    private Long phoneNumber;
    @NotBlank(message = "   Message is Mandatory")
    @Size(min = 50, max = 200)
    private String Message;
    @NotNull
    private Boolean isRead;


}
