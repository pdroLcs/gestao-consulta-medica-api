package dev.pdrolcs.gestao_consulta_medica_api.controllers;

import dev.pdrolcs.gestao_consulta_medica_api.dto.MedicoResponseDTO;
import dev.pdrolcs.gestao_consulta_medica_api.services.MedicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
