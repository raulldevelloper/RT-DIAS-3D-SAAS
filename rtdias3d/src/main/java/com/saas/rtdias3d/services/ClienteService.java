package com.saas.rtdias3d.services;

import com.saas.rtdias3d.dto.ClienteRequestDTO;
import com.saas.rtdias3d.dto.ClienteResponseDTO;
import com.saas.rtdias3d.entitys.ClienteEntity;
import com.saas.rtdias3d.entitys.UsuarioEntity;
import com.saas.rtdias3d.repositories.ClienteRepository;
import com.saas.rtdias3d.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public ClienteResponseDTO criar(ClienteRequestDTO dto) {
        UsuarioEntity usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));

        ClienteEntity cliente = new ClienteEntity();
        cliente.setNome(dto.nome());
        cliente.setTelefone(dto.telefone());
        cliente.setEmail(dto.email());
        cliente.setUsuario(usuario);

        ClienteEntity salvo = clienteRepository.save(cliente);
        return toResponseDTO(salvo);
    }

    public List<ClienteResponseDTO> listarPorUsuario(int usuarioId) {
        return clienteRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public ClienteResponseDTO buscarPorId(int id) {
        ClienteEntity cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado."));
        return toResponseDTO(cliente);
    }

    public ClienteResponseDTO atualizar(int id, ClienteRequestDTO dto) {
        ClienteEntity cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado."));

        cliente.setNome(dto.nome());
        cliente.setTelefone(dto.telefone());
        cliente.setEmail(dto.email());

        ClienteEntity atualizado = clienteRepository.save(cliente);
        return toResponseDTO(atualizado);
    }

    public void deletar(int id) {
        if (!clienteRepository.existsById(id)) {
            throw new IllegalArgumentException("Cliente não encontrado.");
        }
        clienteRepository.deleteById(id);
    }

    private ClienteResponseDTO toResponseDTO(ClienteEntity entity) {
        return new ClienteResponseDTO(
                entity.getId(),
                entity.getNome(),
                entity.getTelefone(),
                entity.getEmail()
        );
    }
}
