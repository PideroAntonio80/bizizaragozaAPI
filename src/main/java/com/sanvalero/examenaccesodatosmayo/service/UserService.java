package com.sanvalero.examenaccesodatosmayo.service;

import com.sanvalero.examenaccesodatosmayo.domain.Rent;
import com.sanvalero.examenaccesodatosmayo.domain.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 19/05/2021
 */

public interface UserService {

    Set<User> findAll();
    Optional<User> findById(long id);
    User findByEmail(String email);
    List<Rent> getUserRentList(long id);

    User addUser(User user);
    User modifyUserCash(long id, float cash);
    void deleteUser(long id);
}
