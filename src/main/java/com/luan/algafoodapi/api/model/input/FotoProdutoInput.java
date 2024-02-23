package com.luan.algafoodapi.api.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import com.luan.algafoodapi.core.validation.FileContentType;
import com.luan.algafoodapi.core.validation.FileSize;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoProdutoInput {
	
	@ApiModelProperty(value = "Arquivo da foto do produto (500kb, apenas JPG, PNG")
	@NotNull
	@FileSize(max = "500KB")
	@FileContentType(allowed = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE })
	private MultipartFile arquivo;
	
	@ApiModelProperty(value = "Descrição da foto do produto")
	@NotBlank
	private String descricao;

}
