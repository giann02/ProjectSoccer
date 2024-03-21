package com.tutorial.projectSoccer.repository;

import com.tutorial.projectSoccer.model.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipoRepository extends JpaRepository<Equipo, Long> {
    List<Equipo> findByNombreContainingIgnoreCase(String nombre);

}
