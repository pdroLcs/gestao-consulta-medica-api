package dev.pdrolcs.gestao_consulta_medica_api.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusEnum {

    AGENDADA(1, "Agendada"),
    CANCELADA(2, "Cancelada"),
    REALIZADA(3, "Realizada");

    private final int codigo;
    private final String descricao;

}
