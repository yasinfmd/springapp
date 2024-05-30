package com.myworks.mywork.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

public class ValidImageValidator implements ConstraintValidator<ValidImage, MultipartFile> {
    @Override
    public void initialize(ValidImage constraintAnnotation) {
    }

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        if (value.isEmpty()) {
            return false;
        }
        return value.getContentType().equals("image/jpg")
                || value.getContentType().equals("image/png")
                || value.getContentType().equals("image/jpeg")
                || value.getContentType().equals("image/gif");
    }
}
