package com.luan.algafoodapi.api.openapi;

import org.springframework.web.bind.annotation.PathVariable;

import com.luan.algafoodapi.api.exceptionhandler.ApiError;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Fluxo de Pedido")
public interface FluxoPedidoControllerOpenApi {
	
	@ApiOperation("Pedido confirmado")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Pedido confirmado"),
		@ApiResponse(code = 404, message = "Id do pedido não encontrado", response = ApiError.class)
	})
	public void confirmar(@ApiParam(value = "Id do pedido", example = "1")
			Long pedidoId);
	
	@ApiOperation("Pedido entregue")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Pedido entregue"),
		@ApiResponse(code = 404, message = "Id do pedido não encontrado", response = ApiError.class)
	})
	public void entregar(@ApiParam(value = "Id do pedido", example = "1")
			Long pedidoId);
	
	@ApiOperation("Pedido cancelado")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Pedido cancelado"),
		@ApiResponse(code = 404, message = "Id do pedido não encontrado", response = ApiError.class)
	})
	public void cancelar(@ApiParam(value = "Id do pedido", example = "1")
			Long pedidoId);
}
