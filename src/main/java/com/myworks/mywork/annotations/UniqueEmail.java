package com.myworks.mywork.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueEmailValidator.class)
@Documented
public @interface UniqueEmail {
    String message() default "User is exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    String fieldName();
}
