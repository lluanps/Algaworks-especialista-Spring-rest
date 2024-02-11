package com.luan.algafoodapi.domain.service;

import java.util.List;

import com.luan.algafoodapi.domain.model.VendaDiaria;
import com.luan.algafoodapi.domain.model.filter.VendaDiariaFilter;

public interface VendaQueryService  {

	List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro);

	List<VendaDiaria> consultarVendasMensais(VendaDiariaFilter filtro);

	List<VendaDiaria> consultarVendasAnuais(VendaDiariaFilter filtro);

}
