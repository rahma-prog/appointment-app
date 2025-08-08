package com.example.appointment.entities.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum DocumentType {
    CIN,
    PASSPORT,
    RESIDENCE_CARD;

    @JsonCreator
    public static DocumentType fromString(String value) {
        for (DocumentType type : DocumentType.values()) {
            if (type.name().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid document type: " + value);
    }


}
