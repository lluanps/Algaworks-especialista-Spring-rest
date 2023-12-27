package com.luan.algafoodapi.api.exceptionhandler;

import java.time.LocalDateTime;

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
	public ResponseEntity<?> tratarEntidadeNaoEncontradaException(EntidadeNaoEncontradaException e, WebRequest request) {
		return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> tratarNegocioException(NegocioException e, WebRequest request) {
		return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<?> tratarEntidadeEmUsoException(EntidadeEmUsoException e, WebRequest request) {
		return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		if (body == null) {
			body = ApiError.builder()
					.dataHora(LocalDateTime.now())
					.msg(status.getReasonPhrase())//getReasonPhrase() retorna a descrição do status
					.build();
		} else if (body instanceof String) {
			body = ApiError.builder()
					.dataHora(LocalDateTime.now())
					.msg((String) body)
					.build();
		}
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
}
