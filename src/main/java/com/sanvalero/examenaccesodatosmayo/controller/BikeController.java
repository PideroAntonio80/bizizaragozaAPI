package com.sanvalero.examenaccesodatosmayo.controller;

import com.sanvalero.examenaccesodatosmayo.domain.Bike;
import com.sanvalero.examenaccesodatosmayo.exception.BikeNotFoundException;
import com.sanvalero.examenaccesodatosmayo.service.BikeService;
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

import java.util.Set;

import static com.sanvalero.examenaccesodatosmayo.controller.Response.NOT_FOUND;

/**
 * Creado por @ author: Pedro Orós
 * el 19/05/2021
 */

@RestController
@Tag(name = "bikes", description = "Bikes information")
public class BikeController {

    private final Logger logger = LoggerFactory.getLogger(BikeController.class);

    @Autowired
    private BikeService bikeService;

    /*--------------------------------FIND_ALL----------------------------------*/
    @Operation(summary = "Get all Bikes list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bikes list",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Bike.class)))),
            @ApiResponse(responseCode = "404", description = "Bikes list failed",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/bizizaragoza/bikes", produces = "application/json")
    public ResponseEntity<Set<Bike>> getBikes() {

        logger.info("Init getBikes");

        Set<Bike> bikes = bikeService.findAll();

        logger.info("End getBikes");

        return ResponseEntity.status(HttpStatus.OK).body(bikes);
    }

    /*--------------------------------FIND_BY_ID----------------------------------*/
    @Operation(summary = "Get bike by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bike exists",
                    content = @Content(schema = @Schema(implementation = Bike.class))),
            @ApiResponse(responseCode = "404", description = "Bike doesn't exists",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/bizizaragoza/bikes/{id}", produces = "application/json")
    public ResponseEntity<Bike> getBikeById(@PathVariable long id) {

        logger.info("Init getBikeById");

        Bike bike = bikeService.findById(id)
                .orElseThrow(() -> new BikeNotFoundException(id));

        logger.info("End getBikeById");

        return new ResponseEntity<>(bike, HttpStatus.OK);
    }

    /*--------------------------------ADD----------------------------------*/
    @Operation(summary = "Add new bike")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Bike added",
                    content = @Content(schema = @Schema(implementation = Bike.class))),
            @ApiResponse(responseCode = "404", description = "Bike couldn't be added",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PostMapping(value= "/bizizaragoza/bikes", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Bike> addBike(@RequestBody Bike bike) {

        logger.info("Init addBike");

        Bike addedBike = bikeService.addBike(bike);

        logger.info("End addBike");

        return new ResponseEntity<>(addedBike, HttpStatus.CREATED);
    }

    /*--------------------------------EXCEPTION----------------------------------*/
    @ExceptionHandler(BikeNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleException(BikeNotFoundException bnfe){
        Response response = Response.errorResponse(NOT_FOUND, bnfe.getMessage());
        logger.error(bnfe.getMessage(), bnfe);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
