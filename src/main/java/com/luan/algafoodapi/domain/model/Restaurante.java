package com.luan.algafoodapi.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "id")
@Entity
public class Restaurante {

	@Id
	private Long id;
	
	private String nome;
	
	@Column(name = "taxa_frete")
	private BigDecimal taxaFrete;

}
