package com.saas.rtdias3d.services;

import com.saas.rtdias3d.dto.FilamentoRequestDTO;
import com.saas.rtdias3d.dto.FilamentoResponseDTO;
import com.saas.rtdias3d.entitys.FilamentoEntity;
import com.saas.rtdias3d.repositories.FilamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class FilamentoService {

    @Autowired
    private FilamentoRepository filamentoRepository;

    public FilamentoResponseDTO criar(FilamentoRequestDTO dto) {
        FilamentoEntity filamento = new FilamentoEntity();
        filamento.setTipo(dto.tipo());
        filamento.setGramasCompradas(dto.gramasCompradas());
        filamento.setPrecoTotalCompra(dto.precoTotalCompra());
        filamento.setDataCompra(dto.dataCompra());

        FilamentoEntity salvo = filamentoRepository.save(filamento);
        return toResponseDTO(salvo);
    }

    public List<FilamentoResponseDTO> listarTodos() {
        return filamentoRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public FilamentoResponseDTO buscarPorId(int id) {
        FilamentoEntity filamento = filamentoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Filamento não encontrado."));
        return toResponseDTO(filamento);
    }

    public void deletar(int id) {
        if (!filamentoRepository.existsById(id)) {
            throw new IllegalArgumentException("Filamento não encontrado.");
        }
        filamentoRepository.deleteById(id);
    }

    /**
     * Calcula o preço médio por 100g com base na última compra registrada
     * daquele tipo de filamento. Usado pelo OrcamentoService para não
     * depender de digitação manual do preço a cada orçamento.
     */
    public BigDecimal calcularPrecoPor100g(FilamentoEntity filamento) {
        return filamento.getPrecoTotalCompra()
                .divide(filamento.getGramasCompradas(), 4, java.math.RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100));
    }

    private FilamentoResponseDTO toResponseDTO(FilamentoEntity entity) {
        return new FilamentoResponseDTO(
                entity.getId(),
                entity.getTipo(),
                entity.getGramasCompradas(),
                entity.getPrecoTotalCompra(),
                entity.getDataCompra()
        );
    }
}