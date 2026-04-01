package dev.pdrolcs.gestao_consulta_medica_api.controllers;

import dev.pdrolcs.gestao_consulta_medica_api.dto.PacienteRequestDTO;
import dev.pdrolcs.gestao_consulta_medica_api.dto.PacienteResponseDTO;
import dev.pdrolcs.gestao_consulta_medica_api.services.PacienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping
    public ResponseEntity<List<PacienteResponseDTO>> listarPacientes() {
        return ResponseEntity.ok(pacienteService.listarPacientes());
    }

    @PostMapping
    public ResponseEntity<PacienteResponseDTO> cadastrarPaciente(@Valid @RequestBody PacienteRequestDTO pacienteRequest) {
        var paciente = pacienteService.criarPaciente(pacienteRequest);
        var uri = URI.create("/pacientes/" + paciente.id());
        return ResponseEntity.created(uri).body(paciente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> buscarPorId(@RequestParam Long id) {
        return ResponseEntity.ok(pacienteService.buscarPorId(id));
    }
}
