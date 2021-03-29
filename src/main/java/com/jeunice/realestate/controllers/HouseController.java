package com.jeunice.realestate.controllers;


import com.jeunice.realestate.models.Agent;
import com.jeunice.realestate.models.House;
import com.jeunice.realestate.services.HouseServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
//@RequestMapping("/houses")
//@RestController
public class HouseController {

    @Autowired
    private HouseServiceImplementation houseServiceImplementation;

    //Create a method handler for home-page(index.html) that will display a list of houses
    @GetMapping("/houses")
    public String viewHomePage(Model model){
        model.addAttribute("listHouses" , houseServiceImplementation.getAllHouses());
        return "house_index";
    }

    @GetMapping("/{agentId}/houses")
    public  String agentHouses(@PathVariable("agentId") Long agentId, Model model){
        model.addAttribute("listHouses", houseServiceImplementation.findHousesByAgentId(agentId));
        return "house_index";
    }

    //Method handler for request addition of house files
    @GetMapping("/addHouses")
    public String addHouse(Model model){
        House house = new House();
        model.addAttribute("house", house);
        return "new_house";
    }
    //Updating method
    //@PostMapping("/saveAddedHouse")
    @RequestMapping("/saveAddedHouse")
    //Bind the model attributes data to the house
    public String saveHouse(@ModelAttribute("house") House house) {
        // save agent to database
        houseServiceImplementation.saveHouse(house);
        //redirect to home page
        return "redirect:/houses";

    }
    //Method Handler for saveAddedHouse request
    //@PutMapping("/edit/{houseCode}")
    @RequestMapping("/editHouses/{houseCode}")
    public ModelAndView editHouseForm(@PathVariable(name = "houseCode") Integer houseCode){

        ModelAndView modelAndView = new ModelAndView("edit_house");

        Optional<House> house = houseServiceImplementation.getHouseByCode(houseCode);
        modelAndView.addObject("house",house);

        return modelAndView;
    }

    //@DeleteMapping("/delete/{houseCode}")
    @RequestMapping("/deleteHouses/{houseCode}")
    public String deleteAgent(@PathVariable(name = "houseCode") Integer houseCode){
        houseServiceImplementation.deleteHouse(houseCode);
        return "redirect:/houses";
    }

}
