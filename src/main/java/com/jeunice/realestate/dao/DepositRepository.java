package com.jeunice.realestate.dao;

import com.jeunice.realestate.models.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepositRepository extends JpaRepository<Deposit, Long> {
}
