package com.luan.algafoodapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luan.algafoodapi.domain.model.Cidade;
import com.luan.algafoodapi.domain.model.Estado;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

	Estado save(Estado estado);
	
}
