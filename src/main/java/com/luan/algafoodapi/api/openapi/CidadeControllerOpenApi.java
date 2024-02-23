package com.luan.algafoodapi.api.openapi;

import org.springframework.hateoas.CollectionModel;

import com.luan.algafoodapi.api.exceptionhandler.ApiError;
import com.luan.algafoodapi.api.model.CidadeDTO;
import com.luan.algafoodapi.api.model.input.CidadeInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

//SpringFox (3.0.0) foi descontinuada.
@Api(tags = "Cidades")
public interface CidadeControllerOpenApi {

	@ApiOperation("Lista as cidades")
	public CollectionModel<CidadeDTO> listar();

	@ApiOperation("Busca uma cidade po Id")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Id da cidade inválido", response = ApiError.class),
		@ApiResponse(code = 404, message = "Cidade não encontrada", response = ApiError.class),
	})
	public CidadeDTO cidadeById(@ApiParam(value = "Id de uma cidade", example = "1") Long cidadeId);
	
	@ApiOperation("Cadastra uma cidade")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Cidade cadastrada")
	})
	public CidadeDTO salvar(@ApiParam(name = "corpo", value = "Representação de uma nova cidade") CidadeInput cidadeInput);
	
	@ApiOperation("Atualiza uma cidade por Id")
	@ApiResponses({
		@ApiResponse(code = 404, message = "Cidade não encontrada", response = ApiError.class),
	})
	public CidadeDTO atualizar(@ApiParam("Id de uma cidade") Long cidadeId,
			@ApiParam(name = "corpo", value = "Representação de uma cidade como novos dados a serem atualizados", example = "1") CidadeInput cidadeInput);
	
	@ApiOperation("Exclui uma cidade por Id")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Cidade excluída", response = ApiError.class),
		@ApiResponse(code = 404, message = "Cidade não encontrada", response = ApiError.class),
	})
	public void remover(@ApiParam(value = "Id de uma cidade", example = "1") Long cidadeId);
	
}
