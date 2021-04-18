package com.jeunice.realestate.services;

import com.jeunice.realestate.dao.AgentRepository;
import com.jeunice.realestate.models.Agent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AgentServiceImplementation {

    @Autowired
    private AgentRepository agentRepository;

    //The method will basically return a list of all agents to the controller

    public List<Agent> getAllAgents() {
        return agentRepository.findAll();
    }

    //Save Method for Agents
    public Agent saveAgent(Agent agent) {
        if(agentRepository.existsById(agent.getAgentId()))
            throw new DuplicateKeyException("Agent with id " + agent.getAgentId() + " exists!" );
        return agentRepository.save(agent);
    }

    //Get Agent by Id
    public Agent getAgentById(Long agentId) {
        return agentRepository.findById(agentId).orElseThrow(() -> {
            throw new NoSuchElementException("Agent not found with id : " + agentId);
        });
    }

    //Delete method using agentId
    public void deleteAgent(Long agentId) {
        agentRepository.findById(agentId).ifPresentOrElse((agent) -> {
            agentRepository.delete(agent);
        }, () -> {
            throw new NoSuchElementException("not found");
        });
    }

    public void updateAgent(Long agentId, Agent newAgent) {
        agentRepository.findById(agentId).ifPresentOrElse((oldAgent) -> {
            oldAgent.setEmail(newAgent.getEmail());
            oldAgent.setFirstName(newAgent.getFirstName());
            oldAgent.setLastName(newAgent.getLastName());
            oldAgent.setNationalId(newAgent.getNationalId());
            oldAgent.setPhoneNumber(newAgent.getPhoneNumber());
            agentRepository.save(oldAgent);
        }, () -> {
            throw new NoSuchElementException("The agent with id: " + agentId + " does not exist");
        });
    }
}