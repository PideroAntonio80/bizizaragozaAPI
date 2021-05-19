package com.sanvalero.examenaccesodatosmayo.service;

import com.sanvalero.examenaccesodatosmayo.domain.Rent;
import com.sanvalero.examenaccesodatosmayo.domain.dto.RentDTO;

import java.util.Optional;
import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 19/05/2021
 */

public interface RentService {

    Set<Rent> findAll();
    Optional<Rent> findById(long id);

    Rent addRentToUserAndBike(long idUser, long idBike, RentDTO rentDTO);
}
