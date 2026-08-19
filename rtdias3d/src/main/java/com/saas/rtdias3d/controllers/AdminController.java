package com.saas.rtdias3d.controllers;

import com.saas.rtdias3d.dto.UsuarioResponseDTO;
import com.saas.rtdias3d.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UsuarioService usuarioService;

    @Value("${admin.chave-secreta}")
    private String chaveSecreta;

    @PostMapping("/usuarios/{id}/renovar")
    public ResponseEntity<UsuarioResponseDTO> renovar(
            @PathVariable int id,
            @RequestParam int dias,
            @RequestHeader("X-Admin-Key") String chaveEnviada
    ) {
        if (!chaveSecreta.equals(chaveEnviada)) {
            throw new IllegalArgumentException("Chave de administrador inválida.");
        }
        return ResponseEntity.ok(usuarioService.renovarAssinatura(id, dias));
    }

    @PostMapping("/usuarios/{id}/desativar")
    public ResponseEntity<UsuarioResponseDTO> desativar(
            @PathVariable int id,
            @RequestHeader("X-Admin-Key") String chaveEnviada
    ) {
        if (!chaveSecreta.equals(chaveEnviada)) {
            throw new IllegalArgumentException("Chave de administrador inválida.");
        }
        return ResponseEntity.ok(usuarioService.desativar(id));
    }
}
