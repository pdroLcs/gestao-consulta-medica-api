package dev.pdrolcs.gestao_consulta_medica_api.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ConsultaRequestDTO(

    @NotNull
    Long pacienteId,

    @NotNull
    Long medicoId,

    @NotNull
    LocalDateTime dataHora
) { }
