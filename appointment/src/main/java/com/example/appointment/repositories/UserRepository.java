package com.example.appointment.repositories;

import com.example.appointment.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u JOIN u.contacts c WHERE c.type = com.example.appointment.entities.enums.ContactType.EMAIL AND c.value = :email")
    Optional<User> findByEmail(@Param("email") String email);

}
