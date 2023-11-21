package com.luan.algafoodapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luan.algafoodapi.domain.model.FormaPagamento;

@Repository
public interface PagamentoRepository extends JpaRepository<FormaPagamento, Long>{

}
