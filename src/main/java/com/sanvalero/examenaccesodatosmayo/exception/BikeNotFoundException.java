package com.sanvalero.examenaccesodatosmayo.exception;

/**
 * Creado por @ author: Pedro Orós
 * el 19/05/2021
 */

public class BikeNotFoundException extends RuntimeException {

    public BikeNotFoundException() {
        super();
    }

    public BikeNotFoundException(String message){
        super(message);
    }

    public BikeNotFoundException(long id){
        super("Bike not found: " + id);
    }
}
