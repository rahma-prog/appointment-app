package com.example.appointment.entities.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ContactStatus {
    ACTIVE,
    INACTIVE;

    @JsonCreator
    public static ContactStatus fromString(String value) {
        for (ContactStatus status : ContactStatus.values()) {
            if (status.name().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid contact status: " + value);
    }
}
