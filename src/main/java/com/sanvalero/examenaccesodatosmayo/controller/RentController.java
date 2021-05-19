package com.sanvalero.examenaccesodatosmayo.controller;

import com.sanvalero.examenaccesodatosmayo.domain.Rent;
import com.sanvalero.examenaccesodatosmayo.domain.dto.RentDTO;
import com.sanvalero.examenaccesodatosmayo.exception.RentNotFoundException;
import com.sanvalero.examenaccesodatosmayo.service.RentService;
import io.swagger.v3.oas.annotations.Operation;
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

import static com.sanvalero.examenaccesodatosmayo.controller.Response.NOT_FOUND;

/**
 * Creado por @ author: Pedro Orós
 * el 19/05/2021
 */

@RestController
@Tag(name = "Rents", description = "Rents information")
public class RentController {

    private final Logger logger = LoggerFactory.getLogger(RentController.class);

    @Autowired
    private RentService rentService;

    /*--------------------------------FIND_BY_ID----------------------------------*/
    @Operation(summary = "Get rent by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rent exist",
                    content = @Content(schema = @Schema(implementation = Rent.class))),
            @ApiResponse(responseCode = "404", description = "Rent doesn't exist",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/bizizaragoza/rents/{id}", produces = "application/json")
    public ResponseEntity<Rent> getRentById(@PathVariable long id) {

        logger.info("Init getRentById");

        Rent rent = rentService.findById(id)
                .orElseThrow(() -> new RentNotFoundException(id));

        logger.info("End getRentById");

        return new ResponseEntity<>(rent, HttpStatus.OK);
    }

    /*--------------------------------ADD----------------------------------*/
    @Operation(summary = "Add new rent to user and bike")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Rent added",
                    content = @Content(schema = @Schema(implementation = Rent.class))),
            @ApiResponse(responseCode = "404", description = "Rent couldn't be added",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PostMapping(value = "/bizizaragoza/user/{idUser}/bike/{idBike}/rents", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Rent> addRent(@PathVariable long idUser, @PathVariable long idBike, @RequestBody RentDTO rentDTO) {

        logger.info("Init addRent");

        Rent addedRent = rentService.addRentToUserAndBike(idUser, idBike, rentDTO);

        logger.info("End addRent");

        return new ResponseEntity<>(addedRent, HttpStatus.CREATED);
    }

    /*--------------------------------EXCEPTION----------------------------------*/
    @ExceptionHandler(RentNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleException(RentNotFoundException rnfe){
        Response response = Response.errorResponse(NOT_FOUND, rnfe.getMessage());
        logger.error(rnfe.getMessage(), rnfe);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
