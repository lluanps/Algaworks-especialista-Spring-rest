package com.luan.algafoodapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luan.algafoodapi.domain.model.Pagamento;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long>{

}
