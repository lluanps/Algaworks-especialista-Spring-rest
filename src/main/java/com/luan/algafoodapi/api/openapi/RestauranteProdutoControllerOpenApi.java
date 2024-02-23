package com.luan.algafoodapi.api.openapi;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.luan.algafoodapi.api.exceptionhandler.ApiError;
import com.luan.algafoodapi.api.model.ProdutoDTO;
import com.luan.algafoodapi.api.model.input.ProdutoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Restaurante - Produto")
public interface RestauranteProdutoControllerOpenApi {
	
	@ApiOperation("Lista os produtos de um restaurante")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Id do restaurante inválido", response = ApiError.class),
		@ApiResponse(code = 404, message = "Produto de restaurante não encontrado", response = ApiError.class)
	})
	public List<ProdutoDTO> listar(@ApiParam(value = "Id do restaurante", example = "1") Long restauranteId,
			@RequestParam(required = false) boolean incluirInativos);
	
	@ApiOperation("Busca um único produto de um restaurante")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Id do restaurante ou produto inválido", response = ApiError.class),
		@ApiResponse(code = 404, message = "Produto de restaurante não encontrado", response = ApiError.class)
	})
	public ProdutoDTO buscarProdutoPorId(@ApiParam(value = "Id do restaurante", example = "1") Long restauranteId,
			@ApiParam(value = "Id do produto", example = "1") Long produtoId);
	
	@ApiOperation("Cadastra um novo produto a um restaurante")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Produto cadastrado com sucesso!"),
		@ApiResponse(code = 404, message = "Restaurante não encontrado", response = ApiError.class)
	})
	public ProdutoDTO adicionar(@ApiParam(value = "Id do restaurante", example = "1") Long restauranteId,
			ProdutoInput produtoInput);
	
	@ApiOperation("Atualiza o produto de um restaurante")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Produto atualizado"),
		@ApiResponse(code = 404, message = "Produto de restaurante não encontrado", response = ApiError.class)
	})
	public ProdutoDTO atualizar(@ApiParam(value = "Id do restaurante", example = "1") Long restauranteId,
			@ApiParam(value = "Id do produto", example = "1") Long produtoId,
			ProdutoInput produtoInput);

}
