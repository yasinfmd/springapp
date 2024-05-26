package com.myworks.mywork.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class ValidationError {
    private String fieldName;
    private String validationCode;
    private String errorMessage;
    private String errorValue;
    private String description;
}
