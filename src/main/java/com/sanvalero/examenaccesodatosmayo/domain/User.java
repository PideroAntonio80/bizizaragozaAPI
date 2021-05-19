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
@Entity(name = "user")
public class User {

    @Schema(description = "User identification code", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "User name", example = "Juan", required = true)
    @NotBlank
    @Column
    private String name;

    @Schema(description = "User surname", example = "Perez", required = true)
    @NotBlank
    @Column
    private String sureName;

    @Schema(description = "User email", example = "juan@mail.com")
    @Column
    private String email;

    @Schema(description = "User remaining cash", example = "45.5")
    @Column
    private float cash;

    @Schema(description = "User's rents list")
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Rent> userRentList;
}
