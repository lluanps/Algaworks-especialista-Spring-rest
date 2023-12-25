package com.luan.algafoodapi.api.exceptionhandler;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
//@Setter
@Builder
public class ApiError {
	
	private LocalDateTime dataHora;
	private String msg;

}
