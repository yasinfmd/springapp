package com.myworks.mywork.annotations;

import com.myworks.mywork.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Method;

public class UniqueUserValidator implements ConstraintValidator<UniqueUser, String> {

    @Autowired
    private UserRepository userRepository;

    private String fieldName;


    @Override
    public void initialize(UniqueUser constraintAnnotation) {
        this.fieldName = constraintAnnotation.fieldName();

    }


    @Override
    public boolean isValid(String val, ConstraintValidatorContext context) {
        try {
            Method method = userRepository.getClass().getMethod("findBy" + capitalize(this.fieldName), String.class);
            Object result = method.invoke(userRepository, val);
            return result == null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
