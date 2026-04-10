package dev.pdrolcs.gestao_consulta_medica_api.services;

import dev.pdrolcs.gestao_consulta_medica_api.repositories.ConsultaRepository;
import dev.pdrolcs.gestao_consulta_medica_api.repositories.MedicoRepository;
import dev.pdrolcs.gestao_consulta_medica_api.repositories.PacienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void agendarConsulta() {
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