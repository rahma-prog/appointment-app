package com.example.appointment.services;

import com.example.appointment.entities.Address;
import com.example.appointment.entities.Contact;
import com.example.appointment.entities.User;
import com.example.appointment.entities.enums.ContactType;
import com.example.appointment.repositories.ContactRepository;
import com.example.appointment.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactRepository contactRepository;

    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    private final PasswordEncoder passwordEncoder;

    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    public User createUser( @Valid User user) {
        String rawPassword = user.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encodedPassword);
        if (user.getContacts() != null) {
            for (Contact contact : user.getContacts()) {
                if (contact.getType() == ContactType.EMAIL) {
                    boolean exists = contactRepository.existsByValueAndType(contact.getValue(), ContactType.EMAIL);
                    if (exists) {
                        throw new IllegalArgumentException("Email already in use: " + contact.getValue());
                    }
                }
                contact.setUser(user);
            }
        }
        if (user.getAddresses() != null) {
            for (Address address : user.getAddresses()) {
                address.setUser(user);
            }
        }

        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}