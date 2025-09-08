package com.example.appointment.entities.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum AgencyStatus {
    ACTIVE, INACTIVE;


    @JsonCreator
    public static AgencyStatus fromString(String value) {
        for (AgencyStatus status : AgencyStatus.values()) {
            if (status.name().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid agency status: " + value);
    }
}
