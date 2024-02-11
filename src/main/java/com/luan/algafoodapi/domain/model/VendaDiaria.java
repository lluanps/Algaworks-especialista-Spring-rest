package com.luan.algafoodapi.domain.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendaDiaria {
	
	private String data;
	private Long totalVendas;
	private BigDecimal totalFaturado;
    private Integer mes;
    private Integer ano;

    public VendaDiaria(Integer mes, Long totalVendas, BigDecimal totalFaturado) {
        this.mes = mes;
        this.totalVendas = totalVendas;
        this.totalFaturado = totalFaturado;
    }
    public VendaDiaria(Long totalVendas, BigDecimal totalFaturado, Integer ano) {
        this.totalVendas = totalVendas;
        this.totalFaturado = totalFaturado;
        this.ano = ano;
    }

    public VendaDiaria(String data, Long totalVendas, BigDecimal totalFaturado) {
        this.data = data;
        this.totalVendas = totalVendas;
        this.totalFaturado = totalFaturado;
    }

}
