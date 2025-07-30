package com.example.appointment.controllers;

import com.example.appointment.entities.Contact;
import com.example.appointment.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping
    public List<Contact> getAllContacts() {
        return contactService.getAllContacts();
    }

    @GetMapping("/user/{userId}")
    public List<Contact> getContactsByUser(@PathVariable Long userId) {
        return contactService.getContactsByUserId(userId);
    }

    @PostMapping
    public Contact createContact(@RequestBody Contact contact) {
        return contactService.createContact(contact);
    }

    @DeleteMapping("/{id}")
    public void deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
    }
}
