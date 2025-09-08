package com.example.appointment.repositories;

import com.example.appointment.entities.AgencySchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.Optional;

@Repository
public interface AgencyScheduleRepository extends JpaRepository<AgencySchedule, Long> {

    boolean existsByAgencyIdAndDayOfWeek(Long agencyId, DayOfWeek dayOfWeek);

    Optional<AgencySchedule> findByAgencyIdAndDayOfWeek(Long agencyId, DayOfWeek dayOfWeek);
}
