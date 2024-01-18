package com.luan.algafoodapi.api.assembler;

import javax.validation.constraints.NotBlank;

import com.luan.algafoodapi.api.model.input.UsuarioInput;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioComSenhaInput extends UsuarioInput {
	
	@NotBlank
	private String senha;
}
