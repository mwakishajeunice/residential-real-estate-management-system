package com.jeunice.realestate.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tenant {

    @Id
    private Long phoneNumber;

    private String firstName;
    private String lastName;
    private String email;
    private long nationalId;


}
