package com.sanvalero.examenaccesodatosmayo.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * Creado por @ author: Pedro Orós
 * el 19/05/2021
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "bike")
public class Bike {

    @Schema(description = "Bike identification number", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "Bike's code", example = "A002", required = true)
    @NotBlank
    @Column
    private String code;

    @Schema(description = "description of the bike's state", example = "Works property", required = true)
    @Column
    private String state;

    @Schema(description = "Station code", example = "123456")
    @Column
    private int station;

    @Schema(description = "Bike's rents list")
    @OneToMany(mappedBy = "bike", cascade = CascadeType.REMOVE)
    private List<Rent> bikeRentList;
}
