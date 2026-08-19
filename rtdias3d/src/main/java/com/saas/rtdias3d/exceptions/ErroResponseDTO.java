package com.saas.rtdias3d.exceptions;

import java.util.Map;

public record ErroResponseDTO(
        String mensagem,
        Map<String, String> campos // null quando o erro não é de validação de campo
) {}
