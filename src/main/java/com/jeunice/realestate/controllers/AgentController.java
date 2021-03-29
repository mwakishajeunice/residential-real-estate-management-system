package com.jeunice.realestate.controllers;
import com.jeunice.realestate.models.Agent;
import com.jeunice.realestate.services.AgentServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;


@Controller
//@RequestMapping("/agents")
//@RestController
public class AgentController {

    @Autowired
    private AgentServiceImplementation agentServiceImplementation;

    //Create a method handler for home-page(index.html) that will display a list of agents
    @GetMapping("/agents")
    public String viewHomePage(Model model) {
        //List<Agent> agentList = agentServiceImplementation.getAllAgents();
        model.addAttribute("agentList", agentServiceImplementation.getAllAgents());
        return "index";
    }

    //Method Handler for addNewAgent request
    @GetMapping("/addNewAgent")
    public String addNewAgent(Model model) {
        //Create model attribute to bind data accessed from thymeleaf template(this empty agent object)
        Agent agent = new Agent();
        model.addAttribute("agent", agent);
        return "new_agent";
    }

    //Updating method
    //@PostMapping("/saveAgent")
    @RequestMapping("/saveAgent")
    //Bind the model attributes data to the agent
    public String saveAgent(@ModelAttribute("agent") Agent agent) {
        // save agent to database
        agentServiceImplementation.saveAgent(agent);
        //redirect to home page
        return "redirect:/agents";

    }

    //Method Handler for addNewAgent request
//    @PutMapping("/edit/{agentId}")
    @RequestMapping("/edit/{agentId}")
    public ModelAndView updateAgent(@PathVariable(name = "agentId") Long agentId){

    ModelAndView modelAndView = new ModelAndView("edit_agent");

    Optional<Agent> agent = agentServiceImplementation.getAgentById(agentId);
    modelAndView.addObject("agent",agent);

    return modelAndView;
    }

    //@DeleteMapping("/delete/{agentId}")
    @RequestMapping("/delete/{agentId}")
    public String deleteAgent(@PathVariable(name = "agentId") Long agentId){
        agentServiceImplementation.deleteAgent(agentId);
        return "redirect:/agents";
    }



}


//    update an agent



