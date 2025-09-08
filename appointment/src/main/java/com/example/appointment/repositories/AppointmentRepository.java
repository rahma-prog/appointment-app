package com.example.appointment.repositories;

import com.example.appointment.entities.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment , Long> {

    boolean existsByUserIdAndAppointDateBetween(Long id, LocalDateTime startOfDay, LocalDateTime endOfDay);
}
