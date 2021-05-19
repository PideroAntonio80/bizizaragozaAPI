package com.sanvalero.examenaccesodatosmayo.exception;

/**
 * Creado por @ author: Pedro Orós
 * el 19/05/2021
 */
public class UserExistException extends RuntimeException {

    public UserExistException(String email) {
        super("User email already exists: " + email);
    }
}
