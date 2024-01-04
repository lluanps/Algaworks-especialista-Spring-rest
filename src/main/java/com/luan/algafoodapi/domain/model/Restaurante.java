package com.luan.algafoodapi.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.luan.algafoodapi.Groups;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "id")
@Entity
public class Restaurante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
/*	https://docs.jboss.org/hibernate/stable/validator/reference/en-US/html_single/#section-builtin-constraints
	@NotNull nao aceita null mas aceita string vazia
	@NotEmpty nao aceita nenhum dos dois acima mas aceita string com 'espaço' em branco*/
	@NotBlank(groups = Groups.CadastroRestaurante.class)
	@Column(nullable = false)
	private String nome;
	
	@PositiveOrZero(groups = Groups.CadastroRestaurante.class)
	@Column(name = "taxa_frete", nullable = false)
	private BigDecimal taxaFrete;
	
	@Valid//valida as propriedades de cozinha
	@NotNull(groups = Groups.CadastroRestaurante.class)
	@ManyToOne
	@JoinColumn(name = "cozinha_id", nullable = false)
	private Cozinha cozinha;
	
	@JsonIgnore
	@Embedded // indica que a classe Endereco é uma parte da classe restaurante
	private Endereco endereco;

	@JsonIgnore
	@CreationTimestamp // informe que a propriedade anotada deve ser atribuido com a data no momento que for cadastrada
	@Column(name = "data_cadastro", nullable = false, columnDefinition = "datetime")
	private LocalDateTime dataCadastro;
	
	@JsonIgnore
	@UpdateTimestamp // altera a data sempre na hora atual
	@Column(name = "data_atualizacao", nullable = false, columnDefinition = "datetime") // columnDefinition = "datetime" -> cria sem o milisegundos ficando hh:mm:ss
	private LocalDateTime dataAtualizacao;
	
	@JsonIgnore
	//muitos restaurantes possui muitas formas de pagamentos
	@ManyToMany
	//joinColumns define o nome da coluna da chave estrangeira na tabela intermediaria que faz referencia a tabela restaurante
	@JoinTable(name = "restaurante_forma_pagamento", 
		joinColumns = @JoinColumn(name = "restaurante_id"),
		inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
	private List<FormaPagamento> formasPagamento = new ArrayList<>();
	
	@OneToMany(mappedBy = "restaurantes")
	private List<Produto> produto;
	
	@OneToMany(mappedBy = "restaurantes")
	private List<Pedido> pedido;
	
}
