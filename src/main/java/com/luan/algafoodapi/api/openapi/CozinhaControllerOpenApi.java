package com.luan.algafoodapi.api.openapi;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.PagedModel;

import com.luan.algafoodapi.api.exceptionhandler.ApiError;
import com.luan.algafoodapi.api.model.CozinhaDTO;
import com.luan.algafoodapi.api.model.input.CozinhaInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Cozinhas")
public interface CozinhaControllerOpenApi {
	
	@ApiOperation("Lista as cozinhas")
	public PagedModel<CozinhaDTO> listar(@PageableDefault(size = 10) Pageable pageable);
	
	@ApiOperation("Busca cozinha por Id")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Id da cozinha inválido", response = ApiError.class),
		@ApiResponse(code = 404, message = "Cozinha não encontrada", response = ApiError.class),
	})
	public CozinhaDTO findCozinhaById(Long cozinhaId);
	
	@ApiOperation("Cadastra cozinha")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Cozinha cadastrada"),
	})
	public CozinhaDTO save(@ApiParam(name = "corpo", value = "Representação de uma nova cozinha") CozinhaInput cozinhaIdInput);
	
	@ApiOperation("Atualiza cozinha")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Cozinha atualizada"),
		@ApiResponse(code = 404, message = "Id da cozinha não encontrado", response = ApiError.class),
	})
	public CozinhaDTO update(@ApiParam(name = "corpo", value = "Representação de uma cozinha com os novos dados")
		Long id, CozinhaInput cozinhaInput);

	@ApiOperation("Remove cozinha")
	@ApiResponse(code = 204, message = "Cozinha removida")
	public void delete(@ApiParam(value = "ID de uma cozinha", example = "1") Long id);

}
