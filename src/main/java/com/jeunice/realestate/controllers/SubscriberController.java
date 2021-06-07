package com.jeunice.realestate.controllers;

import com.jeunice.realestate.dao.SubscriberRepository;
import com.jeunice.realestate.models.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/subscribers")
public class SubscriberController {
    @Autowired
    private SubscriberRepository subscriberRepository;

    @GetMapping
    public List<Subscriber> findAllSubscribers(){
        return subscriberRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subscriber> findSubscriberById(@PathVariable(value = "id") long id) {
        Optional<Subscriber> subscriber = subscriberRepository.findById(id);
        if(subscriber.isPresent()){
            return ResponseEntity.ok().body(subscriber.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
