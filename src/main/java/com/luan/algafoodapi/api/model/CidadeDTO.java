package com.luan.algafoodapi.api.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "cidades")// muda o nome da resposta de cidadeDTOList para cidades
@Getter
@Setter
public class CidadeDTO extends RepresentationModel<CidadeDTO> {

	private Long id;
	private String nome;
	private EstadoDTO estado;
	
}
