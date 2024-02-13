package com.luan.algafoodapi.infrastructure.service.report;

import java.util.HashMap;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luan.algafoodapi.domain.model.filter.VendaDiariaFilter;
import com.luan.algafoodapi.domain.service.VendaQueryService;
import com.luan.algafoodapi.domain.service.VendaReportService;
import com.luan.algafoodapi.infrastructure.service.report.exception.ReportException;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class PdfVendaReportServiceImpl implements VendaReportService {

	@Autowired
	private VendaQueryService vendaQueryService;

	@Override
	public byte[] emitirVendasDiarias(VendaDiariaFilter filter) {
		try {
			var inputStream = this.getClass().getResourceAsStream("/relatorios/vendas-diarias.jasper");

			var parametros = new HashMap<String, Object>();
			parametros.put("REPORT_LOCALE", new Locale("pt", "br"));

			var vendasDiarias = vendaQueryService.consultarVendasDiarias(filter);
			var dataSource = new JRBeanCollectionDataSource(vendasDiarias);

			var jasperPrint = JasperFillManager.fillReport(inputStream, parametros, dataSource);

			return JasperExportManager.exportReportToPdf(jasperPrint);
		} catch (JRException e) {
			throw new ReportException("Não foi possivel emitir relatório de vendas diárias", e);
		}
	}

}
