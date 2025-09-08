package com.example.appointment.entities;

import com.example.appointment.entities.enums.AppointmentStatus;
import com.example.appointment.entities.enums.JobTitle;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnoreProperties({"appointments"})
    private User user;

    @ManyToOne
    @JoinColumn(name = "agency_id", nullable = false)
    @JsonIgnoreProperties({"appointments", "staffMembers", "addresses", "schedules"})
    private Agency agency;

    @ManyToOne
    @JoinColumn(name = "staff_id", nullable = false)
    @JsonIgnoreProperties({"appointments", "agency"})
    private Staff staff;


    @NotNull(message = "Appointment date is required")
    private LocalDateTime appointDate;


    @Enumerated(EnumType.STRING)
    private JobTitle staffJob;

    @NotNull(message = "Status is required")
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Agency getAgency() { return agency; }
    public void setAgency(Agency agency) { this.agency = agency; }

    public Staff getStaff() { return staff; }
    public void setStaff(Staff staff) { this.staff = staff; }

    public LocalDateTime getAppointDate() { return appointDate; }
    public void setAppointDate(LocalDateTime appointDate) { this.appointDate = appointDate; }

    public JobTitle getStaffJob() { return staffJob; }
    public void setStaffJob(JobTitle staffJob) { this.staffJob = staffJob; }

    public AppointmentStatus getStatus() { return status; }
    public void setStatus(AppointmentStatus status) { this.status = status; }
}
