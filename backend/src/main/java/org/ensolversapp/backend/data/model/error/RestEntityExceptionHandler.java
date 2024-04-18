package org.ensolversapp.backend.data.model.error;

import jakarta.persistence.EntityNotFoundException;
import org.ensolversapp.backend.data.model.error.exception.ApiErrorMessages;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class RestEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<ApiErrorMessages.ApiErrors> errors = new ArrayList<>();

        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.add(new ApiErrorMessages.ApiErrors(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return buildErrorResponse(ex.getStatusCode(), errors, 400);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)

    public ResponseEntity<Object> handleEntityNotFoundException(org.ensolversapp.backend.data.model.error.exception.EntityNotFoundException ex) {
        List<ApiErrorMessages.ApiErrors> errors = new ArrayList<>();

        errors.add(new ApiErrorMessages.ApiErrors(ex.getFieldName(), ex.getMessage()));
        return buildErrorResponse(ex.getStatusCode(), errors, 404);
    }

    private ResponseEntity<Object> buildErrorResponse(HttpStatusCode statusCode, List<ApiErrorMessages.ApiErrors> errors, Integer code) {
        return new ResponseEntity<>(new ApiErrorMessages(statusCode, errors, code), statusCode);
    }
}
