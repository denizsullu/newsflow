package com.example.javanewsrss.controller.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;


public class NewsNameValidator implements ConstraintValidator<NewsNameConstraint,String > {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return !value.matches(".*\\d.*") && !value.contains(" ");
    }

    @Override
    public void initialize(NewsNameConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
