package dev.pdrolcs.gestao_consulta_medica_api.services;

import dev.pdrolcs.gestao_consulta_medica_api.dto.MedicoRequestDTO;
import dev.pdrolcs.gestao_consulta_medica_api.dto.MedicoResponseDTO;
import dev.pdrolcs.gestao_consulta_medica_api.models.Medico;
import dev.pdrolcs.gestao_consulta_medica_api.repositories.MedicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;

    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    public MedicoResponseDTO criarMedico(MedicoRequestDTO medicoRequest) {
        var medico = new Medico(medicoRequest.nome(), medicoRequest.especialidade());
        medicoRepository.save(medico);
        return new MedicoResponseDTO(medico.getId(), medico.getNome(), medico.getEspecialidade());
    }

    public List<MedicoResponseDTO> listarMedicos() {
        return medicoRepository.findAll().stream().map(m -> new MedicoResponseDTO(
                m.getId(), m.getNome(), m.getEspecialidade()
        )).toList();
    }
}
