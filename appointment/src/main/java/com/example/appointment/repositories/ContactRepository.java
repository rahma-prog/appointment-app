package com.example.appointment.repositories;

import com.example.appointment.entities.Contact;
import com.example.appointment.entities.enums.ContactType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findByUserId(Long userId);
    boolean existsByValueAndType(String value, ContactType type);

}
