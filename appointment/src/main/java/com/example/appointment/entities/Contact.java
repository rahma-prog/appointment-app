package com.example.appointment.entities;

import com.example.appointment.entities.enums.ContactStatus;
import com.example.appointment.entities.enums.ContactType;
import com.example.appointment.entities.enums.ContactUsage;
import com.example.appointment.validation.ValidContactValue;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "contacts")
@ValidContactValue
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Contact value is required")
    private String value;



    @Enumerated(EnumType.STRING)
//    @EnumValidator(enumClass = ContactType.class, message = "Type must be EMAIL or PHONE")
    @NotNull(message = "Contact type is required")
    private ContactType type;


    @Enumerated(EnumType.STRING)
    @NotNull(message = "Contact status is required")
    private ContactStatus status;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Contact usage is required")
    private ContactUsage usage;

//    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    public Contact() {}

    public Contact(String value, ContactType type, ContactStatus status, ContactUsage usage, User user) {
        this.value = value;
        this.type = type;
        this.status = status;
        this.usage = usage;
        this.user = user;
    }


    public Long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ContactType getType() {
        return type;
    }

    public void setType(ContactType type) {
        this.type = type;
    }

    public ContactStatus getStatus() {
        return status;
    }

    public void setStatus(ContactStatus status) {
        this.status = status;
    }

    public ContactUsage getUsage() {
        return usage;
    }

    public void setUsage(ContactUsage usage) {
        this.usage = usage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
