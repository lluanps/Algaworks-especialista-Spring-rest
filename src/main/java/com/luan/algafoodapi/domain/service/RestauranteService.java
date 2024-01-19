package com.luan.algafoodapi.domain.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.luan.algafoodapi.domain.exception.RestauranteNaoEncontradoException;
import com.luan.algafoodapi.domain.model.Cidade;
import com.luan.algafoodapi.domain.model.Cozinha;
import com.luan.algafoodapi.domain.model.FormaPagamento;
import com.luan.algafoodapi.domain.model.Restaurante;
import com.luan.algafoodapi.domain.model.Usuario;
import com.luan.algafoodapi.domain.repository.RestauranteRepository;

@Service
public class RestauranteService {
	
	@Autowired
	private RestauranteRepository repository;
	
	@Autowired
	private CozinhaService cozinhaService;
	
	@Autowired
	private CidadeService cidadeService;
	
	@Autowired
	private UsuarioService usuarioService; 
	
	@Autowired
	private FormaPagamentoService formaPagamentoService;
	
	public List<Restaurante> findAll() {
		return repository.findAll();
	}
	
	public Optional<Restaurante> findById(Long restaurantedId) {
		try {
			return repository.findById(restaurantedId);
		} catch (EmptyResultDataAccessException e) {
			throw new RestauranteNaoEncontradoException(
					String.format("Restaurante id %d, não existe", restaurantedId));
		}
	}
	
	@Transactional
	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		Long cidadeId = restaurante.getEndereco().getCidade().getId();

		Cozinha cozinha = cozinhaService.buscaOuFalha(cozinhaId);
		Cidade cidade = cidadeService.buscarOuFalhar(cidadeId);
		
		restaurante.setCozinha(cozinha);
		restaurante.getEndereco().setCidade(cidade);
		
		if (restaurante.getDataCadastro() == null) {
			restaurante.setDataCadastro(OffsetDateTime.now());
		}
		
		return repository.save(restaurante);
	}
	
	@Transactional
	public void ativar(Long restaurantedId) {
		Restaurante restauranteAtual = buscaOuFalha(restaurantedId);
		restauranteAtual.ativar();
	}

	@Transactional
	public void inativar(Long restaurantedId) {
		Restaurante resturanteAtual = buscaOuFalha(restaurantedId);
		resturanteAtual.inativar();
	}
	
	@Transactional
	public void desassociarFormaPagamento(Long restauranteId, Long formaPagamentoId) {
		Restaurante restaurante = buscaOuFalha(restauranteId);
		FormaPagamento formaPagamento = formaPagamentoService.buscaOuFalha(formaPagamentoId);
		
		restaurante.removerFormaPagamento(formaPagamento);
	}
	
	@Transactional
	public void associarFormaPagamento(Long restauranteId, Long formaPagamentoId) {
		Restaurante restaurante = buscaOuFalha(restauranteId);
		FormaPagamento formaPagamento = formaPagamentoService.buscaOuFalha(formaPagamentoId);
		
		restaurante.associarFormaPagamento(formaPagamento);
	}

	@Transactional
	public void abrir(Long restauranteId) {
		Restaurante restaurante = buscaOuFalha(restauranteId);
		restaurante.setAberto(true);
	}

	@Transactional
	public void fechar(Long restauranteId) {
		Restaurante restaurante = buscaOuFalha(restauranteId);
		restaurante.setAberto(false);
	}
	
	@Transactional
	public void adicionarResponsavel(Long restauranteId, Long usuarioId) {
		Restaurante restaurante = buscaOuFalha(restauranteId);
		Usuario usuario = usuarioService.buscaOuFalha(usuarioId);
		
		restaurante.adicionarResponsavel(usuario);
	}
	
	@Transactional
	public void removerResponsavel(Long restauranteId, Long usuarioId) {
		Restaurante restaurante = buscaOuFalha(restauranteId);
		Usuario usuario = usuarioService.buscaOuFalha(usuarioId);
	
		restaurante.removerResponsavel(usuario);
	}

	public Restaurante buscaOuFalha(Long restauranteId) {
		return repository.findById(restauranteId).orElseThrow(() -> new RestauranteNaoEncontradoException(
				String.format("Não existe um restaurante com esse id %d", restauranteId)));
	}
	
}
