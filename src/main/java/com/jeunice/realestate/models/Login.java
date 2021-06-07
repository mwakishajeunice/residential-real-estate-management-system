package com.jeunice.realestate.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Entity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Login {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String token;
    private boolean is_active;
    private Date expiry_date;
    @CreationTimestamp
    private LocalDateTime login_at;
    @UpdateTimestamp
    private LocalDateTime logout_at;

    public Boolean comparePassword(String encryptedPassword, String plainPassword){
        boolean equal = BCrypt.checkpw(this.encryptPassword(plainPassword), encryptedPassword);
        return equal;
    }

    public String encryptPassword(String plainPassword){
        String password = BCrypt.hashpw(plainPassword, "nQ1zoi78n93");
        return password;
    }
}
