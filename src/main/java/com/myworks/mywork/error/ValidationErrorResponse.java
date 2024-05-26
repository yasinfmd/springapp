package com.myworks.mywork.error;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ValidationErrorResponse  extends BaseError{
    private Map<String, List<ValidationError>> validationErrors;

}
