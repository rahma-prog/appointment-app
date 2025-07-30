package com.example.appointment.services;

import com.example.appointment.entities.Contact;
import com.example.appointment.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    public List<Contact> getContactsByUserId(Long userId) {
        return contactRepository.findByUserId(userId);
    }

    public Contact createContact(Contact contact) {
        return contactRepository.save(contact);
    }

    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }
}
