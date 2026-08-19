package com.saas.rtdias3d.services;

import com.saas.rtdias3d.dto.UsuarioRequestDTO;
import com.saas.rtdias3d.dto.UsuarioResponseDTO;
import com.saas.rtdias3d.entitys.UsuarioEntity;
import com.saas.rtdias3d.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioResponseDTO criar(UsuarioRequestDTO dto) {
        if (usuarioRepository.existsByEmail(dto.email())) {
            throw new IllegalArgumentException("Já existe um usuário cadastrado com este email.");
        }

        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setSenha(passwordEncoder.encode(dto.senha()));
        usuario.setMargemLucroPadrao(dto.margemLucroPadrao());
        usuario.setAssinaturaAtiva(true);
        usuario.setAssinaturaExpiraEm(java.time.LocalDateTime.now().plusDays(7));

        UsuarioEntity salvo = usuarioRepository.save(usuario);
        return toResponseDTO(salvo);
    }

    public List<UsuarioResponseDTO> listarTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public UsuarioResponseDTO buscarPorId(int id) {
        UsuarioEntity usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));
        return toResponseDTO(usuario);
    }

    public UsuarioResponseDTO atualizar(int id, UsuarioRequestDTO dto) {
        UsuarioEntity usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));

        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setMargemLucroPadrao(dto.margemLucroPadrao());
        // senha normalmente tem endpoint/fluxo separado de alteração

        UsuarioEntity atualizado = usuarioRepository.save(usuario);
        return toResponseDTO(atualizado);
    }

    public void deletar(int id) {
        if (!usuarioRepository.existsById(id)) {
            throw new IllegalArgumentException("Usuário não encontrado.");
        }
        usuarioRepository.deleteById(id);
    }

    private UsuarioResponseDTO toResponseDTO(UsuarioEntity entity) {
        return new UsuarioResponseDTO(
                entity.getId(),
                entity.getNome(),
                entity.getEmail(),
                entity.getMargemLucroPadrao()
        );
    }

    public UsuarioResponseDTO renovarAssinatura(int id, int dias) {
        UsuarioEntity usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));

        // Se já expirou, conta a partir de agora. Se ainda está válida, soma em cima do que resta
        LocalDateTime base = usuario.getAssinaturaExpiraEm().isAfter(LocalDateTime.now())
                ? usuario.getAssinaturaExpiraEm()
                : LocalDateTime.now();

        usuario.setAssinaturaExpiraEm(base.plusDays(dias));
        usuario.setAssinaturaAtiva(true);

        UsuarioEntity atualizado = usuarioRepository.save(usuario);
        return toResponseDTO(atualizado);
    }

    public UsuarioResponseDTO desativar(int id) {
        UsuarioEntity usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));
        usuario.setAssinaturaAtiva(false);
        UsuarioEntity atualizado = usuarioRepository.save(usuario);
        return toResponseDTO(atualizado);
    }
}
