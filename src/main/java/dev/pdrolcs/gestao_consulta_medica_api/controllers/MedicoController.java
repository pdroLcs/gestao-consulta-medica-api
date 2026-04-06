package dev.pdrolcs.gestao_consulta_medica_api.controllers;

import dev.pdrolcs.gestao_consulta_medica_api.dto.MedicoRequestDTO;
import dev.pdrolcs.gestao_consulta_medica_api.dto.MedicoResponseDTO;
import dev.pdrolcs.gestao_consulta_medica_api.services.MedicoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/medico")
public class MedicoController {

    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @GetMapping
    public ResponseEntity<List<MedicoResponseDTO>> listarMedicos() {
        return ResponseEntity.ok(medicoService.listarMedicos());
    }

    @PostMapping
    public ResponseEntity<MedicoResponseDTO> criarMedico(@Valid @RequestBody MedicoRequestDTO medicoRequest) {
        var medico = medicoService.criarMedico(medicoRequest);
        var uri = URI.create("/medico/" + medico.id());
        return ResponseEntity.created(uri).body(medico);
    }
}
