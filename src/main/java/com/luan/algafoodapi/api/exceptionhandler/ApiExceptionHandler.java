package com.luan.algafoodapi.api.exceptionhandler;

import org.springframework.data.projection.EntityProjection.ProjectionType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.luan.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.luan.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.luan.algafoodapi.domain.exception.NegocioException;

@ControllerAdvice//dentro desse componente é possivel adicionar exceptions handlers
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException e,
			WebRequest request) {
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		ApiErrorType apiErrorType = ApiErrorType.ENTIDADE_NAO_ENCONTRADA;
		String detail = e.getMessage();
		
		ApiError apiError = createApiErrorBuilder(status, apiErrorType, detail).build();
		
//		ApiError apiError = ApiError.builder()
//				.status(status.value())
//				.type("https://algafood.com.br/entidade-nao-encontrada")
//				.title("Entidade não encontrada")
//				.detail(detail)
//				.build();
		
		return handleExceptionInternal(e, apiError, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> handleNegocioException(NegocioException e, WebRequest request) {
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		ApiErrorType apiErrorType = ApiErrorType.NEGOCIO_EXCEPETION;
		String detail = e.getMessage();
		
		ApiError apiError = createApiErrorBuilder(status, apiErrorType, detail).build();
		
		return handleExceptionInternal(e, apiError, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<?> handleEntidadeEmUsoException(EntidadeEmUsoException e, WebRequest request) {
		
		HttpStatus status = HttpStatus.CONFLICT;
		ApiErrorType apiErrorType = ApiErrorType.ENTIDADE_EM_USO;
		String detail = e.getMessage();
		
		ApiError apiError = createApiErrorBuilder(status, apiErrorType, detail).build();
		
		return handleExceptionInternal(e, apiError, new HttpHeaders(), status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		if (body == null) {
			body = ApiError.builder()
					.title(status.getReasonPhrase())//getReasonPhrase() retorna a descrição do status
					.status(status.value())
					.build();
		} else if (body instanceof String) {
			body = ApiError.builder()
					.title((String) body)
					.status(status.value())
					.build();
		}
		
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	private ApiError.ApiErrorBuilder createApiErrorBuilder(HttpStatus status, ApiErrorType apiErrorType
			, String detail) {
		
		
		return ApiError.builder()
				.status(status.value())
				.type(apiErrorType.getUri())
				.title(apiErrorType.getTitle())
				.detail(detail);
	}
	
}
