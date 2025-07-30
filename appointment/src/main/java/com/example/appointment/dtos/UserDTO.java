package com.example.appointment.dtos;

public class UserDTO {

    @NotBlank(message = "First name is required")
    private String firstName;
}
