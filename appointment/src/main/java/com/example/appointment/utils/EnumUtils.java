package com.example.appointment.utils;

public class EnumUtils {
    public static <T extends Enum<T>> T fromString(Class<T> enumClass, String value) {
        for (T constant : enumClass.getEnumConstants()) {
            if (constant.name().equalsIgnoreCase(value)) {
                return constant;
            }
        }
        throw new IllegalArgumentException("Invalid value '" + value + "' for enum " + enumClass.getSimpleName());
    }
}
