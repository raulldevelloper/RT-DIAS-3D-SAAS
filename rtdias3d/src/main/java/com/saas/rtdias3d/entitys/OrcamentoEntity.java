package com.saas.rtdias3d.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "orcamento")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrcamentoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "peca_id", nullable = false)
    private PecaEntity peca;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioEntity usuario;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_filamento", nullable = false)
    private TipoFilamento tipoFilamento;

    // Consumo
    @Column(name = "quantidade_gramas", precision = 10, scale = 2)
    private BigDecimal quantidadeGramas;

    @Column(name = "tempo_impressao_horas")
    private int tempoImpressaoMinutos;

    // Custos
    @Column(name = "preco_filamento_100g", precision = 10, scale = 2, nullable = false)
    private BigDecimal precoFilamento100g;

    @Column(name = "custo_material", precision = 10, scale = 2, nullable = false)
    private BigDecimal custoMaterial;

    @Column(name = "custo_energia", precision = 10, scale = 2, nullable = false)
    private BigDecimal custoEnergia;

    // Precificação
    @Column(name = "margem_lucro", precision = 5, scale = 2, nullable = false)
    private BigDecimal margemLucro;

    @Column(name = "valor_total", precision = 10, scale = 2, nullable = false)
    private BigDecimal valorTotal;


}
