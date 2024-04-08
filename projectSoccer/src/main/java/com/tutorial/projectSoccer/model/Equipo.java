package com.tutorial.projectSoccer.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Equipos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank(message = "El nombre del equipo no puede estar vacío")
    private String nombre;

    @Column
    @NotBlank(message = "La liga del equipo no puede estar vacía")
    private String liga;

    @Column
    @NotBlank(message = "El país del equipo no puede estar vacío")
    private String pais;

}
