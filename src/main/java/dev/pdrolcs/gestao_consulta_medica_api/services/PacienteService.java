package dev.pdrolcs.gestao_consulta_medica_api.services;

import dev.pdrolcs.gestao_consulta_medica_api.dto.PacienteRequestDTO;
import dev.pdrolcs.gestao_consulta_medica_api.dto.PacienteResponseDTO;
import dev.pdrolcs.gestao_consulta_medica_api.models.Paciente;
import dev.pdrolcs.gestao_consulta_medica_api.repositories.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public PacienteResponseDTO criarPaciente(PacienteRequestDTO pacienteRequest) {
        if (pacienteRepository.existsByCpf(pacienteRequest.cpf())) throw new RuntimeException("CPF já cadastrado");

        var paciente = new Paciente(pacienteRequest.nome(), pacienteRequest.cpf(), pacienteRequest.dataNascimento());
        pacienteRepository.save(paciente);
        return new PacienteResponseDTO(paciente.getId(), paciente.getNome(), pacienteRequest.cpf(), paciente.getDataNascimento());
    }

    public List<PacienteResponseDTO> listarPacientes() {
        return pacienteRepository.findAll().stream().map(p -> new PacienteResponseDTO(
                p.getId(), p.getNome(), p.getCpf(), p.getDataNascimento()
        )).toList();
    }

    public PacienteResponseDTO buscarPorId(Long id) {
        return pacienteRepository.findById(id)
                .map(p -> new PacienteResponseDTO(p.getId(), p.getNome(), p.getCpf(), p.getDataNascimento()))
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
    }

}
