package com.myworks.mywork.exception;

import com.myworks.mywork.error.BaseError;
import com.myworks.mywork.error.ValidationError;
import com.myworks.mywork.error.ValidationErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class BaseException  {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, List<ValidationError>> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.putIfAbsent(fieldName, new ArrayList<>());
            errors.get(fieldName).add(new ValidationError(fieldName,error.getCode(),error.getDefaultMessage(),((FieldError) error).getRejectedValue() != null ? ((FieldError) error).getRejectedValue().toString() : "null",ex.getMessage()));
        });

        ValidationErrorResponse validationErrorResponse=new ValidationErrorResponse();
        validationErrorResponse.setSuccess(false);
        validationErrorResponse.setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        validationErrorResponse.setValidationErrors(errors);
        validationErrorResponse.setMessage("Validation Error !");
        return new ResponseEntity<>(validationErrorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseError> handleAllExceptions(Exception ex) {
        return new ResponseEntity<>(BaseError.of("EXCEPTION_1",ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
