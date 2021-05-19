package com.sanvalero.examenaccesodatosmayo.service;

import com.sanvalero.examenaccesodatosmayo.domain.Bike;
import com.sanvalero.examenaccesodatosmayo.domain.Rent;
import com.sanvalero.examenaccesodatosmayo.domain.User;
import com.sanvalero.examenaccesodatosmayo.domain.dto.RentDTO;
import com.sanvalero.examenaccesodatosmayo.exception.BikeNotFoundException;
import com.sanvalero.examenaccesodatosmayo.exception.UserNotFoundException;
import com.sanvalero.examenaccesodatosmayo.repository.BikeRepository;
import com.sanvalero.examenaccesodatosmayo.repository.RentRepository;
import com.sanvalero.examenaccesodatosmayo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 19/05/2021
 */

@Service
public class RentServiceImp implements RentService{

    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BikeRepository bikeRepository;


    @Override
    public Set<Rent> findAll() {
        return rentRepository.findAll();
    }

    @Override
    public Optional<Rent> findById(long id) {
        return rentRepository.findById(id);
    }

    @Override
    public Rent addRentToUserAndBike(long idUser, long idBike, RentDTO rentDTO) {
        Rent newRent = new Rent();
        setRent(newRent, rentDTO);
        newRent = rentRepository.save(newRent);

        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new UserNotFoundException(idUser));
        newRent.setUser(user);

        Bike bike = bikeRepository.findById(idBike)
                .orElseThrow(() -> new BikeNotFoundException(idBike));
        newRent.setBike(bike);

        rentRepository.save(newRent);

        return newRent;
    }

    public void setRent(Rent rent, RentDTO rentDTO) {
        rent.setStartDate(rentDTO.getStartDate());
        rent.setEndDate(rentDTO.getEndDate());
        rent.setActive(rentDTO.isActive());
        rent.setPrice(rentDTO.getPrice());
    }
}
