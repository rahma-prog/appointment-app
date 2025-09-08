package com.example.appointment.controllers;

import com.example.appointment.entities.AgencySchedule;
import com.example.appointment.services.AgencyScheduleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/schedules")
public class AgencyScheduleController {

    @Autowired
    private AgencyScheduleService scheduleService;

    @GetMapping
    public Page<AgencySchedule> getAllSchedules(Pageable pageable) {
        return scheduleService.getAllSchedules(pageable);
    }

    @PostMapping
    public ResponseEntity<AgencySchedule> createSchedule(@RequestBody @Valid AgencySchedule schedule) {
        AgencySchedule created = scheduleService.createSchedule(schedule);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public AgencySchedule getSchedule(@PathVariable Long id) {
        return scheduleService.getScheduleById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
    }
}
