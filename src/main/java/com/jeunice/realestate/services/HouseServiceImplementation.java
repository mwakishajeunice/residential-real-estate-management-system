package com.jeunice.realestate.services;

import com.jeunice.realestate.dao.HouseRepository;
import com.jeunice.realestate.models.Agent;
import com.jeunice.realestate.models.House;
import com.jeunice.realestate.models.HouseTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.jeunice.realestate.models.HouseTypes.BEDSITTER;

@Service
//Implements the interface HouseService
public class HouseServiceImplementation{

//    House house = new House(453,BEDSITTER,"Njokerio",3500.00,"Breeze","Booked","A6");
    @Autowired
    private HouseRepository houseRepository;

    //The method will basically return a list of all houses to the controller
    public List<House> getAllHouses() {
        return houseRepository.findAll();
    }

    //Save Method for houses
    public House saveHouse(House house){

        return houseRepository.save(house);
    }

    //Get method by house code
    public Optional<House> getHouseByCode(Integer houseCode){

        return houseRepository.findById(houseCode);
    }

    //Delete method using houseCode
    public void deleteHouse(Integer houseCode){
        houseRepository.findById(houseCode).ifPresent(houseRepository::delete);
    }

    public House findHousesById(Integer houseCode) {
        return houseRepository.findById(houseCode).orElseThrow();
    }
}
