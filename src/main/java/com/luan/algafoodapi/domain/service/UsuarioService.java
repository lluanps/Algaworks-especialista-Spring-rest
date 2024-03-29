package com.luan.algafoodapi.domain.service;

import java.time.OffsetDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luan.algafoodapi.domain.exception.NegocioException;
import com.luan.algafoodapi.domain.exception.UsuarioNaoEncontradoException;
import com.luan.algafoodapi.domain.model.Grupo;
import com.luan.algafoodapi.domain.model.Usuario;
import com.luan.algafoodapi.domain.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private GrupoService grupoService;
	
	public Usuario buscaOuFalha(Long usuarioId) {
		return repository.findById(usuarioId).orElseThrow(() -> new UsuarioNaoEncontradoException(
				String.format("Não existe um cadastro de usuario com o código %d em nosso sisterma.", usuarioId)));
	}

	@Transactional
	public Usuario salvar(Usuario usuario) {
		repository.detach(usuario);
		
		Optional<Usuario> usarExistente = repository.findByEmail(usuario.getEmail());
		if (usarExistente.isPresent() && !usarExistente.get().equals(usuario)) {
			throw new NegocioException(String.format("Já existe um usuário cadastrado com o e-mail %s", usuario.getEmail()));
		}
		
		if (usuario.getId() == null) {
			usuario.setDataCadastro(OffsetDateTime.now());
		}
		
		return repository.save(usuario);
	}
	
	@Transactional
	public void alterarSenha(Long usuarioId, String senhaAtual, String senhanova) {
		Usuario usuario = buscaOuFalha(usuarioId);
		
		if (usuario.senhaNaoConincideCom(senhaAtual)) {
			throw new NegocioException("Senha atual informada não coincide com a senha do usuário.");
		}
		
		usuario.setSenha(senhanova);
	}

	@Transactional
	public void associarGrupo(Long usuarioId, Long grupoId) {
		Usuario usuario  = buscaOuFalha(usuarioId);
		Grupo grupo = grupoService.buscaOuFalha(grupoId);
		
		usuario.adicionarGrupo(grupo);
	}
	
	@Transactional
	public void desassociarGrupo(Long usuarioId, Long grupoId) {
		Usuario usuario = buscaOuFalha(usuarioId);
		Grupo grupo = grupoService.buscaOuFalha(grupoId);
		
		usuario.removerGrupo(grupo);
	}
	
}
