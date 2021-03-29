package com.jeunice.realestate.controllers;

import com.jeunice.realestate.models.Agent;
import com.jeunice.realestate.models.Tenant;
import com.jeunice.realestate.services.TenantServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
//@RequestMapping("/tenants")
//@RestController
public class TenantController {

    @Autowired
    private TenantServiceImplementation tenantServiceImplementation;

    @GetMapping("/tenants")
    public String viewHomePage(Model model){
        model.addAttribute("listTenants", tenantServiceImplementation.getAllTenants());
        return "tenant_index";
    }
    //Method Handler for addNewTenant request
    @GetMapping("/addNewTenant")
    public String addNewTenant(Model model) {
        //Create model attribute to bind data accessed from thymeleaf template(this empty tenant object)
        Tenant tenant = new Tenant();
        model.addAttribute("tenant", tenant);
        return "new_tenant";
    }

    //Updating method
    //@PostMapping("/saveNewTenant")
    @RequestMapping("/saveNewTenant")
    //Bind the model attributes data to the tenant
    public String saveNewTenant(@ModelAttribute("tenant") Tenant tenant) {
        // save tenant to database
        tenantServiceImplementation.saveTenant(tenant);
        //redirect to home page
        return "redirect:/tenants";

    }

    //Method Handler for newTenant request
    //@PutMapping("/edit/{phoneNumber}")
    @RequestMapping("/editTenant/{phoneNumber}")
    public ModelAndView updateTenant(@PathVariable(name = "phoneNumber") Long phoneNumber){

        ModelAndView modelAndView = new ModelAndView("edit_tenant");

        Optional<Tenant> tenant = tenantServiceImplementation.getTenantByPhoneNumber(phoneNumber);
        modelAndView.addObject("tenant",tenant);

        return modelAndView;
    }

    //@DeleteMapping("/delete/{phoneNumber}")
    @RequestMapping("/deleteTenant/{phoneNumber}")
    public String deleteTenant(@PathVariable(name = "phoneNumber") Long phoneNumber){
        tenantServiceImplementation.deleteTenant(phoneNumber);
        return "redirect:/tenants";
    }
}
