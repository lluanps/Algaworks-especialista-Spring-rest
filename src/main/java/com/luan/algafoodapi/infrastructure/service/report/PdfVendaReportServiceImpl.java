package com.luan.algafoodapi.infrastructure.service.report;

import org.springframework.stereotype.Service;

import com.luan.algafoodapi.domain.model.filter.VendaDiariaFilter;
import com.luan.algafoodapi.domain.service.VendaReportService;

@Service
public class PdfVendaReportServiceImpl implements VendaReportService {

	@Override
	public byte[] emitirVendasDiarias(VendaDiariaFilter filter) {
		return null;
	}

}
