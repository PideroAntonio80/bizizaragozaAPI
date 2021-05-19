package com.sanvalero.examenaccesodatosmayo.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Creado por @ author: Pedro Orós
 * el 19/05/2021
 */

@Data
@NoArgsConstructor
public class RentDTO {

    @Schema(description = "Rent starting date", example = "08/07/2021")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate startDate;

    @Schema(description = "Rent ending date", example = "08/07/2021")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate endDate;

    @Schema(description = "is this renting still active?", example = "true")
    private boolean active;

    @Schema(description = "Renting price", example = "45.5")
    private float price;
}
