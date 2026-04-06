package dev.pdrolcs.gestao_consulta_medica_api.services;

import dev.pdrolcs.gestao_consulta_medica_api.dto.MedicoRequestDTO;
import dev.pdrolcs.gestao_consulta_medica_api.models.Medico;
import dev.pdrolcs.gestao_consulta_medica_api.repositories.MedicoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MedicoServiceTest {

    @Mock
    private MedicoRepository medicoRepository;

    @InjectMocks
    private MedicoService medicoService;

    private static final String nomeTeste = "João";
    private static final String especialidadeTeste = "Veterinário";

    @Nested
    class CriarMedico {

        @Test
        @DisplayName("Deve criar médico com sucesso")
        void criarMedico() {

            var medico = new MedicoRequestDTO(nomeTeste, especialidadeTeste);

            when(medicoRepository.save(any(Medico.class))).thenAnswer(invocation -> invocation.getArgument(0));

            var resultado = medicoService.criarMedico(medico);

            assertEquals(nomeTeste, resultado.nome());
            assertEquals(especialidadeTeste, resultado.especialidade());

        }
    }

    @Nested
    class ListarMedico {

        @Test
        @DisplayName("Deve listar todos os médicos com sucesso")
        void listarMedicos() {

            var medico1 = new Medico(nomeTeste, especialidadeTeste);
            var medico2 = new Medico("Ana", "Dentista");

            when(medicoRepository.findAll()).thenReturn(List.of(medico1, medico2));

            var resultado = medicoService.listarMedicos();

            assertEquals(2, resultado.size());
            assertEquals(nomeTeste, resultado.getFirst().nome());

        }

        @Test
        @DisplayName("Deve listar nenhum medico quando a lista estiver vazia")
        void listarNenhumMedico() {

            when(medicoRepository.findAll()).thenReturn(List.of());
            var resultado = medicoService.listarMedicos();

            assertEquals(0, resultado.size());

        }
    }
}