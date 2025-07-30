package com.example.appointment.entities;

import com.example.appointment.entities.enums.ContactStatus;
import com.example.appointment.entities.enums.ContactType;
import com.example.appointment.entities.enums.ContactUsage;
import jakarta.persistence.*;

@Entity
@Table(name = "contacts")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String value;



    @Enumerated(EnumType.STRING)
    private ContactType type;



    @Enumerated(EnumType.STRING)
    private ContactStatus status;

    @Enumerated(EnumType.STRING)
    private ContactUsage usage;

    @ManyToOne
    @JoinColumn(name = "user_id")
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
