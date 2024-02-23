package com.luan.algafoodapi.api.openapi;

import java.util.List;

import com.luan.algafoodapi.api.exceptionhandler.ApiError;
import com.luan.algafoodapi.api.model.GrupoDTO;
import com.luan.algafoodapi.api.model.input.GrupoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Grupos")
public interface GrupoControllerOpenApi {

	@ApiOperation("Lista grupos")
	public List<GrupoDTO> listar();
	
	@ApiOperation("busca grupo por id")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Id do grupo inválido", response = ApiError.class),
		@ApiResponse(code = 404, message = "Grupo não encontrado", response = ApiError.class)
	})
	public GrupoDTO buscarPorId(Long grupoId);

	@ApiOperation("Cadastra grupo")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Grupo cadastrado")
	})
	public GrupoDTO adicionar(GrupoInput grupoInput);

	@ApiOperation("Atualiza grupo")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Grupo atualizado"),
		@ApiResponse(code = 404, message = "Id do grupo não encontrado", response = ApiError.class)
	})
	public GrupoDTO atualizar(Long grupoId, GrupoInput grupoInput);
	
	@ApiOperation("Remove grupo")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Grupo excluído"),
		@ApiResponse(code = 404, message = "Id do grupo não encontrado", response = ApiError.class)
	})
	public void remover(Long grupoId);
	
}
