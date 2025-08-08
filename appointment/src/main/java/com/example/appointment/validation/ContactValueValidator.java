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

//    private static final Pattern PHONE_PATTERN = Pattern.compile("^[0-9\\s]{8}$");
private static final Pattern PHONE_FORMAT_PATTERN = Pattern.compile("^[0-9\\s]+$");

    @Override
    public boolean isValid(Contact contact, ConstraintValidatorContext context) {
        if (contact == null) {
            return true;
        }
        String value = contact.getValue();
        ContactType type = contact.getType();

        if (contact.getValue() == null || contact.getValue().trim().isEmpty() || contact.getType() == null) {
            return true;
        }



        boolean valid = true;

        if (type == ContactType.EMAIL) {
            valid = EMAIL_PATTERN.matcher(value).matches();
            if (!valid) {
                return buildViolation(context, "Invalid email format");
            }
        } else if (type == ContactType.PHONE) {
            String sanitized = value.replaceAll("\\s+", "");
            if (!PHONE_FORMAT_PATTERN.matcher(value).matches()) {
                return buildViolation(context, "Phone number must contain only digits and spaces");
            }
            if (sanitized.length() != 8) {
                return buildViolation(context, "Phone number must be exactly 8 digits");
            }
        }

        return true;
    }

    private boolean buildViolation(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addPropertyNode("value")
                .addConstraintViolation();
        return false;
    }
//
//        boolean isValid = true;
//
//        if (contact.getType() == ContactType.EMAIL) {
//            isValid = EMAIL_PATTERN.matcher(contact.getValue()).matches();
//        } else if (contact.getType() == ContactType.PHONE) {
//            isValid = PHONE_PATTERN.matcher(contact.getValue()).matches();
//        }
//
//        if (!isValid) {
//            context.disableDefaultConstraintViolation();
//            context.buildConstraintViolationWithTemplate("Invalid format for contact value: " + contact.getType())
//                    .addPropertyNode("value")
//                    .addConstraintViolation();
//        }
//
//        return isValid;
//    }

}
