package com.example.appointment.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ContactValueValidator.class)
@Documented
public @interface ValidContactValue {
    String message() default "Invalid contact value";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
