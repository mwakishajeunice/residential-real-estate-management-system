package com.jeunice.realestate.controllers;

import com.jeunice.realestate.models.House;
import com.jeunice.realestate.services.HouseServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/houses")
public class HouseController {

    @Autowired
    private HouseServiceImplementation houseServiceImplementation;

    //Create a method handler for home-page(index.html) that will display a list of houses
    @GetMapping("/all")
    public ResponseEntity<List<House>> getAllHouses(){

        return new ResponseEntity<>(houseServiceImplementation.getAllHouses(), HttpStatus.OK);
    }

    @GetMapping("/houses/{houseCode}")
    public ResponseEntity<House> getHouses(@PathVariable("houseCode") Integer houseCode){

        return new ResponseEntity<>(houseServiceImplementation.findHousesById(houseCode), HttpStatus.OK);
    }

    //Method handler for request addition of house files
    @PostMapping("/addNewHouse")
    public ResponseEntity<House> addHouse(@RequestBody House house){

        return new ResponseEntity<>(houseServiceImplementation.saveHouse(house),HttpStatus.CREATED);
    }

    //Method Handler for saveAddedHouse request
    @PutMapping("/editHouse/{houseCode}")
    public ResponseEntity editHouseForm(@PathVariable(name = "houseCode") Integer houseCode){
        houseServiceImplementation.getHouseByCode(houseCode);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteHouse/{houseCode}")
    public ResponseEntity deleteHouse(@PathVariable(name = "houseCode") Integer houseCode){
        houseServiceImplementation.deleteHouse(houseCode);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
