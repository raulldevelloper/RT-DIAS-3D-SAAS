package com.saas.rtdias3d.services;

import com.saas.rtdias3d.dto.VendaRequestDTO;
import com.saas.rtdias3d.dto.VendaResponseDTO;
import com.saas.rtdias3d.entitys.*;
import com.saas.rtdias3d.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private OrcamentoRepository orcamentoRepository;

    @Autowired
    private PecaRepository pecaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public VendaResponseDTO criar(VendaRequestDTO dto) {
        UsuarioEntity usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));

        ClienteEntity cliente = clienteRepository.findById(dto.clienteId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado."));

        PecaEntity peca;
        OrcamentoEntity orcamento = null;

        if (dto.orcamentoId() != null) {
            // Venda vinculada a um orçamento — a peça vem do orçamento, não do request
            orcamento = orcamentoRepository.findById(dto.orcamentoId())
                    .orElseThrow(() -> new IllegalArgumentException("Orçamento não encontrado."));
            peca = orcamento.getPeca();
        } else {
            // Venda direta — peça é obrigatória no request
            if (dto.pecaId() == null) {
                throw new IllegalArgumentException("Informe a peça, já que a venda não está vinculada a um orçamento.");
            }
            peca = pecaRepository.findById(dto.pecaId())
                    .orElseThrow(() -> new IllegalArgumentException("Peça não encontrada."));
        }

        BigDecimal valorTotal = dto.valorUnitario()
                .multiply(BigDecimal.valueOf(dto.quantidade()));

        VendaEntity venda = new VendaEntity();
        venda.setOrcamento(orcamento); // fica null se venda direta
        venda.setPeca(peca);
        venda.setUsuario(usuario);
        venda.setCliente(cliente);
        venda.setQuantidade(dto.quantidade());
        venda.setValorUnitario(dto.valorUnitario());
        venda.setValorTotal(valorTotal);
        venda.setDataVenda(LocalDateTime.now());

        VendaEntity salva = vendaRepository.save(venda);
        return toResponseDTO(salva);
    }

    public List<VendaResponseDTO> listarPorUsuario(int usuarioId) {
        return vendaRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public List<VendaResponseDTO> listarPorCliente(int clienteId) {
        return vendaRepository.findByClienteId(clienteId)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public VendaResponseDTO buscarPorId(int id) {
        VendaEntity venda = vendaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Venda não encontrada."));
        return toResponseDTO(venda);
    }

    public void deletar(int id) {
        if (!vendaRepository.existsById(id)) {
            throw new IllegalArgumentException("Venda não encontrada.");
        }
        vendaRepository.deleteById(id);
    }

    private VendaResponseDTO toResponseDTO(VendaEntity entity) {
        return new VendaResponseDTO(
                entity.getId(),
                entity.getPeca().getNome(),
                entity.getCliente().getNome(),
                entity.getQuantidade(),
                entity.getValorUnitario(),
                entity.getValorTotal(),
                entity.getDataVenda()
        );
    }
}
