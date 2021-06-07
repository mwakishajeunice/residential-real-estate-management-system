package com.jeunice.realestate.dao;
import com.jeunice.realestate.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRequestRepository extends JpaRepository<Transaction, Long> {
    @Query(value = "SELECT * FROM transactions WHERE checkout_requestid = ? AND accepted = 0", nativeQuery = true)
    Optional<Transaction> getTransactionsByCheckoutRequestID( String checkout_id);
}
