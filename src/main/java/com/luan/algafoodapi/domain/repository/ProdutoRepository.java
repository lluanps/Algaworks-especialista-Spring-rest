package com.luan.algafoodapi.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.luan.algafoodapi.domain.model.Produto;
import com.luan.algafoodapi.domain.model.Restaurante;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	@Query("FROM Produto p WHERE p.restaurante.id = :restaurante AND p.id = :produto")
	Optional<Produto> findProdutoIdByRestauaranteId(@Param("restaurante") Long restauranteId, @Param("produto") Long produtoId);

	List<Produto> findByRestaurante(Restaurante restaurante);

}