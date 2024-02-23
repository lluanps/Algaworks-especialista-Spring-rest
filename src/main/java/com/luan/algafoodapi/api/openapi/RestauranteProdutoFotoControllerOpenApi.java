package com.luan.algafoodapi.api.openapi;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.multipart.MultipartFile;

import com.luan.algafoodapi.api.exceptionhandler.ApiError;
import com.luan.algafoodapi.api.model.FotoProdutoDTO;
import com.luan.algafoodapi.api.model.input.FotoProdutoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Restaurante - Foto produto")
public interface RestauranteProdutoFotoControllerOpenApi {

	@ApiOperation("Atualiza uma foto de produto do restaurante")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Foto do produto atualizada"),
		@ApiResponse(code = 404, message = "Produto de restaurante não encontrado", response = ApiError.class)
	})
	public FotoProdutoDTO atualizarFoto(Long restauranteId, Long produtoId,
			@Valid FotoProdutoInput fotoProdutoInput, 
			@ApiParam(value = "Arquivo da foto do produto (500kb, apenas JPG, PNG)", hidden = true) MultipartFile arquivo) throws IOException;

	@ApiOperation(value = "busca uma foto do produto de um restaurante", produces = "application/json, image/jpeg, image/png")
	@ApiResponses({
        @ApiResponse(code = 400, message = "Id do restaurante ou produto inválido", response = ApiError.class),
        @ApiResponse(code = 404, message = "Foto de produto não encontrada", response = ApiError.class)
	})
	public FotoProdutoDTO buscar(Long restauranteId, Long produtoId);

	@ApiOperation(value = "Busca a foto do produto de um restaurante", hidden = true)
	public ResponseEntity<?> servirFoto(Long restauranteId, Long produtoId,
			@RequestHeader(name = "accept") String acceptHeader) throws HttpMediaTypeNotAcceptableException;

	@ApiOperation("Remove a foto do produto de um restaurante")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Foto do produto excluída"),
        @ApiResponse(code = 400, message = "Id do restaurante ou produto inválido", response = ApiError.class),
        @ApiResponse(code = 404, message = "Foto de produto não encontrada", response = ApiError.class)
    })
	public void remover(Long restauranteId, Long produtoId);

}
