package com.luan.algafoodapi.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.luan.algafoodapi.core.validation.TaxaFrete;
import com.luan.algafoodapi.core.validation.ValorZeroIncluiDescricao;

import lombok.Data;
import lombok.EqualsAndHashCode;

@ValorZeroIncluiDescricao(valorField = "taxaFrete", descricaoField = "nome", descricaoObrigatoria = "Frete Grátis")
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
	@Column(nullable = false)
	private String nome;
	
	@TaxaFrete
	@Column(name = "taxa_frete", nullable = false)
	private BigDecimal taxaFrete;
	
//	@Valid//valida as propriedades de cozinha
//	@ConvertGroup(from = Default.class, to = Groups.CozinhaId.class)//from = convert o grupo,to = para outro grupo
	@ManyToOne
	@JoinColumn(name = "cozinha_id", nullable = false)
	private Cozinha cozinha;
	
	@Embedded // indica que a classe Endereco é uma parte da classe restaurante
	private Endereco endereco;
	
	@Column(nullable = false)
	private boolean ativo = true;

	@CreationTimestamp // informe que a propriedade anotada deve ser atribuido com a data no momento que for cadastrada
	@Column(name = "data_cadastro", nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dataCadastro;
	
	@UpdateTimestamp // altera a data sempre na hora atual
	@Column(name = "data_atualizacao", nullable = false, columnDefinition = "datetime") // columnDefinition = "datetime" -> cria sem o milisegundos ficando hh:mm:ss
	private OffsetDateTime dataAtualizacao;
	
	//muitos restaurantes possui muitas formas de pagamentos
	@ManyToMany
	//joinColumns define o nome da coluna da chave estrangeira na tabela intermediaria que faz referencia a tabela restaurante
	@JoinTable(name = "restaurante_forma_pagamento", 
		joinColumns = @JoinColumn(name = "restaurante_id"),
		inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
	private Set<FormaPagamento> formasPagamento = new HashSet<>();
	
	@ManyToMany
	@JoinTable(name = "restaurante_usuario_responsavel",
			joinColumns = @JoinColumn(name = "restaurante_id"),
			inverseJoinColumns = @JoinColumn(name = "usuario_id"))
	private Set<Usuario> responsaveis;
	
	@OneToMany(mappedBy = "restaurante")
	private List<Produto> produto;
	
	private boolean aberto;
	
	public void ativar() {
		setAtivo(true);
	}
	
	public void inativar() {
		setAtivo(false);
	}
	
	public boolean removerFormaPagamento(FormaPagamento formaPagamento) {
		return getFormasPagamento().remove(formaPagamento);
	}
	
	public boolean associarFormaPagamento(FormaPagamento formaPagamento) {
		return getFormasPagamento().add(formaPagamento);
	}
	
	public void abrir() {
		setAberto(true);
	}
	
	public void fechar() {
		setAberto(false);
	}
	
	public void adicionarResponsavel(Usuario usuario) {
		getResponsaveis().add(usuario);
	}
	
	public void removerResponsavel(Usuario usuario) {
		getResponsaveis().remove(usuario);
	}
	
}
