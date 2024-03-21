package com.tutorial.projectSoccer.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EquipoDTO {
    private Long id;
    @NotBlank(message = "El nombre del equipo no puede estar vacío")
    private String nombre;
    @NotBlank(message = "La liga del equipo no puede estar vacía")
    private String liga;
    @NotBlank(message = "El país del equipo no puede estar vacío")
    private String pais;

}
