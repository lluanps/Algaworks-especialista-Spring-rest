package com.luan.algafoodapi.domain.repository.filter;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoFilter {

	private Long clienteId;
	private Long restaruanteId;
	private OffsetDateTime dataCriacaoInicio;
	private OffsetDateTime dataCriacaoFim;
	
	
	
}
