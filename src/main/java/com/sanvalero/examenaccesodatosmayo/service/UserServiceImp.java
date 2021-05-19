package com.sanvalero.examenaccesodatosmayo.service;

import com.sanvalero.examenaccesodatosmayo.domain.Rent;
import com.sanvalero.examenaccesodatosmayo.domain.User;
import com.sanvalero.examenaccesodatosmayo.exception.UserNotFoundException;
import com.sanvalero.examenaccesodatosmayo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 19/05/2021
 */

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Set<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<Rent> getUserRentList(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        List<Rent> myList = user.getUserRentList();
        return myList;
    }

    @Override
    public User addUser(User user) {
        /*userRepository.findByEmail(user.getEmail());
        if(userRepository.findByEmail(user.getEmail()).equals(email)) throw new UserExistException(email);*/
        return userRepository.save(user);
    }

    @Override
    public User modifyUserCash(long id, float cash) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        user.setCash(cash);
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        userRepository.delete(user);
    }
}
