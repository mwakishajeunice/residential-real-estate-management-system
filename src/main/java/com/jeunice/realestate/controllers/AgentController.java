package com.jeunice.realestate.controllers;

import com.jeunice.realestate.models.Agent;
import com.jeunice.realestate.services.AgentServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/agents")
public class AgentController {

    @Autowired
    private AgentServiceImplementation agentServiceImplementation;

    //Create a method handler for home-page(index.html) that will display a list of agents
    @GetMapping("/all")
    public ResponseEntity<List<Agent>> getAllAgents() {

        return new ResponseEntity<>(agentServiceImplementation.getAllAgents(), HttpStatus.OK);
    }

    @GetMapping("/{agentId}")
    public ResponseEntity<Agent> getAgentById(@PathVariable("agentId") Long agentId){
        return new ResponseEntity<>(agentServiceImplementation.getAgentById(agentId), HttpStatus.OK);
    }

    //Method Handler for addNewAgent request
    @PostMapping("/addNewAgent")
    public ResponseEntity<Agent> addNewAgent(@RequestBody @Validated Agent agent) {

        return new ResponseEntity<>(agentServiceImplementation.saveAgent(agent), HttpStatus.CREATED);
    }

    //Method Handler for addNewAgent request
    //@PutMapping("/edit/{agentId}")
    @PutMapping("/edit/{agentId}")
    public ResponseEntity updateAgent(@PathVariable(name = "agentId") Long agentId,
                            @RequestBody @Validated Agent newAgent) {
        agentServiceImplementation.updateAgent(agentId, newAgent);
        return  new ResponseEntity(HttpStatus.CREATED);

    }

    //@DeleteMapping("/delete/{agentId}")
    @DeleteMapping("/delete/{agentId}")
    public ResponseEntity deleteAgent(@PathVariable(name = "agentId") Long agentId) {
        agentServiceImplementation.deleteAgent(agentId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}



