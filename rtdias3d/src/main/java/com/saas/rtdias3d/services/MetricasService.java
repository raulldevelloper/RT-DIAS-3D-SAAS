package com.saas.rtdias3d.services;

import com.saas.rtdias3d.dto.MetricasResponseDTO;
import com.saas.rtdias3d.repositories.OrcamentoRepository;
import com.saas.rtdias3d.repositories.PecaRepository;
import com.saas.rtdias3d.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MetricasService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PecaRepository pecaRepository;

    @Autowired
    private OrcamentoRepository orcamentoRepository;

    public MetricasResponseDTO calcular() {
        LocalDateTime seteDiasAtras = LocalDateTime.now().minusDays(7);

        return new MetricasResponseDTO(
                usuarioRepository.count(),
                usuarioRepository.countByUltimoLoginIsNotNull(),
                pecaRepository.countUsuariosDistintosComPeca(),
                orcamentoRepository.countUsuariosDistintosComOrcamento(),
                usuarioRepository.countByUltimoLoginAfter(seteDiasAtras)
        );
    }
}
