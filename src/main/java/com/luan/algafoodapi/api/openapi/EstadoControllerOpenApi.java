package com.luan.algafoodapi.api.openapi;

import org.springframework.hateoas.CollectionModel;

import com.luan.algafoodapi.api.exceptionhandler.ApiError;
import com.luan.algafoodapi.api.model.EstadoDTO;
import com.luan.algafoodapi.api.model.input.EstadoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Estados")
public interface EstadoControllerOpenApi {
	
	@ApiOperation("Lista os estados")
	public CollectionModel<EstadoDTO> listar();
	
	@ApiOperation("busca estado por Id")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Id do estado inválido", response = ApiError.class),
		@ApiResponse(code = 404, message = "Estado não encontrado", response = ApiError.class),
		
	})
	public EstadoDTO findEstadoById(@ApiParam(value = "Id de um estado", example = "1")
			Long estadoId);
	
	@ApiOperation("Cadastra um estado")
	@ApiResponse(code = 201, message = "Estado cadastrado com sucesso!")
	public EstadoDTO salvar(@ApiParam(name = "corpo", value = "Representação de um novo estado") 
			EstadoInput estadoInput);
	
	@ApiOperation("Atualiza o nome do estado")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Estado atualizado com sucesso!"),
		@ApiResponse(code = 404, message = "Estado não encontrado", response = ApiError.class)
	})
	public EstadoDTO atualizar(@ApiParam(name = "corpo", value = "Representação de um estado com os novos dados") 
			EstadoInput estadoInput, Long estadoId);

	@ApiOperation("Remove estado")
	@ApiResponse(code = 204, message = "Estado removido com sucesso!")
	public void excluir(@ApiParam(value = "Id de um estado", example = "1")
			Long estadoId);
}
