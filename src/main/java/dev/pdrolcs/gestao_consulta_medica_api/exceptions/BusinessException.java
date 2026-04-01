package dev.pdrolcs.gestao_consulta_medica_api.exceptions;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
