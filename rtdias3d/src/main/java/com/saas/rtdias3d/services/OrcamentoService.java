package com.saas.rtdias3d.services;

import com.saas.rtdias3d.dto.OrcamentoRequestDTO;
import com.saas.rtdias3d.dto.OrcamentoResponseDTO;
import com.saas.rtdias3d.entitys.*;
import com.saas.rtdias3d.repositories.FilamentoRepository;
import com.saas.rtdias3d.repositories.OrcamentoRepository;
import com.saas.rtdias3d.repositories.PecaRepository;
import com.saas.rtdias3d.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class OrcamentoService {

    @Autowired
    private OrcamentoRepository orcamentoRepository;

    @Autowired
    private PecaRepository pecaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private FilamentoRepository filamentoRepository;

    @Autowired
    private FilamentoService filamentoService;

    public OrcamentoResponseDTO criar(OrcamentoRequestDTO dto) {
        PecaEntity peca = pecaRepository.findById(dto.pecaId())
                .orElseThrow(() -> new IllegalArgumentException("Peça não encontrada."));

        UsuarioEntity usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));

        FilamentoEntity filamento = filamentoRepository.findByTipo(dto.tipoFilamento())
                .stream()
                .max((f1, f2) -> f1.getDataCompra().compareTo(f2.getDataCompra()))
                .orElseThrow(() -> new IllegalArgumentException(
                        "Nenhuma compra registrada para o filamento " + dto.tipoFilamento()));

        BigDecimal precoFilamento100g = filamentoService.calcularPrecoPor100g(filamento);
        BigDecimal margemLucro = usuario.getMargemLucroPadrao();

        BigDecimal custoMaterial = dto.quantidadeGramas()
                .divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP)
                .multiply(precoFilamento100g);

        BigDecimal subtotal = custoMaterial.add(dto.custoEnergia());

        BigDecimal valorTotal = subtotal.add(
                subtotal.multiply(margemLucro).divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP)
        ).setScale(2, RoundingMode.HALF_UP);

        OrcamentoEntity orcamento = new OrcamentoEntity();
        orcamento.setPeca(peca);
        orcamento.setUsuario(usuario);
        orcamento.setTipoFilamento(dto.tipoFilamento());
        orcamento.setQuantidadeGramas(dto.quantidadeGramas());
        orcamento.setTempoImpressaoMinutos(dto.tempoImpressaoMinutos());
        orcamento.setPrecoFilamento100g(precoFilamento100g);
        orcamento.setCustoMaterial(custoMaterial.setScale(2, RoundingMode.HALF_UP));
        orcamento.setCustoEnergia(dto.custoEnergia());
        orcamento.setMargemLucro(margemLucro);
        orcamento.setValorTotal(valorTotal);

        OrcamentoEntity salvo = orcamentoRepository.save(orcamento);
        return toResponseDTO(salvo);
    }

    public List<OrcamentoResponseDTO> listarPorUsuario(int usuarioId) {
        return orcamentoRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public List<OrcamentoResponseDTO> listarPorPeca(int pecaId) {
        return orcamentoRepository.findByPecaId(pecaId)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public OrcamentoResponseDTO buscarPorId(int id) {
        OrcamentoEntity orcamento = orcamentoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Orçamento não encontrado."));
        return toResponseDTO(orcamento);
    }

    public void deletar(int id) {
        if (!orcamentoRepository.existsById(id)) {
            throw new IllegalArgumentException("Orçamento não encontrado.");
        }
        orcamentoRepository.deleteById(id);
    }

    private OrcamentoResponseDTO toResponseDTO(OrcamentoEntity entity) {
        return new OrcamentoResponseDTO(
                entity.getId(),
                entity.getPeca().getNome(),
                entity.getTipoFilamento(),
                entity.getQuantidadeGramas(),
                entity.getTempoImpressaoMinutos(),
                entity.getPrecoFilamento100g(),
                entity.getCustoMaterial(),
                entity.getCustoEnergia(),
                entity.getMargemLucro(),
                entity.getValorTotal()
        );
    }
}