package com.luan.algafoodapi.api.openapi;

import java.util.List;

import com.luan.algafoodapi.api.exceptionhandler.ApiError;
import com.luan.algafoodapi.api.model.GrupoDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Usuario - Grupo")
public interface UsuarioGrupoControlllerOpenApi {

	@ApiOperation("Lista os grupos associados ao usuário")
	@ApiResponses({
		@ApiResponse(code = 404, message = "Usuário não encontrado", response = ApiError.class)
	})
    public List<GrupoDTO> listar(@ApiParam(value = "Id do usuário", example = "1") Long usuarioId);
	
	@ApiOperation("Associação de grupo com usuário")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Associação realizada com sucesso"),
		@ApiResponse(code = 404, message = "Usuário ou grupo não encontrado", response = ApiError.class)
	})
    public void associar(@ApiParam(value = "Id do usuário", example = "1") Long usuarioId,
    		@ApiParam(value = "Id do grupo", example = "1") Long grupoId);
	
	@ApiOperation("Desassociação de grupo com usuário")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Desassociação realizada com sucesso"),
		@ApiResponse(code = 404, message = "Usuário ou grupo não encontrado", response = ApiError.class)
	})
	public void desassociar(@ApiParam(value = "Id do usuário", example = "1") Long usuarioId, 
			@ApiParam(value = "Id do grupo", example = "1") Long grupoId);
	
}
