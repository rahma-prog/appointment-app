package com.example.appointment.entities.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum AddressType {
    PERSONAL,
    PROFESSIONAL;

    @JsonCreator
    public static AddressType fromString(String value) {
        for (AddressType type : AddressType.values()) {
            if (type.name().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid address type: " + value);
    }
}
