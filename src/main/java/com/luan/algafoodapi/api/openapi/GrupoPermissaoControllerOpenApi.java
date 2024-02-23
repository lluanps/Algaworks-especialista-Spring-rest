package com.luan.algafoodapi.api.openapi;

import java.util.List;

import com.luan.algafoodapi.api.exceptionhandler.ApiError;
import com.luan.algafoodapi.api.model.PermissaoDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Grupo permissão")
public interface GrupoPermissaoControllerOpenApi {
	
	@ApiOperation("Lista as permissões associadas a um grupo")
    @ApiResponses({
        @ApiResponse(code = 400, message = "Id do grupo inválido", response = ApiError.class),
        @ApiResponse(code = 404, message = "Grupo não encontrado", response = ApiError.class)
    })
	public List<PermissaoDTO> listar(@ApiParam(value = "Id do grupo", example = "1") Long grupoId);
	
	@ApiOperation("Associa as permissões com o grupo")
    @ApiResponses({
        @ApiResponse(code = 400, message = "Id do grupo inválido", response = ApiError.class),
        @ApiResponse(code = 404, message = "Grupo não encontrado", response = ApiError.class)
    })
	public void associar(@ApiParam(value = "Id do grupo", example = "1") Long grupoId, 
			@ApiParam(value = "Id da permissão", example = "1") Long permissaoId);

	@ApiOperation("Desassocia as permissões com o grupo")
    @ApiResponses({
        @ApiResponse(code = 400, message = "Id do grupo inválido", response = ApiError.class),
        @ApiResponse(code = 404, message = "Grupo não encontrado", response = ApiError.class)
    })
	public void desassociarGrupo(@ApiParam(value = "Id do grupo", example = "1") Long grupoId, 
			@ApiParam(value = "Id da permissão", example = "1") Long permissaoId);

}
