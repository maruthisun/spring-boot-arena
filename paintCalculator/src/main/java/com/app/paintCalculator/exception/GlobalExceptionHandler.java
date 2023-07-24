package com.app.paintCalculator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> argsNotValidException(MethodArgumentNotValidException exception) {
        List<String> errorsList = exception.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        return new ResponseEntity<>(errorsList, HttpStatus.BAD_REQUEST);
    }
}
