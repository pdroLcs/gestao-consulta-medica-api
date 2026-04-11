package dev.pdrolcs.gestao_consulta_medica_api.controllers;

import dev.pdrolcs.gestao_consulta_medica_api.dto.PacienteRequestDTO;
import dev.pdrolcs.gestao_consulta_medica_api.dto.PacienteResponseDTO;
import dev.pdrolcs.gestao_consulta_medica_api.services.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pacientes")
@Tag(name = "Pacientes", description = "Operações relacionadas a pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping
    @Operation(summary = "Lista todos os pacientes")
    public ResponseEntity<List<PacienteResponseDTO>> listarPacientes() {
        return ResponseEntity.ok(pacienteService.listarPacientes());
    }

    @PostMapping
    @Operation(summary = "Cadastra um novo paciente")
    public ResponseEntity<PacienteResponseDTO> cadastrarPaciente(@Valid @RequestBody PacienteRequestDTO pacienteRequest) {
        var paciente = pacienteService.criarPaciente(pacienteRequest);
        var uri = URI.create("/pacientes/" + paciente.id());
        return ResponseEntity.created(uri).body(paciente);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Lista o paciente pelo id")
    public ResponseEntity<PacienteResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.buscarPorId(id));
    }
}
