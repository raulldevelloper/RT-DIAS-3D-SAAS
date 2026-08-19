package com.saas.rtdias3d.controllers;

import com.saas.rtdias3d.dto.FilamentoRequestDTO;
import com.saas.rtdias3d.dto.FilamentoResponseDTO;
import com.saas.rtdias3d.services.FilamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filamentos")
public class FilamentoController {

    @Autowired
    private FilamentoService filamentoService;

    @PostMapping
    public ResponseEntity<FilamentoResponseDTO> criar(@Valid @RequestBody FilamentoRequestDTO dto) {
        FilamentoResponseDTO criado = filamentoService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @GetMapping
    public ResponseEntity<List<FilamentoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(filamentoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilamentoResponseDTO> buscarPorId(@PathVariable int id) {
        return ResponseEntity.ok(filamentoService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        filamentoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
