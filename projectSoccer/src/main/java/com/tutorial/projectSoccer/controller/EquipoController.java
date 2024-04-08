package com.tutorial.projectSoccer.controller;


import com.tutorial.projectSoccer.dto.EquipoDTO;
import com.tutorial.projectSoccer.model.Equipo;
import com.tutorial.projectSoccer.service.EquipoService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@OpenAPIDefinition(info = @Info(title = "Project Soccer", version = "1.0", description = "Prueba tecnica para ingresar a dux software"))
public class EquipoController {

    @Autowired
    private EquipoService equipoService;


    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/equipos")
    public ResponseEntity<EquipoDTO> guardarEquipo(@RequestBody EquipoDTO equipoDTO){
        EquipoDTO equipoGuardado = equipoService.guardarEquipo(equipoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(equipoGuardado);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/equipos/{id}")
    public ResponseEntity<EquipoDTO> listarEquipoPorId(@PathVariable Long id) {
        EquipoDTO equipo = equipoService.listarEquipoPorId(id);
        return ResponseEntity.ok(equipo);
    }
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/equipos/{id}")
    public ResponseEntity<EquipoDTO> actualizarEquipo(@PathVariable Long id, @RequestBody EquipoDTO equipoDTO) {
        equipoDTO.setId(id);
        EquipoDTO equipoActualizado = equipoService.actualizarEquipo(equipoDTO);
        return ResponseEntity.ok(equipoActualizado);
    }


    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("/equipos/{id}")
    public void eliminarEquipoPorId(@PathVariable Long id) {
        equipoService.eliminarEquipoPorId(id);
    }
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/equipos/buscar")
    public ResponseEntity<List<EquipoDTO>> buscarEquiposPorNombre(@RequestParam String nombre) {
        List<EquipoDTO> equipos = equipoService.buscarEquiposPorNombre(nombre);
        return ResponseEntity.ok(equipos);
    }


}
