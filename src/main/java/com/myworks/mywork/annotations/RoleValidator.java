package com.myworks.mywork.annotations;


import com.myworks.mywork.enums.Role;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class RoleValidator implements ConstraintValidator<ValidRole, Role> {

    @Override
    public boolean isValid(Role role, ConstraintValidatorContext context) {
        return role != null && Arrays.stream(Role.values()).anyMatch(r -> r == role);
    }
}
