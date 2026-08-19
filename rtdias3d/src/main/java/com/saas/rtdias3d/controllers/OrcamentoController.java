package com.saas.rtdias3d.controllers;

import com.saas.rtdias3d.dto.OrcamentoRequestDTO;
import com.saas.rtdias3d.dto.OrcamentoResponseDTO;
import com.saas.rtdias3d.services.OrcamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orcamentos")
public class OrcamentoController {

    @Autowired
    private OrcamentoService orcamentoService;

    @PostMapping
    public ResponseEntity<OrcamentoResponseDTO> criar(@Valid @RequestBody OrcamentoRequestDTO dto) {
        OrcamentoResponseDTO criado = orcamentoService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<OrcamentoResponseDTO>> listarPorUsuario(@PathVariable int usuarioId) {
        return ResponseEntity.ok(orcamentoService.listarPorUsuario(usuarioId));
    }

    @GetMapping("/peca/{pecaId}")
    public ResponseEntity<List<OrcamentoResponseDTO>> listarPorPeca(@PathVariable int pecaId) {
        return ResponseEntity.ok(orcamentoService.listarPorPeca(pecaId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrcamentoResponseDTO> buscarPorId(@PathVariable int id) {
        return ResponseEntity.ok(orcamentoService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        orcamentoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
