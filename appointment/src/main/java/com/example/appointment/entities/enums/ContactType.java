package com.example.appointment.entities.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ContactType {
    EMAIL,
    PHONE;

    @JsonCreator
    public static ContactType fromString(String value) {
        for (ContactType type : ContactType.values()) {
            if (type.name().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid contact type: " + value);
    }
}
