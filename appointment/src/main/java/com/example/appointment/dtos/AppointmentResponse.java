package com.example.appointment.dtos;

import com.example.appointment.entities.Appointment;
import com.example.appointment.entities.enums.AppointmentStatus;

import java.time.LocalDateTime;

public class AppointmentResponse {
    private Long id;
    private Long userId;
    private Long agencyId;
    private Long staffId;
    private String staffJob;
    private LocalDateTime appointDate;
    private AppointmentStatus status;

    public AppointmentResponse(Appointment appointment) {
        this.id = appointment.getId();
        this.userId = appointment.getUser().getId();
        this.agencyId = appointment.getAgency().getId();
        this.staffId = appointment.getStaff().getId();
        this.staffJob = appointment.getStaffJob().name();
        this.appointDate = appointment.getAppointDate();
        this.status = appointment.getStatus();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Long agencyId) {
        this.agencyId = agencyId;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getStaffJob() {
        return staffJob;
    }

    public void setStaffJob(String staffJob) {
        this.staffJob = staffJob;
    }

    public LocalDateTime getAppointDate() {
        return appointDate;
    }

    public void setAppointDate(LocalDateTime appointDate) {
        this.appointDate = appointDate;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }
}
