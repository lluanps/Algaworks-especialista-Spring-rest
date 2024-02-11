package com.luan.algafoodapi.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luan.algafoodapi.domain.model.VendaDiaria;
import com.luan.algafoodapi.domain.model.filter.VendaDiariaFilter;
import com.luan.algafoodapi.domain.service.VendaQueryService;

@RestController
@RequestMapping("/estatisticas")
public class EstatisticaController {

	@Autowired
	private VendaQueryService vendaQueryService;
	
	@GetMapping("/vendas-diarias")
	public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro) {
		return vendaQueryService.consultarVendasDiarias(filtro);
	}
}
