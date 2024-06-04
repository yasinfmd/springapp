package com.myworks.mywork.exception;

import com.myworks.mywork.error.BaseError;
import com.myworks.mywork.error.ValidationError;
import com.myworks.mywork.error.ValidationErrorResponse;
import com.myworks.mywork.utils.MessageHelper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

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
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<BaseError> handleRuntimeException(Exception ex, HttpServletRequest request) {
        return new ResponseEntity<>(BaseError.of(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),ex.getMessage(),request.getRequestURI()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BaseError> handleBadRequestException(Exception ex, HttpServletRequest request) {
        return new ResponseEntity<>(BaseError.of(HttpStatus.BAD_REQUEST.getReasonPhrase(),ex.getMessage(),request.getRequestURI()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<BaseError> handleRecordNotFoundException(Exception ex, HttpServletRequest request) {
        return new ResponseEntity<>(BaseError.of(HttpStatus.NOT_FOUND.getReasonPhrase(),ex.getMessage(),request.getRequestURI()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseError> handleAllExceptions(Exception ex, HttpServletRequest request) {
        return new ResponseEntity<>(BaseError.of(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), MessageHelper.getMessage("error.local.message"),request.getRequestURI()), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
