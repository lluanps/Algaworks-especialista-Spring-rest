package com.luan.algafoodapi.api.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "cidades")// muda o nome da resposta de cidadeDTOList para cidades
@Getter
@Setter
public class CidadeDTO extends RepresentationModel<CidadeDTO> {

//	@ApiModelProperty(value = "Id da cidade", example = "1")
	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Florianópolis")
	private String nome;
	
	private EstadoDTO estado;
	
}
