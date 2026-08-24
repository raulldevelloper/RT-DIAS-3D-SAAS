package com.saas.rtdias3d.services;

import com.saas.rtdias3d.dto.ConfiguracaoRequestDTO;
import com.saas.rtdias3d.dto.ConfiguracaoResponseDTO;
import com.saas.rtdias3d.entitys.ConfiguracaoEntity;
import com.saas.rtdias3d.repositories.ConfiguracaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ConfiguracaoService {

    @Autowired
    private ConfiguracaoRepository configuracaoRepository;

    public ConfiguracaoResponseDTO buscar() {
        ConfiguracaoEntity config = buscarOuCriar();
        return new ConfiguracaoResponseDTO(config.getValorAssinatura(), config.getWhatsapp());
    }

    public ConfiguracaoResponseDTO atualizar(ConfiguracaoRequestDTO dto) {
        ConfiguracaoEntity config = buscarOuCriar();
        config.setValorAssinatura(dto.valorAssinatura());
        config.setWhatsapp(dto.whatsapp());
        configuracaoRepository.save(config);
        return new ConfiguracaoResponseDTO(config.getValorAssinatura(), config.getWhatsapp());
    }

    private ConfiguracaoEntity buscarOuCriar() {
        return configuracaoRepository.findById(1)
                .orElseGet(() -> {
                    ConfiguracaoEntity nova = new ConfiguracaoEntity(1, BigDecimal.ZERO, "");
                    return configuracaoRepository.save(nova);
                });
    }
}
