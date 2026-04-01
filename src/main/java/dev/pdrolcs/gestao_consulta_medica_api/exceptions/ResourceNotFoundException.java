package dev.pdrolcs.gestao_consulta_medica_api.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException() {
        super("Não encontrado");
    }
}
