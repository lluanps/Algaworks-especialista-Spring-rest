package com.luan.algafoodapi.api.openapi;

import java.util.List;

import com.luan.algafoodapi.domain.model.VendaDiaria;
import com.luan.algafoodapi.domain.model.filter.VendaDiariaFilter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Estatísticas")
public interface EstatisticaControllerOpenApi {
	
    @ApiOperation("Consulta estatísticas de vendas diárias")
	public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro);

}
