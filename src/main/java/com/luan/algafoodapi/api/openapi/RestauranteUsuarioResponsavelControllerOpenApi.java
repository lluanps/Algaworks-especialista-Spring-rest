package com.luan.algafoodapi.api.openapi;

import org.springframework.hateoas.CollectionModel;

import com.luan.algafoodapi.api.exceptionhandler.ApiError;
import com.luan.algafoodapi.api.model.UsuarioDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Restaurante - Usuário Responsavel")
public interface RestauranteUsuarioResponsavelControllerOpenApi {
	
	@ApiOperation("Lista dos usuários associados ao restaurante")
	@ApiResponses({
		@ApiResponse(code = 404, message = "Restaurante ou usuário não encontrado", response = ApiError.class)
	})
	public CollectionModel<UsuarioDTO> listar(@ApiParam(value = "Id do restaurante" , example = "1") Long restauranteId);
	
	@ApiOperation("Associação de restaurante com usuário responsável")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Associação realizada com sucesso"),
		@ApiResponse(code = 404, message = "Restaurante ou usuário não encontrado", response = ApiError.class)
	})
	public void associarResponsavel(@ApiParam(value = "Id do restaurante" , example = "1") Long restauranteId,
			@ApiParam(value = "Id do usuário" , example = "1") Long usuarioId);
	
	@ApiOperation("Desassociação de restaurante com usuário responsável")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Desassociação realizada com sucesso"),
		@ApiResponse(code = 404, message = "Restaurante ou usuário não encontrado", response = ApiError.class)
	})
	public void desassociarResponsavel(@ApiParam(value = "Id do restaurante" , example = "1") Long restauranteId,
			@ApiParam(value = "Id do usuário" , example = "1") Long usuarioId);

}
