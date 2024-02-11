package com.luan.algafoodapi.domain.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class VendaDiaria {
	
	private String data;
	private Long totalVendas;
	private BigDecimal valorTotal;

}
