package com.sanvalero.examenaccesodatosmayo.service;

import com.sanvalero.examenaccesodatosmayo.domain.Bike;

import java.util.Optional;
import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 19/05/2021
 */

public interface BikeService {

    Set<Bike> findAll();
    Optional<Bike> findById(long id);
    
    Bike addBike(Bike bike);
}
