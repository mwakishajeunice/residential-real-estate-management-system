package com.jeunice.realestate.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tenants {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(nullable = false, unique = true, length = 50)
    private String email;
    @NotEmpty
    @Column(nullable = false, length = 20)
    private String firstname;
    @NotEmpty
    @Column(nullable = false, length = 20)
    private String lastname;
    @NotEmpty
    @Column(length = 13)
    private String phonenumber;
    @NotEmpty
    @Column(nullable =  false, columnDefinition = "varchar(750)")
    private String password;
    //problem
    //for forgotten password functionality >>> with reset token column
    @Column(name = "created_on")
    private Date createdOn;
    @Column(name = "enabled")
    private boolean enabled;
    @Column(name = "last_login")
    private Date lastLogin;
    @Column(name = "reset_token")
    private String resetToken;
}
