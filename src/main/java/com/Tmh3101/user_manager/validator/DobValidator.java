package com.Tmh3101.user_manager.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class DobValidator implements ConstraintValidator<DobConstraint, Date> {

    private int min;

    @Override
    public void initialize(DobConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        min = constraintAnnotation.min();
    }

    @Override
    public boolean isValid(Date value, ConstraintValidatorContext context) {

        if(Objects.isNull(value)) return true;

        LocalDate localDate = value.toLocalDate();
        long years = ChronoUnit.YEARS.between(localDate, LocalDate.now());

        return years >= min;
    }
}
