package com.example.appointment.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Table(name = "agency_schedules",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"agency_id", "dayOfWeek"})})
public class AgencySchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Day of the week is required")
    private DayOfWeek dayOfWeek;

    @NotNull(message = "Start time is required")
    private LocalTime startTime;

    @NotNull(message = "End time is required")
    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(name = "agency_id", nullable = false)
//    @JsonBackReference
    private Agency agency;


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public DayOfWeek getDayOfWeek() { return dayOfWeek; }
    public void setDayOfWeek(DayOfWeek dayOfWeek) { this.dayOfWeek = dayOfWeek; }

    public LocalTime getStartTime() { return startTime; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }

    public LocalTime getEndTime() { return endTime; }
    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }

    public Agency getAgency() { return agency; }
    public void setAgency(Agency agency) { this.agency = agency; }
}
