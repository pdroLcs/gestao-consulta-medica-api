package dev.pdrolcs.gestao_consulta_medica_api.dto;

import dev.pdrolcs.gestao_consulta_medica_api.models.Medico;
import dev.pdrolcs.gestao_consulta_medica_api.models.Paciente;
import dev.pdrolcs.gestao_consulta_medica_api.models.StatusEnum;

import java.time.LocalDateTime;

public record ConsultaResponseDTO(
        Long id,
        Paciente paciente,
        Medico medico,
        LocalDateTime dataHora,
        StatusEnum status
) {
}
