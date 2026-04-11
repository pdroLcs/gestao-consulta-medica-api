package dev.pdrolcs.gestao_consulta_medica_api.services;

import dev.pdrolcs.gestao_consulta_medica_api.dto.ConsultaRequestDTO;
import dev.pdrolcs.gestao_consulta_medica_api.dto.ConsultaResponseDTO;
import dev.pdrolcs.gestao_consulta_medica_api.exceptions.BusinessException;
import dev.pdrolcs.gestao_consulta_medica_api.exceptions.ResourceNotFoundException;
import dev.pdrolcs.gestao_consulta_medica_api.models.Consulta;
import dev.pdrolcs.gestao_consulta_medica_api.models.Medico;
import dev.pdrolcs.gestao_consulta_medica_api.models.Paciente;
import dev.pdrolcs.gestao_consulta_medica_api.repositories.ConsultaRepository;
import dev.pdrolcs.gestao_consulta_medica_api.repositories.MedicoRepository;
import dev.pdrolcs.gestao_consulta_medica_api.repositories.PacienteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static dev.pdrolcs.gestao_consulta_medica_api.models.StatusEnum.AGENDADA;
import static dev.pdrolcs.gestao_consulta_medica_api.models.StatusEnum.CANCELADA;

@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;

    public ConsultaService(ConsultaRepository consultaRepository, PacienteRepository pacienteRepository, MedicoRepository medicoRepository) {
        this.consultaRepository = consultaRepository;
        this.pacienteRepository = pacienteRepository;
        this.medicoRepository = medicoRepository;
    }

    public ConsultaResponseDTO agendarConsulta(ConsultaRequestDTO consultaRequest) {
        if (consultaRequest.dataHora().isBefore(LocalDateTime.now())) throw new BusinessException("Não é possível agendar consulta no passado");
        if (consultaRepository.existsByMedicoIdAndDataHoraAndStatus(consultaRequest.medicoId(), consultaRequest.dataHora(), AGENDADA)) {
            throw new BusinessException("Este médico já possui consulta neste horário");
        }
        Paciente paciente = pacienteRepository.findById(consultaRequest.pacienteId()).orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado"));
        Medico medico = medicoRepository.findById(consultaRequest.medicoId()).orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado"));

        var consulta = new Consulta(paciente, medico, consultaRequest.dataHora(), AGENDADA);

        consultaRepository.save(consulta);
        return new ConsultaResponseDTO(consulta.getId(), consulta.getPaciente(), consulta.getMedico(), consulta.getDataHora(), consulta.getStatus());
    }

    public void cancelarConsulta(Long consultaId) {
        Consulta consulta = consultaRepository.findById(consultaId).orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrada"));
        if (consulta.getStatus() != AGENDADA) throw new BusinessException("Só é possível cancelar consultas agendadas");

        consulta.setStatus(CANCELADA);
        consultaRepository.save(consulta);
    }

    public List<ConsultaResponseDTO> listarConsultas() {
        return consultaRepository.findAll().stream().map(c -> new ConsultaResponseDTO(
                c.getId(), c.getPaciente(), c.getMedico(), c.getDataHora(), c.getStatus()
        )).toList();
    }

    public List<ConsultaResponseDTO> listarConsultaPorMedico(Long medicoId) {
        return consultaRepository.findByMedicoId(medicoId).stream().map(c -> new ConsultaResponseDTO(
                c.getId(), c.getPaciente(), c.getMedico(), c.getDataHora(), c.getStatus()
        )).toList();
    }

    public List<ConsultaResponseDTO> listarConsultaPorPaciente(Long pacienteId) {
        return consultaRepository.findByPacienteId(pacienteId).stream().map(c -> new ConsultaResponseDTO(
                c.getId(), c.getPaciente(), c.getMedico(), c.getDataHora(), c.getStatus()
        )).toList();
    }

}
