package com.luan.algafoodapi.api.openapi;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.luan.algafoodapi.api.exceptionhandler.ApiError;
import com.luan.algafoodapi.api.model.PedidoDTO;
import com.luan.algafoodapi.api.model.input.PedidoInput;
import com.luan.algafoodapi.domain.filter.PedidoFilter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Pedido")
public interface PedidoControllerOpenApi {
	
	@ApiOperation("Lista os pedidos")
	public Page<PedidoDTO> listar(Pageable pageable);
	
	@ApiOperation("Pesquisa os pedidos")
	public Page<PedidoDTO> pesquisar(PedidoFilter filtro, @PageableDefault(size = 10) Pageable pageable);
	
	@ApiOperation("Busca pedidos por Id")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Id do pedido inválido", response = ApiError.class),
		@ApiResponse(code = 404, message = "Pedido não encontrado", response = ApiError.class)
	})
	public PedidoDTO buscarPedidoPorId(@PathVariable Long pedidoId);
	
	@ApiOperation("Cadastra novo pedido")
	@ApiResponse(code = 201, message = "Pedido cadastrado")
	public PedidoDTO adicionar(@Valid @RequestBody PedidoInput pedidoInput);

}
