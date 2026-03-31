package dev.pdrolcs.gestao_consulta_medica_api.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "consultas")
@Getter
@Setter
@RequiredArgsConstructor
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private final Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "medico_id", nullable = false)
    private final Medico medico;

    @Column(nullable = false)
    private final LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private final StatusEnum status;

}
