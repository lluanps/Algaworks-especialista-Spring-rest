package com.luan.algafoodapi.api.openapi;

import java.util.List;

import com.luan.algafoodapi.api.exceptionhandler.ApiError;
import com.luan.algafoodapi.api.model.RestauranteDTO;
import com.luan.algafoodapi.api.model.input.RestauranteInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Restaurante")
public interface RestauranteControllerOpenApi {
	
	@ApiOperation("Lista os restaurantes")
	@ApiImplicitParams({
			@ApiImplicitParam(value = "Nome da projeção de pedidos", allowableValues = "apenas-nome",
					name = "projecao", paramType = "query", dataType = "java.lang.String")
	})
	public List<RestauranteDTO> listar();
	
    @ApiOperation(value = "Lista restaurantes", hidden = true)
    public List<RestauranteDTO> listarApenasNome();
	
	@ApiOperation("Busca o restaurante pelo Id")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Id do restaurante inválido", response = ApiError.class),
		@ApiResponse(code = 404, message = "Restaurante não encontrado", response = ApiError.class),
	})
	public RestauranteDTO buscarRestaurantePorId(@ApiParam(value = "ID de um restaurante", example = "1") 
			Long restaurantedId);
	
	@ApiOperation("Cadastra um novo restaurante")
	@ApiResponse(code = 201, message = "Novo Restaurante cadastrado")
	public RestauranteDTO save(@ApiParam(name = "corpo", value = "Representação de um novo restaurante") 
			RestauranteInput restauranteInput);
	
	@ApiOperation("Atualiza restaurante")
	@ApiResponse(code = 404, message = "Restaurante não encontrada", response = ApiError.class)
	public RestauranteDTO atualizar(@ApiParam(name = "corpo", value = "Id de um restaurante") 
			Long restauranteId, RestauranteInput restauranteInput);

	@ApiOperation("Ativa o restaurante por Id, possibilitando novos pedidos")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Restaurante ativado"),
		@ApiResponse(code = 404, message = "Restaurante não encontrado", response = ApiError.class)
	})
	public void ativar(@ApiParam(name = "corpo", value = "Id de um restaurante") 
			Long restauranteId);
	
	@ApiOperation("Desativa o restaurante por Id, impossibilitando novos pedido")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Restaurante inativado"),
		@ApiResponse(code = 404, message = "Restaurante não encontrado", response = ApiError.class)
	})
	public void inativar(@ApiParam(name = "corpo", value = "Id de um restaurante") 
			Long restauranteId);

	@ApiOperation("Ativa múltiplos restaurantes")
    @ApiResponse(code = 204, message = "Restaurantes ativados com sucesso")
	public void ativarMultiplosRestaurantes(@ApiParam(name = "corpo", value = "Ids de um restaurante")
			List<Long> restaurantesIds);
	
	@ApiOperation("Desativa múltiplos restaurantes")
    @ApiResponse(code = 204, message = "Restaurantes desativado com sucesso")
	public void inativarMultiplosRestuarantes(@ApiParam(name = "corpo", value = "Ids de um restaurante")
			List<Long> restaurantesIds);
	
	@ApiOperation("Abre um restaurante")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Restaurante aberto com sucesso"),
        @ApiResponse(code = 404, message = "Restaurante não encontrado", response = ApiError.class)
    })
	public void abrir(@ApiParam(name = "corpo", value = "Id de um restaurante") 
			Long restauranteId);
	
	@ApiOperation("Fecha um restaurante")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Restaurante aberto com sucesso"),
        @ApiResponse(code = 404, message = "Restaurante não encontrado", response = ApiError.class)
    })
	public void fechar(@ApiParam(value = "ID de um restaurante", example = "1") 
			Long restauranteId);
	
}