package dev.pdrolcs.gestao_consulta_medica_api.dto;

public record MedicoResponseDTO(
        Long id,
        String nome,
        String especialidade
) {
}
