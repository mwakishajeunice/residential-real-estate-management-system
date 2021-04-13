package com.jeunice.realestate.dao;

import com.jeunice.realestate.models.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {
//    boolean existsAgentByPhoneNumber(Long phoneNumber);

}
