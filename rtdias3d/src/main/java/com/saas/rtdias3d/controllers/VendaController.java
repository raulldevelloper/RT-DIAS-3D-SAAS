package com.saas.rtdias3d.controllers;

import com.saas.rtdias3d.dto.VendaRequestDTO;
import com.saas.rtdias3d.dto.VendaResponseDTO;
import com.saas.rtdias3d.services.VendaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @PostMapping
    public ResponseEntity<VendaResponseDTO> criar(@Valid @RequestBody VendaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vendaService.criar(dto));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<VendaResponseDTO>> listarPorUsuario(@PathVariable int usuarioId) {
        return ResponseEntity.ok(vendaService.listarPorUsuario(usuarioId));
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<VendaResponseDTO>> listarPorCliente(@PathVariable int clienteId) {
        return ResponseEntity.ok(vendaService.listarPorCliente(clienteId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendaResponseDTO> buscarPorId(@PathVariable int id) {
        return ResponseEntity.ok(vendaService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        vendaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
