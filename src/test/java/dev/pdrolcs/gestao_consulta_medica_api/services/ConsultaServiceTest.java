package dev.pdrolcs.gestao_consulta_medica_api.services;

import dev.pdrolcs.gestao_consulta_medica_api.dto.ConsultaRequestDTO;
import dev.pdrolcs.gestao_consulta_medica_api.exceptions.BusinessException;
import dev.pdrolcs.gestao_consulta_medica_api.models.Consulta;
import dev.pdrolcs.gestao_consulta_medica_api.models.Medico;
import dev.pdrolcs.gestao_consulta_medica_api.models.Paciente;
import dev.pdrolcs.gestao_consulta_medica_api.repositories.ConsultaRepository;
import dev.pdrolcs.gestao_consulta_medica_api.repositories.MedicoRepository;
import dev.pdrolcs.gestao_consulta_medica_api.repositories.PacienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConsultaServiceTest {

    @Mock
    private ConsultaRepository consultaRepository;

    @Mock
    private PacienteRepository pacienteRepository;

    @Mock
    private MedicoRepository medicoRepository;

    @InjectMocks
    private ConsultaService consultaService;

    private Paciente pacienteTeste;
    private Medico medicoTeste;
    private ConsultaRequestDTO requestTeste;

    @BeforeEach
    void setup() {
        pacienteTeste = new Paciente(1L, "Pedro", "123", LocalDate.of(2006, 3, 13));
        medicoTeste = new Medico(1L, "João", "Veterinário");
        LocalDateTime dataFutura = LocalDateTime.now().plusDays(1);
        requestTeste = new ConsultaRequestDTO(1L, 1L, dataFutura);
    }

    @Nested
    class AgendarConsulta {

        @Test
        @DisplayName("Deve agendar consulta com sucesso")
        void agendarConsulta() {

            when(consultaRepository.existsByMedicoIdAndDataHoraAndStatus(any(), any(), any())).thenReturn(false);
            when(pacienteRepository.findById(any())).thenReturn(Optional.of(pacienteTeste));
            when(medicoRepository.findById(any())).thenReturn(Optional.of(medicoTeste));

            var resultado = consultaService.agendarConsulta(requestTeste);

            assertNotNull(resultado);
            verify(consultaRepository).save(any(Consulta.class));

        }

        @Test
        @DisplayName("Deve lançar exceção quando agendar consulta no passado")
        void consultaNoPassado() {

            var request = new ConsultaRequestDTO(1L, 1L, LocalDateTime.now().minusDays(1));

            assertThrows(BusinessException.class, () -> consultaService.agendarConsulta(request));
            verify(consultaRepository, never()).save(any());

        }

        @Test
        @DisplayName("Deve lançar exceção quando médico já tiver consulta")
        void medicoComConsulta() {

            when(consultaRepository.existsByMedicoIdAndDataHoraAndStatus(any(), any(), any())).thenReturn(true);
            assertThrows(BusinessException.class, () -> consultaService.agendarConsulta(requestTeste));
            verify(consultaRepository, never()).save(any());

        }
    }

    @Test
    void cancelarConsulta() {
    }

    @Test
    void listarConsultas() {
    }

    @Test
    void listarConsultaPorMedico() {
    }

    @Test
    void listarConsultaPorPaciente() {
    }
}