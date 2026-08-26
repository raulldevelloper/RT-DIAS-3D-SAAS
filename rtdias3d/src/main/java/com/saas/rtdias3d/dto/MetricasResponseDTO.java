package com.saas.rtdias3d.dto;

public record MetricasResponseDTO(
        long usuariosCadastrados,
        long usuariosComLogin,
        long usuariosComPecaCriada,
        long usuariosComOrcamentoCriado,
        long usuariosAtivosUltimos7Dias
) {}
