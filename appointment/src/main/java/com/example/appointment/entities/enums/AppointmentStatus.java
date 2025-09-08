package com.example.appointment.entities.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum AppointmentStatus {
    PENDING, CONFIRMED, CANCELLED, COMPLETED;

    @JsonCreator
    public static AppointmentStatus fromString(String value) {
        for (AppointmentStatus status : AppointmentStatus.values()) {
            if (status.name().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid appointment status: " + value);
    }
}
