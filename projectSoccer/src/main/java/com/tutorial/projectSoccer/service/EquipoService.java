package com.tutorial.projectSoccer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tutorial.projectSoccer.dto.EquipoDTO;
import com.tutorial.projectSoccer.exception.BadRequestException;
import com.tutorial.projectSoccer.exception.ResourceNotFoundException;
import com.tutorial.projectSoccer.model.Equipo;
import com.tutorial.projectSoccer.repository.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class EquipoService implements EquipoInterface{

    @Autowired
    private EquipoRepository equipoRepository;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public List<EquipoDTO> buscarEquiposPorNombre(String nombre) {
        List<Equipo> equipos = equipoRepository.findByNombreContainingIgnoreCase(nombre);
        return equipos.stream()
                .map(equipo -> mapper.convertValue(equipo, EquipoDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public EquipoDTO guardarEquipo(EquipoDTO equipoDTO) {
        try {
            Equipo equipo = Equipo.builder()
                    .nombre(equipoDTO.getNombre())
                    .liga(equipoDTO.getLiga())
                    .pais(equipoDTO.getPais())
                    .build();

            equipo = equipoRepository.save(equipo);

            return mapper.convertValue(equipo, EquipoDTO.class);
        } catch (Exception ex) {
            throw new BadRequestException("La solicitud es invalida");
        }
    }




    @Override
    public EquipoDTO actualizarEquipo(EquipoDTO equipoDTO) {
        Equipo equipo = equipoRepository.findById(equipoDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Equipo no encontrado"));

        equipo.setNombre(equipoDTO.getNombre());
        equipo.setLiga(equipoDTO.getLiga());
        equipo.setPais(equipoDTO.getPais());

        equipo = equipoRepository.save(equipo);

        return mapper.convertValue(equipo, EquipoDTO.class);
    }

    @Override
    public void eliminarEquipoPorId(Long id) {
        Equipo equipo = equipoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Equipo no encontrado"));
        equipoRepository.deleteById(id);


    }

    @Override
    public List<EquipoDTO> listarEquipos(EquipoDTO equipoDTO) {
        List<Equipo> equipos = equipoRepository.findAll();
        return equipos.stream().map(equipo -> mapper.convertValue(equipo, EquipoDTO.class)).collect(Collectors.toList());
    }

    @Override
    public EquipoDTO listarEquipoPorId(Long id) {
        return equipoRepository.findById(id)
                .map(equipo -> mapper.convertValue(equipo, EquipoDTO.class))
                .orElseThrow(() -> new ResourceNotFoundException("Equipo no encontrado"));
    }
}
