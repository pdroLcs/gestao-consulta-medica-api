package dev.pdrolcs.gestao_consulta_medica_api.repositories;

import dev.pdrolcs.gestao_consulta_medica_api.models.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
