package com.sanvalero.examenaccesodatosmayo.service;

import com.sanvalero.examenaccesodatosmayo.domain.Bike;
import com.sanvalero.examenaccesodatosmayo.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 19/05/2021
 */

@Service
public class BikeServiceImp implements BikeService {

    @Autowired
    private BikeRepository bikeRepository;

    @Override
    public Set<Bike> findAll() {
        return bikeRepository.findAll();
    }

    @Override
    public Optional<Bike> findById(long id) {
        return bikeRepository.findById(id);
    }

    @Override
    public Bike addBike(Bike bike) {
        return bikeRepository.save(bike);
    }
}
