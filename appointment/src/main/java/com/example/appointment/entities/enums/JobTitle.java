package com.example.appointment.entities.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum JobTitle {
    MANAGER,
    RECEPTIONIST,
    CASHIER;

    @JsonCreator
    public static JobTitle fromString(String value) {
        for (JobTitle title : JobTitle.values()) {
            if (title.name().equalsIgnoreCase(value)) {
                return title;
            }
        }
        throw new IllegalArgumentException("Invalid job title: " + value);
    }
}
