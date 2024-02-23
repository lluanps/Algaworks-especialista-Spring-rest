package com.luan.algafoodapi.api.openapi;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

import com.luan.algafoodapi.api.exceptionhandler.ApiError;
import com.luan.algafoodapi.api.model.FormaPagamentoDTO;
import com.luan.algafoodapi.api.model.input.FormaPagamentoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Forma Pagamento")
public interface FormaPagamentoControllerOpenApi {
	
	@ApiOperation("Lista as formas de pagamento")
	public ResponseEntity<List<FormaPagamentoDTO>> listar(ServletWebRequest request);

	@ApiOperation("Busca forma de pagamento pelo Id")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Id da forma de pagamento inválido", response = ApiError.class),
		@ApiResponse(code = 404, message = "Forma de pagamento não encontrada", response = ApiError.class)
	})
	public ResponseEntity<FormaPagamentoDTO> buscarPorId(Long formaPagamentoId, ServletWebRequest request);

	@ApiOperation("Cadastra uma nova forma de pagamento")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Forma de pagamento cadastrada")
	})
	public FormaPagamentoDTO adicionar(FormaPagamentoInput formaPagamentoInput);
	
	@ApiOperation("Remove uma forma de pagamento")
	@ApiResponse(code = 204, message = "Forma de pagamento removida")
	public void excluir(Long formaPagamentoId);

}
