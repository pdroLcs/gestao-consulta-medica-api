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
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static dev.pdrolcs.gestao_consulta_medica_api.models.StatusEnum.AGENDADA;
import static dev.pdrolcs.gestao_consulta_medica_api.models.StatusEnum.CANCELADA;
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
    private Consulta consultaTeste;
    private ConsultaRequestDTO requestTeste;
    private LocalDateTime dataFutura;

    @BeforeEach
    void setup() {
        pacienteTeste = new Paciente(1L, "Pedro", "123", LocalDate.of(2006, 3, 13));
        medicoTeste = new Medico(1L, "João", "Veterinário");
        dataFutura = LocalDateTime.now().plusDays(1);
        consultaTeste = new Consulta(pacienteTeste, medicoTeste, dataFutura, AGENDADA);
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

    @Nested
    class CancelarConsulta {

        @Test
        @DisplayName("Deve cancelar consulta com sucesso")
        void cancelarConsulta() {

            when(consultaRepository.findById(any())).thenReturn(Optional.of(consultaTeste));

            consultaService.cancelarConsulta(consultaTeste.getId());

            ArgumentCaptor<Consulta> captor = ArgumentCaptor.forClass(Consulta.class);

            verify(consultaRepository).save(captor.capture());

            var consultaSalva = captor.getValue();

            assertEquals(CANCELADA, consultaSalva.getStatus());

        }

        @Test
        @DisplayName("Deve lançar exceção quando consulta não for encontrada")
        void consultaNaoEncontrada() {

            when(consultaRepository.findById(any())).thenReturn(Optional.empty());
            assertThrows(BusinessException.class, () -> consultaService.cancelarConsulta(1L));
            verify(consultaRepository, never()).save(any());

        }

        @Test
        @DisplayName("Deve lançar exceção quando consulta não está agendada")
        void consultaNaoAgendada() {

            var consulta = new Consulta(pacienteTeste, medicoTeste, dataFutura, CANCELADA);

            when(consultaRepository.findById(1L)).thenReturn(Optional.of(consulta));
            assertThrows(BusinessException.class, () -> consultaService.cancelarConsulta(1L));
            verify(consultaRepository, never()).save(any());

        }
    }
}