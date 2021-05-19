package com.sanvalero.examenaccesodatosmayo.repository;

import com.sanvalero.examenaccesodatosmayo.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 19/05/2021
 */

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Set<User> findAll();
    User findByEmail(String email);
}
