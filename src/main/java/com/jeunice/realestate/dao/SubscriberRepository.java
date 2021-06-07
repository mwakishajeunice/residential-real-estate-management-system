package com.jeunice.realestate.dao;
import com.jeunice.realestate.models.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubscriberRepository  extends JpaRepository<Subscriber, Long>{
    @Query(value = "SELECT * FROM subcribers u WHERE DATEDIFF(NOW(),u.create_at) < 7", nativeQuery = true)
    List<Subscriber> findRecent();

    @Query(value = "UPDATE subscribers SET is_active = 0 WHERE email = ?", nativeQuery = true)
    ResponseEntity<Subscriber> unsubscribe(String email);

    @Query(value = "SELECT * FROM subscribers u WHERE u.email = ?", nativeQuery = true)
    Optional<Subscriber> findByEmail(String email);
}
