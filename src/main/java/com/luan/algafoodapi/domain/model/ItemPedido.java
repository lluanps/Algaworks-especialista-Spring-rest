package com.luan.algafoodapi.domain.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "id")
@Entity
public class ItemPedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer quantidade;
	private BigDecimal precoUnitario;
	private BigDecimal precoTotal;
	private String observacao;
	
	@ManyToOne
//	@JoinColumn(name = "pedido_id", nullable = false)
	@JoinColumn(nullable = false)
	private Pedido pedido;
	
	@ManyToOne
//	@JoinColumn(name = "produto_id", nullable = false)
	@JoinColumn(nullable = false)
	private Produto produto;

	public void calcularPrecoTotal() {
		BigDecimal precoUnitario = this.getPrecoTotal();
		Integer quantidade = this.getQuantidade();
		
		if (precoUnitario == null) {
			precoUnitario = BigDecimal.ZERO;
		}
		
		if (quantidade == null) {
			quantidade = 0;
		}
		
		this.setPrecoTotal(precoUnitario.multiply(new BigDecimal(quantidade)));
	}
	
}
