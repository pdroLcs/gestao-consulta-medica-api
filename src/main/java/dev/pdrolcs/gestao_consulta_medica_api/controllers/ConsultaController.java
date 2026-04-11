package dev.pdrolcs.gestao_consulta_medica_api.controllers;

import dev.pdrolcs.gestao_consulta_medica_api.dto.ConsultaRequestDTO;
import dev.pdrolcs.gestao_consulta_medica_api.dto.ConsultaResponseDTO;
import dev.pdrolcs.gestao_consulta_medica_api.services.ConsultaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/consultas")
@Tag(name = "Consultas", description = "Endpoints para gerenciamento de consultas médicas, incluindo agendamento, listagem e cancelamento")
public class ConsultaController {

    private final ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @GetMapping
    @Operation(summary = "Listar consultas", description = "Retorna todas as consultas cadastradas no sistema")
    public ResponseEntity<List<ConsultaResponseDTO>> listarConsultas() {
        return ResponseEntity.ok(consultaService.listarConsultas());
    }

    @GetMapping("/medico/{id}")
    @Operation(summary = "Listar consultas por médico", description = "Retorna todas as consultas do médico")
    public ResponseEntity<List<ConsultaResponseDTO>> listarConsultasPorMedico(@PathVariable Long id) {
        return ResponseEntity.ok(consultaService.listarConsultaPorMedico(id));
    }

    @GetMapping("/paciente/{id}")
    @Operation(summary = "Listar consultas por paciente", description = "Retorna todas as consultas do paciente")
    public ResponseEntity<List<ConsultaResponseDTO>> listarConsultasPorPaciente(@PathVariable Long id) {
        return ResponseEntity.ok(consultaService.listarConsultaPorPaciente(id));
    }

    @PostMapping
    @Operation(summary = "Agendar uma nova consulta", description = "Cria e agenda uma nova consulta médica no sistema")
    public ResponseEntity<ConsultaResponseDTO> agendarConsulta(@Valid @RequestBody ConsultaRequestDTO consultaRequest, UriComponentsBuilder uriBuilder) {
        var consulta = consultaService.agendarConsulta(consultaRequest);
        var uri = uriBuilder.path("/consultas/{id}").buildAndExpand(consulta.id()).toUri();
        return ResponseEntity.created(uri).body(consulta);
    }

    @PatchMapping("{id}/cancelar")
    @Operation(summary = "Cancelar consulta", description = "Cancela uma consulta agendada pelo ID")
    public ResponseEntity<Void> cancelarConsulta(@PathVariable Long id) {
        consultaService.cancelarConsulta(id);
        return ResponseEntity.noContent().build();
    }

}
