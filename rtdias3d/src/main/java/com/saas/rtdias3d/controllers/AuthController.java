package com.saas.rtdias3d.controllers;

import com.saas.rtdias3d.dto.LoginRequestDTO;
import com.saas.rtdias3d.dto.LoginResponseDTO;
import com.saas.rtdias3d.entitys.UsuarioEntity;
import com.saas.rtdias3d.repositories.UsuarioRepository;
import com.saas.rtdias3d.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO dto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.email(), dto.senha())
            );
        } catch (org.springframework.security.authentication.DisabledException e) {
            throw new IllegalArgumentException("Sua conta está desativada. Entre em contato com o suporte.");
        } catch (org.springframework.security.authentication.AccountExpiredException e) {
            throw new IllegalArgumentException("Sua assinatura expirou. Entre em contato para renovar.");
        } catch (BadCredentialsException e) {
            throw new IllegalArgumentException("Email ou senha inválidos.");
        }

        UsuarioEntity usuario = usuarioRepository.findByEmail(dto.email())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));

        String token = jwtService.gerarToken(usuario);

        return ResponseEntity.ok(new LoginResponseDTO(token, usuario.getNome(), usuario.getId()));
    }
}
