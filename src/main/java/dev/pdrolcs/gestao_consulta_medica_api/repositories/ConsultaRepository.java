package dev.pdrolcs.gestao_consulta_medica_api.repositories;

import dev.pdrolcs.gestao_consulta_medica_api.models.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    List<Consulta> findByPacientId(Long id);

    List<Consulta> findByMedicoId(Long id);

}
