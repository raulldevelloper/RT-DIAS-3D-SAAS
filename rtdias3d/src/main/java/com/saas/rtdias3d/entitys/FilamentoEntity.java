package com.saas.rtdias3d.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "filamento")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private TipoFilamento tipo;

    @Column(name = "gramas_compradas", precision = 10, scale = 2)
    private BigDecimal gramasCompradas;

    @Column(name = "preco_total_compra", precision = 10, scale = 2)
    private BigDecimal precoTotalCompra;

    @Column(name = "data_compra")
    private LocalDate dataCompra;
}