package com.luan.algafoodapi.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.luan.algafoodapi.domain.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>,
		JpaSpecificationExecutor<Pedido> {
	
	@Query("from Pedido p JOIN FETCH p.cliente JOIN FETCH p.restaurante r JOIN FETCH r.cozinha")
	List<Pedido> findAll();

}
