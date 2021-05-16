package com.jeunice.realestate.services;

import com.jeunice.realestate.dao.ContactRepository;
import com.jeunice.realestate.models.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ContactServiceImplementation {

    @Autowired
    private ContactRepository contactRepository;
    //The method will basically return a list of all contacts to the controller

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    //Save Method for Contacts
    public Contact saveContact(Contact contact) {
        if(contactRepository.existsById(contact.getEmail()))
            throw new DuplicateKeyException("Contact with id " + contact.getEmail() + " exists!" );
        return contactRepository.save(contact);
    }

    //Get Contact by Id
    public Contact getContactById(String  email) {
        return contactRepository.findById(email).orElseThrow(() -> {
            throw new NoSuchElementException("Contact not found with id : " + email);
        });
    }

    //Delete method using email
    public void deleteContact(String email) {
        contactRepository.findById(email).ifPresentOrElse((contact) -> {
            contactRepository.delete(contact);
        }, () -> {
            throw new NoSuchElementException("not found");
        });
    }

    public void updateContact(String email, Contact newContact) {
        contactRepository.findById(email).ifPresentOrElse((oldContact) -> {
            oldContact.setFirstName(newContact.getFirstName());
            oldContact.setLastName(newContact.getLastName());
            oldContact.setMessage(newContact.getMessage());
            oldContact.setPhoneNumber(newContact.getPhoneNumber());
            oldContact.setIsRead(newContact.getIsRead());

            contactRepository.save(oldContact);
        }, () -> {
            throw new NoSuchElementException("The contact with id: " + email + " does not exist");
        });
    }

}
