package com.sanvalero.examenaccesodatosmayo.exception;

/**
 * Creado por @ author: Pedro Orós
 * el 19/05/2021
 */

public class RentNotFoundException extends RuntimeException {

    public RentNotFoundException() {
        super();
    }

    public RentNotFoundException(String message){
        super(message);
    }

    public RentNotFoundException(long id){
        super("Rent not found: " + id);
    }
}
