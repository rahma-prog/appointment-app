package com.example.appointment.entities;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public class Address {

    @NotBlank(message = "Governorate is required")
    private String governorate;

    @NotBlank(message = "City is required")
    private String city;


    @NotBlank(message = "Postal code is required")
    private String postalCode;

    public String getGovernorate() {
        return governorate;
    }

    public void setGovernorate(String governorate) {
        this.governorate = governorate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
