package com.jeunice.realestate.dao;

import com.jeunice.realestate.models.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LoginRepository extends JpaRepository<Login, Long> {
    @Query(value = "SELECT * FROM login u WHERE u.is_active = 1 AND u.expiry_date > NOW()", nativeQuery = true)
    List<Login> findAllActive();

    @Query(value = "INSERT INTO logins(email, token, expiry_date) VALUES(?,?,?)", nativeQuery = true)
    String login(String email, String password);

    @Query(value = "UPDATE logins SET is_active = 0, expiry_date = NOW() WHERE token = ?", nativeQuery = true)
    Boolean logout(String token);

    @Query(value = "UPDATE agents SET password = ? WHERE email = ?", nativeQuery = true)
    Boolean changePassword(String password, String email);

    @Query(value = "SELECT email FROM subscribers WHERE email = ?", nativeQuery = true)
    Boolean checkSubscriberEmail(String email);

    @Query(value = "SELECT email FROM agents WHERE email =  ?", nativeQuery = true)
    Boolean checkAgentEmail(String email);
}
