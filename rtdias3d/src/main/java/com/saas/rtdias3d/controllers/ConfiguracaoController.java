package com.saas.rtdias3d.controllers;

import com.saas.rtdias3d.dto.ConfiguracaoRequestDTO;
import com.saas.rtdias3d.dto.ConfiguracaoResponseDTO;
import com.saas.rtdias3d.services.ConfiguracaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ConfiguracaoController {

    @Autowired
    private ConfiguracaoService configuracaoService;

    @Value("${admin.chave-secreta}")
    private String chaveSecreta;

    // Pública — a tela de cobrança precisa disso mesmo sem estar logado
    @GetMapping("/configuracao/publica")
    public ResponseEntity<ConfiguracaoResponseDTO> buscarPublica() {
        return ResponseEntity.ok(configuracaoService.buscar());
    }

    // Protegida — só você atualiza, com a chave de admin
    @PutMapping("/admin/configuracao")
    public ResponseEntity<ConfiguracaoResponseDTO> atualizar(
            @Valid @RequestBody ConfiguracaoRequestDTO dto,
            @RequestHeader("X-Admin-Key") String chaveEnviada
    ) {
        if (!chaveSecreta.equals(chaveEnviada)) {
            throw new IllegalArgumentException("Chave de administrador inválida.");
        }
        return ResponseEntity.ok(configuracaoService.atualizar(dto));
    }
}
