package dev.pdrolcs.gestao_consulta_medica_api.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record ConsultaRequestDTO(

    @NotBlank
    Long pacienteId,

    @NotBlank
    Long medicoId,

    @NotBlank
    LocalDateTime dataHora
) { }
