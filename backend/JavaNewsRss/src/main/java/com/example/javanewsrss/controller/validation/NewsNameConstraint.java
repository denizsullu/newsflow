package com.example.javanewsrss.controller.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NewsNameValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface NewsNameConstraint {
    String message() default "Invalid News name";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
