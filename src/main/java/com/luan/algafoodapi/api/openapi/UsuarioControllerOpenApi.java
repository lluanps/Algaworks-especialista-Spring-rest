package com.luan.algafoodapi.api.openapi;

import org.springframework.hateoas.CollectionModel;

import com.luan.algafoodapi.api.assembler.UsuarioComSenhaInput;
import com.luan.algafoodapi.api.exceptionhandler.ApiError;
import com.luan.algafoodapi.api.model.UsuarioDTO;
import com.luan.algafoodapi.api.model.input.SenhaInput;
import com.luan.algafoodapi.api.model.input.UsuarioInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Usuário")
public interface UsuarioControllerOpenApi {

	@ApiOperation("Lista os usuários")
	public CollectionModel<UsuarioDTO> listar();
	
	@ApiOperation("busca um usuário por id")
    @ApiResponses({
        @ApiResponse(code = 400, message = "ID do usuário inválido", response = ApiError.class),
        @ApiResponse(code = 404, message = "Usuário não encontrado", response = ApiError.class)
    })
	public UsuarioDTO buscarPorId(@ApiParam(value = "Id de um usuário", example = "1") Long usuarioId);
	
	@ApiOperation("Cadastra um novo usuário")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Usuário cadastrado"),
    })
	public UsuarioDTO adicionar(@ApiParam(name = "corpo", value = "Representação de um novo usuário") UsuarioComSenhaInput usuarioComSenhaInput);
	
	@ApiOperation("Atualiza um usuário")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Usuário atualizado"),
        @ApiResponse(code = 404, message = "Usuário não encontrado", response = ApiError.class)
    })
	public UsuarioDTO atualizar(@ApiParam(value = "Id de um usuário", example = "1") Long usuarioId, 
            @ApiParam(name = "corpo", value = "Representação de um usuário com os novos dados") UsuarioInput usuarioInput);
	
	@ApiOperation("Atualiza a senha de um usuário")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Senha alterada com sucesso"),
        @ApiResponse(code = 404, message = "Usuário não encontrado", response = ApiError.class)
    })
	public void atualizarSenha(@ApiParam(value = "Id de um usuário", example = "1") Long usuarioId,
			@ApiParam(name = "corpo", value = "Representação de uma nova senha") SenhaInput senhaInput);
	
}
