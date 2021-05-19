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
public class UserDTO {

    @Schema(description = "User name", example = "Juan", required = true)
    private String name;

    @Schema(description = "User surname", example = "Perez", required = true)
    private String sureName;

    @Schema(description = "User email", example = "juan@mail.com")
    private String email;

    @Schema(description = "User remaining cash", example = "45.5")
    private float cash;
}
