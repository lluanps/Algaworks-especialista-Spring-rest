package com.luan.algafoodapi.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "id")
@Entity
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private BigDecimal subTotal;
	
	@Column(nullable = false)
	private BigDecimal taxaFrete;
	
	@Column(nullable = false)
	private BigDecimal valorTotal;
	
	@Column(nullable = false)
	private OffsetDateTime dataCriacao;
	private OffsetDateTime dataConfirmacao;
	private OffsetDateTime dataCancelamento;
	private OffsetDateTime dataEntrega;
	
	@ManyToOne
	@JoinColumn(name = "restaurante_id", nullable = false)
	private Restaurante restaurantes;
	
	@ManyToOne
	@JoinColumn(name = "usuario_cliente_id", nullable = false)
	private Usuario cliente;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private FormaPagamento formaPagamento;
	
	@JsonIgnore
	@Embedded // indica que a classe Endereco é uma parte da classe restaurante
	private Endereco endereco;
	
	@OneToMany(mappedBy = "pedido")
	private List<ItemPedido> itemPedidos;
	
	@Enumerated(EnumType.STRING)
	private StatusPedido statusPedido = StatusPedido.CRIADO;
	
	// método responsável por calcular o valor total do pedido.
	public void calcularValorTotal() {

	    // calculando o subtotal somando os preços totais de todos os itens no pedido
	    this.subTotal = getItemPedidos().stream()
	            .map(item -> item.getPrecoTotal())  // mapeia cada item para seu preço total
	            .reduce(BigDecimal.ZERO, BigDecimal::add);  // reduz os preços totais para obter a soma, começando com zero. e no final addiona

	    // adicionando a taxa de frete ao subtotal para obter o valor total do pedido
	    this.valorTotal = this.subTotal.add(this.taxaFrete);  // adiciona a taxa de frete ao subtotal para obter o valor total
	}
	
	public void definirTaxaFrete() {
		setTaxaFrete(getRestaurantes().getTaxaFrete());
	}
	
	/*
	 * getItemPedidos().forEach(item -> item.setPedido(this));: Itera sobre todos os itens do pedido utilizando um loop forEach.
	 * item -> item.setPedido(this): Para cada item, associa o pedido atual (representado por this) ao item chamando o método setPedido().*/
	public void atribuiPedidoAosItens() {
		getItemPedidos().forEach(item -> item.setPedido(this));
	}
	
}
