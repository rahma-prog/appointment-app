package com.example.appointment.entities.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ContactUsage {
    PERSONAL,
    PROFESSIONAL;

    @JsonCreator
    public static ContactUsage fromString(String value) {
        for (ContactUsage usage : ContactUsage.values()) {
            if (usage.name().equalsIgnoreCase(value)) {
                return usage;
            }
        }
        throw new IllegalArgumentException("Invalid contact usage: " + value);
    }
}
