package com.sanvalero.examenaccesodatosmayo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

/**
 * Creado por @ author: Pedro Orós
 * el 19/05/2021
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "rent")
public class Rent {

    @Schema(description = "Rent identification number", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "Rent starting date", example = "08/07/2021")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column
    private LocalDate startDate;

    @Schema(description = "Rent ending date", example = "08/07/2021")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column
    private LocalDate endDate;

    @Schema(description = "is this renting still active?", example = "true")
    @Column
    private boolean active;

    @Schema(description = "Renting price", example = "45.5")
    @Column
    private float price;

    @Schema(description = "Rent's User identity")
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @Schema(description = "Rent's bike identity")
    @ManyToOne
    @JoinColumn(name = "bike_id")
    @JsonBackReference(value = "use-movement")
    private Bike bike;
}
