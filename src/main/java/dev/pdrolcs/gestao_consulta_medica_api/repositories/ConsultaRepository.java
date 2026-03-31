package dev.pdrolcs.gestao_consulta_medica_api.repositories;

import dev.pdrolcs.gestao_consulta_medica_api.models.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    List<Consulta> findByPacienteId(Long paciente_id);

    List<Consulta> findByMedicoId(Long medico_id);

    boolean existsByMedicoIdAndDataHora(Long medico_id, LocalDateTime dataHora);

}
