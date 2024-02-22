package com.luan.algafoodapi.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@JsonInclude(Include.NON_NULL)//Não retorna valor null
@Getter
//@Setter
@Builder
public class ApiError {
	
	@ApiModelProperty(example = "400")
	private Integer status;
	
	@ApiModelProperty(example = "https://algafood.com.brDados inválidos")
	private String type;
	
	@ApiModelProperty(example = "/dados-invalidos")
	private String title;
	
	@ApiModelProperty(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente")
	private String detail;
	
	@ApiModelProperty(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente")
	private String userMessage;

	@ApiModelProperty(example = "2024-02-22T00:44:50.1116078Z")
	private OffsetDateTime timestamp;
	
	@ApiModelProperty(example = "Objetos ou campos que geraram o erro")
	private List<Object> objects;
	
	@Getter
	@Builder
	public static class Object {
		
		@ApiModelProperty(example = "preço")
		private String name;
		
		@ApiModelProperty(example = "O preço é obrigatório")
		private String userMessage;
		
	}

}
