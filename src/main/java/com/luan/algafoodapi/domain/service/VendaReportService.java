package com.luan.algafoodapi.domain.service;

import com.luan.algafoodapi.domain.model.filter.VendaDiariaFilter;

public interface VendaReportService {

	byte[] emitirVendasDiarias(VendaDiariaFilter filter);
	
}
