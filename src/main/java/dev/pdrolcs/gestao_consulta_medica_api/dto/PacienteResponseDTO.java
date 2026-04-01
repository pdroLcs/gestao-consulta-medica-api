package dev.pdrolcs.gestao_consulta_medica_api.dto;

import java.time.LocalDate;

public record PacienteResponseDTO(
        Long id,
        String nome,
        String cpf,
        LocalDate dataNascimento
) {
}
