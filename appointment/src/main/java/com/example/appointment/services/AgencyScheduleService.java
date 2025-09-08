package com.example.appointment.services;

import com.example.appointment.entities.AgencySchedule;
import com.example.appointment.repositories.AgencyScheduleRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AgencyScheduleService {

    @Autowired
    private AgencyScheduleRepository scheduleRepository;

    public Page<AgencySchedule> getAllSchedules(Pageable pageable) {
        return scheduleRepository.findAll(pageable);
    }

    public AgencySchedule createSchedule(@Valid AgencySchedule schedule) {

        if (scheduleRepository.existsByAgencyIdAndDayOfWeek(schedule.getAgency().getId(), schedule.getDayOfWeek())) {
            throw new IllegalArgumentException("Schedule already exists for this agency on " + schedule.getDayOfWeek());
        }

        if (schedule.getEndTime().isBefore(schedule.getStartTime()) || schedule.getEndTime().equals(schedule.getStartTime())) {
            throw new IllegalArgumentException("End time must be after start time");
        }

        return scheduleRepository.save(schedule);
    }

    public AgencySchedule getScheduleById(Long id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Schedule not found with id: " + id));
    }

    public void deleteSchedule(Long id) {
        if (!scheduleRepository.existsById(id)) {
            throw new IllegalArgumentException("Schedule not found with id: " + id);
        }
        scheduleRepository.deleteById(id);
    }
}
