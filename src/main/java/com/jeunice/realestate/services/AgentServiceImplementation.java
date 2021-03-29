package com.jeunice.realestate.services;

import com.jeunice.realestate.dao.AgentRepository;
import com.jeunice.realestate.models.Agent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
//Implements the interface AgentService
public class AgentServiceImplementation {

    @Autowired
    private AgentRepository agentRepository;

    //The method will basically return a list of all agents to the controller

    public List<Agent> getAllAgents() {

        return agentRepository.findAll();
    }

    //Save Method for Agents
    public Agent saveAgent(Agent agent) {
//        if (agentRepository.existsAgentByPhoneNumber(agent.getPhoneNumber()))
//            throw new DuplicateKeyException("The phone number is taken");
        return agentRepository.save(agent);
    }

    //Get Agent by Id
    public Optional<Agent> getAgentById(Long agentId) {
        return agentRepository.findById(agentId);
    }

    //Delete method using agentId
    public void deleteAgent(Long agentId) {
        agentRepository.findById(agentId).ifPresentOrElse(agentRepository::delete, () -> {
            throw new NoSuchElementException("not found");
        });
    }
}

////    update
//    public Optional<Agent> updateAgent(Long agentId, Agent newAgent) {
//        // we check if the object exists
//        Optional<Agent> oldAgent = agentRepository.findById(agentId);
//
//        if (oldAgent.isPresent()) {
//            agentRepository.save(newAgent);
//        }
//
//        return agentRepository.findById(agentId);
//    }


