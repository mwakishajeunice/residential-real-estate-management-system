package com.jeunice.realestate.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity

public class PersistentLogins {
    @NotNull
    @Column(columnDefinition = "varchar(64)")
    private String username;

    @Id
    @Column(columnDefinition = "varchar(64)")
    private String series;

    @NotNull
    private String token;

    @NotNull
    private Timestamp last_used;
}
