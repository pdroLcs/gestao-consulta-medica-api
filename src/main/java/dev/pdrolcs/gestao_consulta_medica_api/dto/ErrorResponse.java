package dev.pdrolcs.gestao_consulta_medica_api.dto;

import java.time.LocalDateTime;

public record ErrorResponse(
        int status,
        String message,
        String path,
        LocalDateTime timestamp
) {
}
