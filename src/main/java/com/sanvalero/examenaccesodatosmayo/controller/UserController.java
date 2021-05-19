package com.sanvalero.examenaccesodatosmayo.controller;

import com.sanvalero.examenaccesodatosmayo.domain.Rent;
import com.sanvalero.examenaccesodatosmayo.domain.User;
import com.sanvalero.examenaccesodatosmayo.exception.UserNotFoundException;
import com.sanvalero.examenaccesodatosmayo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import static com.sanvalero.examenaccesodatosmayo.controller.Response.NOT_FOUND;

/**
 * Creado por @ author: Pedro Orós
 * el 19/05/2021
 */

@RestController
@Tag(name = "user", description = "Users information")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /*--------------------------------FIND_ALL----------------------------------*/
    @Operation(summary = "Get all users list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User list",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = User.class)))),
            @ApiResponse(responseCode = "404", description = "User list failed",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/bizizaragoza/users", produces = "application/json")
    public ResponseEntity<Set<User>> getUser() {

        logger.info("Init getUser");

        Set<User> users = userService.findAll();

        logger.info("End getUser");

        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    /*--------------------------------FIND_BY_ID----------------------------------*/
    @Operation(summary = "Get user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User exists",
                    content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "404", description = "User doesn't exists",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/bizizaragoza/users/{id}", produces = "application/json")
    public ResponseEntity<User> getUserById(@PathVariable long id) {

        logger.info("Init getUserById");

        User user = userService.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        logger.info("End getUserById");

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /*--------------------------------FIND_BY_RENT_LIST----------------------------------*/
    @Operation(summary = "Get user's rent list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List exists",
                    content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "404", description = "List doesn't exists",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/bizizaragoza/users/{id}/rentlist", produces = "application/json")
    public ResponseEntity<List<Rent>> getUserRentList(@PathVariable long id) {

        logger.info("Init getUserRentList");

        List<Rent> myList = userService.getUserRentList(id);

        logger.info("End getUserRentList");

        return new ResponseEntity<>(myList, HttpStatus.OK);
    }

    /*--------------------------------ADD----------------------------------*/
    @Operation(summary = "Add new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User added",
                    content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "404", description = "User couldn't be added",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PostMapping(value= "/bizizaragoza/users", produces = "application/json", consumes = "application/json")
    public ResponseEntity<User> addUser(@RequestBody User user) {

        logger.info("Init addLocation");

        User addedUser = userService.addUser(user);

        logger.info("End addLocation");

        return new ResponseEntity<>(addedUser, HttpStatus.CREATED);
    }

    /*--------------------------------MODIFY_BY_CASH----------------------------------*/
    @Operation(summary = "Modify user's cash")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User's cash modified",
                    content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "404", description = "User doesn't exist",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PatchMapping(value = "/bizizaragoza/users/{id}/cash", produces = "application/json")
    public ResponseEntity<User> modifyUserCash(@PathVariable long id,
                                                          @RequestParam(value = "cash", defaultValue = "") float cash) {

        logger.info("Init modifyUserCash");

        User user = userService.modifyUserCash(id, cash);

        logger.info("End modifyUserCash");

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /*--------------------------------DELETE----------------------------------*/
    @Operation(summary = "Delete user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User deleted",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "User doesn't exist",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping("/bizizaragoza/users/{id}")
    public ResponseEntity<Response> deleteUser(@PathVariable long id) {

        logger.info("Init deleteUser");

        userService.deleteUser(id);

        logger.info("End deleteUser");

        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }

    /*--------------------------------EXCEPTION----------------------------------*/
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleException(UserNotFoundException unfe){
        Response response = Response.errorResponse(NOT_FOUND, unfe.getMessage());
        logger.error(unfe.getMessage(), unfe);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}
