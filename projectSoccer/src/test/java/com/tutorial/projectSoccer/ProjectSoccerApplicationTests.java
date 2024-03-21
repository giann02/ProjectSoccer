package com.tutorial.projectSoccer;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tutorial.projectSoccer.dto.EquipoDTO;
import com.tutorial.projectSoccer.model.Equipo;
import com.tutorial.projectSoccer.repository.EquipoRepository;
import com.tutorial.projectSoccer.service.EquipoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProjectSoccerApplicationTests {

	@Mock
	private EquipoRepository equipoRepositoryMock;

	@Mock
	private ObjectMapper mapperMock;

	@InjectMocks
	private EquipoService equipoService;

	@Test
	void testActualizarEquipo() {
		EquipoDTO equipoDTO = new EquipoDTO();
		equipoDTO.setId(1L);
		equipoDTO.setNombre("Barracas Central");
		equipoDTO.setLiga("Liga Argentina");
		equipoDTO.setPais("Argentina");

		Equipo equipo = new Equipo();
		when(equipoRepositoryMock.findById(1L)).thenReturn(Optional.of(equipo));
		when(equipoRepositoryMock.save(any())).thenReturn(equipo);
		when(mapperMock.convertValue(any(), eq(EquipoDTO.class))).thenReturn(equipoDTO);

		// Act
		EquipoDTO equipoActualizado = equipoService.actualizarEquipo(equipoDTO);

		// Assert
		assertThat(equipoActualizado).isEqualTo(equipoDTO);
	}

	@Test
	public void testGuardarEquipo() {
		// Arrange
		EquipoDTO equipoDTO = new EquipoDTO();
		equipoDTO.setNombre("Deportivo Riestra");
		equipoDTO.setLiga("Liga Argentina");
		equipoDTO.setPais("Argentina");

		when(mapperMock.convertValue(any(), eq(EquipoDTO.class))).thenReturn(equipoDTO);
		when(equipoRepositoryMock.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

		// Act
		EquipoDTO equipoGuardado = equipoService.guardarEquipo(equipoDTO);

		// Assert
		assertEquals(equipoDTO, equipoGuardado);
	}


	@Test
	void testBuscarEquiposPorNombre() {
		// Arrange
		String nombreABuscar = "Deportivo";

		List<Equipo> equiposEncontrados = Arrays.asList(
				new Equipo(1L, "Deportivo Riestra", "Liga Argentina", "Argentina"),
				new Equipo(2L, "Deportivo Barracas Central", "Liga Argentina", "Argentina"));

		when(equipoRepositoryMock.findByNombreContainingIgnoreCase(nombreABuscar)).thenReturn(equiposEncontrados);
		when(mapperMock.convertValue(any(), eq(EquipoDTO.class))).thenReturn(new EquipoDTO());

		// Act
		List<EquipoDTO> equiposEncontradosDTO = equipoService.buscarEquiposPorNombre(nombreABuscar);

		// Assert
		assertThat(equiposEncontradosDTO).isNotNull();
		assertThat(equiposEncontradosDTO).hasSize(2);
	}



	@Test
	public void testListarEquipos() {
		// Arrange
		List<Equipo> equipos = new ArrayList<>();
		equipos.add(Equipo.builder().id(1L).nombre("Equipo 1").liga("Liga 1").pais("País 1").build());
		equipos.add(Equipo.builder().id(2L).nombre("Equipo 2").liga("Liga 2").pais("País 2").build());

		when(equipoRepositoryMock.findAll()).thenReturn(equipos);

		// Act
		List<EquipoDTO> equiposDTO = equipoService.listarEquipos(null);

		// Assert
		assertNotNull(equiposDTO);
		assertFalse(equiposDTO.isEmpty());
		assertEquals(2, equiposDTO.size());
	}

}
