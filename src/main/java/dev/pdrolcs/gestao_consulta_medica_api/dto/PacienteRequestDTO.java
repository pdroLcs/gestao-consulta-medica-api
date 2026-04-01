package dev.pdrolcs.gestao_consulta_medica_api.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record PacienteRequestDTO(

        @NotBlank
        String nome,

        @NotBlank
        String cpf,

        @NotBlank
        LocalDate dataNascimento
) { }
