package com.example.appointment.validation;

import com.example.appointment.entities.Contact;
import com.example.appointment.entities.enums.ContactType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class ContactValueValidator implements ConstraintValidator<ValidContactValue, Contact> {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
    );

    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\+?[0-9\\s]{8,15}$");


    @Override
    public boolean isValid(Contact contact, ConstraintValidatorContext context) {
        if (contact == null) {
            return true;
        }

        // If value or type are null or blank, skip and let @NotBlank/@NotNull handle them
        if (contact.getValue() == null || contact.getValue().trim().isEmpty() || contact.getType() == null) {
            return true;
        }

        boolean isValid = true;

        if (contact.getType() == ContactType.EMAIL) {
            isValid = EMAIL_PATTERN.matcher(contact.getValue()).matches();
        } else if (contact.getType() == ContactType.PHONE) {
            isValid = PHONE_PATTERN.matcher(contact.getValue()).matches();
        }

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Invalid format for contact value: " + contact.getType())
                    .addPropertyNode("value")
                    .addConstraintViolation();
        }

        return isValid;
    }

}
