package com.saas.rtdias3d.controllers;

import com.saas.rtdias3d.dto.PecaRequestDTO;
import com.saas.rtdias3d.dto.PecaResponseDTO;
import com.saas.rtdias3d.services.PecaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pecas")
public class PecaController {

    @Autowired
    private PecaService pecaService;

    @PostMapping
    public ResponseEntity<PecaResponseDTO> criar(@Valid @RequestBody PecaRequestDTO dto) {
        PecaResponseDTO criada = pecaService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criada);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<PecaResponseDTO>> listarPorUsuario(@PathVariable int usuarioId) {
        return ResponseEntity.ok(pecaService.listarPorUsuario(usuarioId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PecaResponseDTO> buscarPorId(@PathVariable int id) {
        return ResponseEntity.ok(pecaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PecaResponseDTO> atualizar(@PathVariable int id, @Valid @RequestBody PecaRequestDTO dto) {
        return ResponseEntity.ok(pecaService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        pecaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
