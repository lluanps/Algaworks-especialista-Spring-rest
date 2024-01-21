package com.luan.algafoodapi.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luan.algafoodapi.domain.exception.NegocioException;
import com.luan.algafoodapi.domain.exception.PedidoNaoEncontradoException;
import com.luan.algafoodapi.domain.model.Cidade;
import com.luan.algafoodapi.domain.model.FormaPagamento;
import com.luan.algafoodapi.domain.model.Pedido;
import com.luan.algafoodapi.domain.model.Produto;
import com.luan.algafoodapi.domain.model.Restaurante;
import com.luan.algafoodapi.domain.model.Usuario;
import com.luan.algafoodapi.domain.repository.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired
	private CidadeService cidadeService;
	
	@Autowired
	private FormaPagamentoService formaPagamentoService;
	
	@Autowired
	private ProdutoService produtoService;

	public Pedido buscaOuFalha(Long pedidoId) {
		return pedidoRepository.findById(pedidoId)
				.orElseThrow(() -> new PedidoNaoEncontradoException(pedidoId));
	}

	@Transactional
	public Pedido emitir(Pedido pedido) {
	    validarPedido(pedido);
	    validarItens(pedido);

	    pedido.setTaxaFrete(pedido.getRestaurantes().getTaxaFrete());
	    pedido.calcularValorTotal();

	    return pedidoRepository.save(pedido);
	}

	private void validarPedido(Pedido pedido) {
	    Cidade cidade = cidadeService.buscarOuFalhar(pedido.getEndereco().getCidade().getId());
//	    Usuario cliente = usuarioService.buscaOuFalha(pedido.getCliente().getId());
	    Restaurante restaurante = restauranteService.buscaOuFalha(pedido.getRestaurantes().getId());
	    FormaPagamento formaPagamento = formaPagamentoService.buscaOuFalha(pedido.getFormaPagamento().getId());

	    pedido.getEndereco().setCidade(cidade);
//	    pedido.setCliente(cliente);
	    pedido.setRestaurantes(restaurante);
	    pedido.setFormaPagamento(formaPagamento);
	    
	    if (restaurante.naoAceitaFormaPagamento(formaPagamento)) {
	        throw new NegocioException(String.format("Forma de pagamento '%s' não é aceita por esse restaurante.",
	                formaPagamento.getDescricao()));
	    }
	}

	private void validarItens(Pedido pedido) {
	    pedido.getItemPedidos().forEach(item -> {
	        Produto produto = produtoService.buscaOuFalha(
	                pedido.getRestaurantes().getId(), item.getProduto().getId());
	        
	        item.setPedido(pedido);
	        item.setProduto(produto);
	        item.setPrecoUnitario(produto.getPreco());
	    });
	}
	
}
