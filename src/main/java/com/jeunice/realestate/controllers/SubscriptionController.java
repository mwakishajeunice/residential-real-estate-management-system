package com.jeunice.realestate.controllers;

import com.jeunice.realestate.dao.SubscriptionRepository;
import com.jeunice.realestate.models.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @GetMapping
    public List<Subscription> findAllSubscriptions(){
        return subscriptionRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subscription> findSubscriptionById(@PathVariable(value = "id") long id) {
        verifySubscription(id);
        Subscription subscription = subscriptionRepository.getOne(id);
        return new ResponseEntity<>(subscription, HttpStatus.OK);
    }

    @GetMapping("/recent")
    public List<Subscription> findRecent() {
        return subscriptionRepository.findRecent();
    }

    @PostMapping
    public Subscription saveSubscription(@Validated @RequestBody Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    @PutMapping
    public Subscription updateSubscription(@Validated @RequestBody Subscription subscription) {
        verifySubscription(subscription.getId());
        return subscriptionRepository.save(subscription);
    }

    @DeleteMapping("/{id}/delete")
    public void deleteSubscription(@PathVariable(value = "id") long id){
        verifySubscription(id);
        subscriptionRepository.deleteById(id);
    }

    @DeleteMapping()
    public void deleteAllSubscriptions(){
        subscriptionRepository.deleteAll();
    }

    //checks if house exists
    private void verifySubscription(long subscription_id) throws ResourceNotFoundException {
        Optional<Subscription> subscription = subscriptionRepository.findById(subscription_id);
        if(subscription.isEmpty()){
            throw  new ResourceNotFoundException("Subscription with id " + subscription_id + " not found");
        }
    }
}
