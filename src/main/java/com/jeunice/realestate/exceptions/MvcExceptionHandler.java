package com.jeunice.realestate.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class MvcExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ExceptionResponse> noSuchElementExceptionHandler(NoSuchElementException e) {

        ExceptionResponse response = ExceptionResponse.builder()
                .timestamp(new Date())
                .message(e.getMessage())
                .build();
        if(e.getCause() != null){
            response.setDetails(List.of(e.getCause()));
        }
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionResponse> constraintViolationExceptionHandler(ConstraintViolationException e) {
        List<ConstraintViolation> errors = new ArrayList<>(e.getConstraintViolations().size());
        e.getConstraintViolations().forEach(constraintViolation -> {
            errors.add(constraintViolation);
        });
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .timestamp(new Date())
                .details(errors)
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
    private ExceptionResponse createResponse(Exception e){
        return ExceptionResponse.builder()
                .timestamp(new Date())
                .details(List.of(e.getCause()))
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> generalExceptionHandler(Exception e) {
        ExceptionResponse response = ExceptionResponse.builder()
                .timestamp(new Date())
                .details(List.of(e.getCause()))
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
