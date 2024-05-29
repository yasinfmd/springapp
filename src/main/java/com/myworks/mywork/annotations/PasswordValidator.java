package com.myworks.mywork.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.annotation.Annotation;

public class PasswordValidator  implements ConstraintValidator<PasswordValidation,String> {

    @Override
    public void initialize(PasswordValidation constraintAnnotation) {}


    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null) {
            return false;
        }
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$");

    }
}
