package com.saas.rtdias3d.services;

import com.saas.rtdias3d.dto.PecaRequestDTO;
import com.saas.rtdias3d.dto.PecaResponseDTO;
import com.saas.rtdias3d.entitys.PecaEntity;
import com.saas.rtdias3d.entitys.UsuarioEntity;
import com.saas.rtdias3d.repositories.PecaRepository;
import com.saas.rtdias3d.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PecaService {

    @Autowired
    private PecaRepository pecaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public PecaResponseDTO criar(PecaRequestDTO dto) {
        UsuarioEntity usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));

        PecaEntity peca = new PecaEntity();
        peca.setNome(dto.nome());
        peca.setPeso(dto.peso());
        peca.setAltura(dto.altura());
        peca.setLargura(dto.largura());
        peca.setComprimento(dto.comprimento());
        peca.setUsuario(usuario);

        PecaEntity salva = pecaRepository.save(peca);
        return toResponseDTO(salva);
    }

    public List<PecaResponseDTO> listarPorUsuario(int usuarioId) {
        return pecaRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public PecaResponseDTO buscarPorId(int id) {
        PecaEntity peca = pecaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Peça não encontrada."));
        return toResponseDTO(peca);
    }

    public PecaResponseDTO atualizar(int id, PecaRequestDTO dto) {
        PecaEntity peca = pecaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Peça não encontrada."));

        peca.setNome(dto.nome());
        peca.setPeso(dto.peso());
        peca.setAltura(dto.altura());
        peca.setLargura(dto.largura());
        peca.setComprimento(dto.comprimento());

        PecaEntity atualizada = pecaRepository.save(peca);
        return toResponseDTO(atualizada);
    }

    public void deletar(int id) {
        if (!pecaRepository.existsById(id)) {
            throw new IllegalArgumentException("Peça não encontrada.");
        }
        pecaRepository.deleteById(id);
    }

    private PecaResponseDTO toResponseDTO(PecaEntity entity) {
        return new PecaResponseDTO(
                entity.getId(),
                entity.getNome(),
                entity.getPeso(),
                entity.getAltura(),
                entity.getLargura(),
                entity.getComprimento()
        );
    }
}