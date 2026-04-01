package dev.pdrolcs.gestao_consulta_medica_api.services;

import dev.pdrolcs.gestao_consulta_medica_api.dto.PacienteRequestDTO;
import dev.pdrolcs.gestao_consulta_medica_api.exceptions.BusinessException;
import dev.pdrolcs.gestao_consulta_medica_api.exceptions.ResourceNotFoundException;
import dev.pdrolcs.gestao_consulta_medica_api.models.Paciente;
import dev.pdrolcs.gestao_consulta_medica_api.repositories.PacienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PacienteServiceTest {

    @Mock
    private PacienteRepository pacienteRepository;

    @InjectMocks
    private PacienteService pacienteService;

    @Test
    void deveCriarPacienteComSucesso() {

        var paciente = new PacienteRequestDTO("João", "123", LocalDate.now());

        when(pacienteRepository.existsByCpf(paciente.cpf())).thenReturn(false);
        when(pacienteRepository.save(any(Paciente.class))).thenAnswer(invocation -> invocation.getArgument(0));

        var resultado = pacienteService.criarPaciente(paciente);

        assertNotNull(resultado);
        assertEquals("João", resultado.nome());

    }

    @Test
    void deveLancarExcecaoQuandoCpfJaExiste() {
        var paciente = new PacienteRequestDTO("João", "123", LocalDate.now());

        when(pacienteRepository.existsByCpf(paciente.cpf())).thenReturn(true);

        assertThrows(BusinessException.class, () -> pacienteService.criarPaciente(paciente));
    }

    @Test
    void deveListarPacientesComSucesso() {

        var paciente1 = new Paciente(1L, "João", "123", LocalDate.now());
        var paciente2 = new Paciente(2L, "Maria", "456", LocalDate.now());

        when(pacienteRepository.findAll()).thenReturn(List.of(paciente1, paciente2));

        var resultado = pacienteService.listarPacientes();

        assertEquals(2, resultado.size());
        assertEquals("João", resultado.getFirst().nome());
        assertEquals("Maria", resultado.get(1).nome());

    }

    @Test
    void deveRetornarListaVaziaQuandoNaoTiverPacientes() {
        when(pacienteRepository.findAll()).thenReturn(List.of());
        var resultado = pacienteService.listarPacientes();
        assertTrue(resultado.isEmpty());
    }

    @Test
    void deveBuscarPorIdComSucesso() {

        Long id = 1L;

        var paciente = new Paciente(id, "João", "123", LocalDate.now());

        when(pacienteRepository.findById(id)).thenReturn(Optional.of(paciente));

        var resultado = pacienteService.buscarPorId(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.id());
        assertEquals("João", resultado.nome());
        assertEquals("123", resultado.cpf());

    }

    @Test
    void deveLancarExcecaoQuandoNaoEncontrarPacientePorId() {
        when(pacienteRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> pacienteService.buscarPorId(1L));
    }
}