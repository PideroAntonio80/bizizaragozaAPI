package com.sanvalero.examenaccesodatosmayo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Creado por @ author: Pedro Orós
 * el 19/05/2021
 */

@RestControllerAdvice
public class UserExistException {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> errorManagement(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
