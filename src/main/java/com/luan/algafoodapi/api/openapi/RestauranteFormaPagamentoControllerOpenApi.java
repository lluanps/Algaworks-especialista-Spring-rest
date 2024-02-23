package com.luan.algafoodapi.api.openapi;

import java.util.List;

import com.luan.algafoodapi.api.exceptionhandler.ApiError;
import com.luan.algafoodapi.api.model.FormaPagamentoDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Restaurante - Forma pagamento")
public interface RestauranteFormaPagamentoControllerOpenApi {
	
	@ApiOperation("Lista as formas de pagamento de associadas a um restaurante")
	@ApiResponses({
		@ApiResponse(code = 404, message = "Restaurante não encontrada", response = ApiError.class)
	})
	public List<FormaPagamentoDTO> listar(@ApiParam(value = "ID do restaurante", example = "1")
			Long restauranteId);

	@ApiOperation("Desassocia uma forma de pagamento de um restaurante")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Desassociação realizada com sucesso"),
		@ApiResponse(code = 404, message = "Restaurante ou forma de pagamento não encontrada", response = ApiError.class)
	})
	public void desassociarFormaPagamento(@ApiParam(value = "ID do restaurante", example = "1") Long restauranteId, 
			@ApiParam(value = "ID do restaurante", example = "1") Long formaPagamentoId);

	@ApiOperation("Associa uma forma de pagamento a um restaurante")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Desassociação realizada com sucesso"),
		@ApiResponse(code = 404, message = "Restaurante ou forma de pagamento não encontrada", response = ApiError.class)
	})
	public void associarFormaPagamento(@ApiParam(value = "ID do restaurante", example = "1") Long restauranteId,
			@ApiParam(value = "ID do restaurante", example = "1") Long formaPagamentoId);

}
