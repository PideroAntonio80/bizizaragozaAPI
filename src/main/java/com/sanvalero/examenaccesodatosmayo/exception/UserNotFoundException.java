package com.sanvalero.examenaccesodatosmayo.exception;

/**
 * Creado por @ author: Pedro Orós
 * el 19/05/2021
 */

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String message){
        super(message);
    }

    public UserNotFoundException(long id){
        super("User not found: " + id);
    }
}
