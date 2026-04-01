package dev.pdrolcs.gestao_consulta_medica_api.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PacienteRequestDTO(

        @NotNull
        String nome,

        @NotNull
        String cpf,

        @NotNull
        LocalDate dataNascimento
) { }
