package com.example.appointment.repositories;

import com.example.appointment.entities.Agency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgencyRepository extends JpaRepository<Agency, Long> {

    boolean existsByPhone(String phone);

    boolean existsByNameIgnoreCase(String name);

    boolean existsByEmailIgnoreCase(String email);
}
