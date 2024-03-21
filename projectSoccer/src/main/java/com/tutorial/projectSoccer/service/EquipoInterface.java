package com.tutorial.projectSoccer.service;

import com.tutorial.projectSoccer.dto.EquipoDTO;

import java.util.List;

public interface EquipoInterface {

    List<EquipoDTO> buscarEquiposPorNombre(String nombre);


    EquipoDTO guardarEquipo(EquipoDTO equipoDTO);
    EquipoDTO actualizarEquipo(EquipoDTO equipoDTO);
    void eliminarEquipoPorId(Long id);
    List<EquipoDTO> listarEquipos (EquipoDTO equipoDTO);
    EquipoDTO listarEquipoPorId(Long id);
}
