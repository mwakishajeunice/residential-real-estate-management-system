package com.jeunice.realestate.controllers;

import com.jeunice.realestate.models.Agent;
import com.jeunice.realestate.models.Contact;
import com.jeunice.realestate.services.AgentServiceImplementation;
import com.jeunice.realestate.services.ContactServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactServiceImplementation contactServiceImplementation;

    //Create a method handler for home-page(index.html) that will display a list of contacts
    @GetMapping("/all")
    public ResponseEntity<List<Contact>> getAllContacts() {

        return new ResponseEntity<>(contactServiceImplementation.getAllContacts(), HttpStatus.OK);
    }

    @GetMapping("/{email}")
    public ResponseEntity<Contact> getContactById(@PathVariable("email") String email){
        return new ResponseEntity<>(contactServiceImplementation.getContactById(email), HttpStatus.OK);
    }

    //Method Handler for addNewContact request
    @PostMapping("/addNewContact")
    public ResponseEntity<Contact> addNewContact(@RequestBody @Validated Contact contact) {

        return new ResponseEntity<>(contactServiceImplementation.saveContact(contact), HttpStatus.CREATED);
    }

    //Method Handler for addNewContact request
    //@PutMapping("/edit/{email}")
    @PutMapping("/edit/{email}")
    public ResponseEntity updateContact(@PathVariable(name = "email") String email,
                                      @RequestBody @Validated Contact newContact) {
        contactServiceImplementation.updateContact(email, newContact);
        return  new ResponseEntity(HttpStatus.CREATED);

    }

    //@DeleteMapping("/delete/{email}")
    @DeleteMapping("/delete/{email}")
    public ResponseEntity deleteContact(@PathVariable(name = "email") String email) {
     contactServiceImplementation.deleteContact(email);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
