package com.example.appointment.repositories;

import com.example.appointment.entities.Agency;
import com.example.appointment.entities.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {

    boolean existsByEmailIgnoreCaseAndAgency(String email, Agency agency);
}
