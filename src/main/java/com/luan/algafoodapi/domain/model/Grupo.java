package com.luan.algafoodapi.domain.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "id")
@Entity
public class Grupo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	@ManyToMany
	@JoinTable(name = "grupo_permissao",
	joinColumns = @JoinColumn(name = "grupo_id"),
	inverseJoinColumns = @JoinColumn(name = "permissao_Id"))
	Set<Permissao> permissoes = new HashSet<>();
	
	public boolean desassociarGrupo(Permissao permissao) {
		return getPermissoes().remove(permissao);
	}
	
	public boolean associar(Permissao permissao) {
		return getPermissoes().add(permissao);
	}

}
