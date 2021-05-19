package com.sanvalero.examenaccesodatosmayo.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Creado por @ author: Pedro Orós
 * el 19/05/2021
 */

@Data
@NoArgsConstructor
public class BikeDTO {

    @Schema(description = "Bike's code", example = "A002", required = true)
    private String code;

    @Schema(description = "description of the bike's state", example = "Works property", required = true)
    private String state;

    @Schema(description = "Station code", example = "123456")
    private int station;
}
