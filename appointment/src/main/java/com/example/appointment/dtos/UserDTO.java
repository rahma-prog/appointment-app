package com.example.appointment.dtos;

import com.example.appointment.entities.enums.DocumentType;
import com.example.appointment.entities.enums.Gender;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private DocumentType documentType;
    private String documentValue;
    private Gender gender;
    private LocalDate birthDate;
    private LocalDateTime createdAt;


    public UserDTO(Long id, String firstName, String lastName, DocumentType documentType,
                   String documentValue, Gender gender, LocalDate birthDate, LocalDateTime createdAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.documentType = documentType;
        this.documentValue = documentValue;
        this.gender = gender;
        this.birthDate = birthDate;
        this.createdAt = createdAt;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public String getDocumentValue() {
        return documentValue;
    }

    public void setDocumentValue(String documentValue) {
        this.documentValue = documentValue;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
