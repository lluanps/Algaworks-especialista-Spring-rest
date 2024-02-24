package com.luan.algafoodapi.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luan.algafoodapi.api.openapi.EstatisticaControllerOpenApi;
import com.luan.algafoodapi.domain.model.VendaDiaria;
import com.luan.algafoodapi.domain.model.filter.VendaDiariaFilter;
import com.luan.algafoodapi.domain.service.VendaQueryService;
import com.luan.algafoodapi.domain.service.VendaReportService;

@RestController
@RequestMapping("/estatisticas")
public class EstatisticaController implements EstatisticaControllerOpenApi {

	@Autowired
	private VendaQueryService vendaQueryService;
	
	@Autowired
	private VendaReportService vendaReportService;
	
	@GetMapping(value = "/vendas-diarias", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro) {
		return vendaQueryService.consultarVendasDiarias(filtro);
	}
	
	@GetMapping(value = "/vendas-diarias", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> consultarVendasDiariasPdf(VendaDiariaFilter filtro) {
		byte[] bytesPdf = vendaReportService.emitirVendasDiarias(filtro);
		
		var headers = new HttpHeaders();
		
//		conteudo retornado ao cliente deve ser baixado
		headers.add(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=relatorio-vendas-diarias.pdf");
		
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_PDF)
				.headers(headers)
				.body(bytesPdf);
	}
	
}
