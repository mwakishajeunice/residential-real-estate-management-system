package com.jeunice.realestate.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Booking {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private long id;
    private String first_name;
    private String last_name;
    private String email;
    private String phone;
    @ManyToOne( targetEntity = House.class)
    private House house;
    private int slots;
    @CreatedDate
    private Date booking_date;
    @OneToOne( targetEntity = Deposit.class)
    private Deposit deposit;
}
