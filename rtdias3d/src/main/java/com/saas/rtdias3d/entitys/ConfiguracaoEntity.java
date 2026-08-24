package com.saas.rtdias3d.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "configuracao")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConfiguracaoEntity {

    @Id
    private int id; // sempre 1 — é um registro único (singleton)

    @Column(name = "valor_assinatura", precision = 10, scale = 2, nullable = false)
    private BigDecimal valorAssinatura;

    @Column(name = "whatsapp", nullable = false)
    private String whatsapp; // formato internacional, ex: 5511999999999 (sem espaços/símbolos)
}
