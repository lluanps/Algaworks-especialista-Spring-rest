package com.luan.algafoodapi.domain.service;

import java.time.OffsetDateTime;

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
	public Pedido salvar(Pedido novoPedido) {
	    Cidade cidade = cidadeService.buscarOuFalhar(novoPedido.getEnderecoEntrega().getCidade().getId());
//	    Usuario cliente = usuarioService.buscaOuFalha(novoPedido.getCliente().getId());
	    Restaurante restaurante = restauranteService.buscaOuFalha(novoPedido.getRestaurante().getId());
	    FormaPagamento formaPagamento = formaPagamentoService.buscaOuFalha(novoPedido.getFormaPagamento().getId());

	    novoPedido.getEnderecoEntrega().setCidade(cidade);
//	    novoPedido.setCliente(cliente);
	    novoPedido.setRestaurante(restaurante);
	    novoPedido.setFormaPagamento(formaPagamento);

	    novoPedido.setDataCriacao(OffsetDateTime.now());
	    novoPedido.setTaxaFrete(novoPedido.getRestaurante().getTaxaFrete());
//	    novoPedido.calcularValorTotal();

//	    novoPedido.getItens().forEach(item -> {
//	        Produto produto = produtoService.buscaOuFalha(
//	        		novoPedido.getRestaurante().getId(), item.getProduto().getId());
//	        
//	        item.setPedido(novoPedido);
//	        item.setProduto(produto);
//	        item.setPrecoUnitario(produto.getPreco());
//	    });
		
	    return pedidoRepository.save(novoPedido);
	}
	
	@Transactional
	public Pedido emitir(Pedido pedido) {
	    validarPedido(pedido);
	    validarItens(pedido);

	    pedido.setTaxaFrete(pedido.getRestaurante().getTaxaFrete());
	    pedido.calcularValorTotal();

	    return pedidoRepository.save(pedido);
	}

	private void validarPedido(Pedido pedido) {
	    Cidade cidade = cidadeService.buscarOuFalhar(pedido.getEnderecoEntrega().getCidade().getId());
	    Usuario cliente = usuarioService.buscaOuFalha(pedido.getCliente().getId());
	    Restaurante restaurante = restauranteService.buscaOuFalha(pedido.getRestaurante().getId());
	    FormaPagamento formaPagamento = formaPagamentoService.buscaOuFalha(pedido.getFormaPagamento().getId());

	    pedido.getEnderecoEntrega().setCidade(cidade);
	    pedido.setCliente(cliente);
	    pedido.setRestaurante(restaurante);
	    pedido.setFormaPagamento(formaPagamento);
	    
	    if (restaurante.naoAceitaFormaPagamento(formaPagamento)) {
	        throw new NegocioException(String.format("Forma de pagamento '%s' não é aceita por esse restaurante.",
	                formaPagamento.getDescricao()));
	    }
	}

	private void validarItens(Pedido pedido) {
	    pedido.getItens().forEach(item -> {
	        Produto produto = produtoService.buscaOuFalha(
	                pedido.getRestaurante().getId(), item.getProduto().getId());
	        
	        item.setPedido(pedido);
	        item.setProduto(produto);
	        item.setPrecoUnitario(produto.getPreco());
	    });
	}

}
