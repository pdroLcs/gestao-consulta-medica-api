package dev.pdrolcs.gestao_consulta_medica_api.repositories;

import dev.pdrolcs.gestao_consulta_medica_api.models.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
}
