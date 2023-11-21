package com.luan.algafoodapi.domain.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Produto {

	private String nome;
	private String descricao;
	private BigDecimal preco;
	private boolean ativo;
	
	@ManyToOne
	@JoinColumn(name = "restaurante_id")
	private List<Restaurante> restaurantes;
	
}
