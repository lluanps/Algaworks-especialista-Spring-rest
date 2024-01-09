package com.luan.algafoodapi.domain.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.luan.algafoodapi.domain.model.Restaurante;

@Repository 
public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>, RestauranteRepositoryQueries, JpaSpecificationExecutor<Restaurante> {
	
	List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);
	
	//@Query("FROM Restaurante WHERE nome Like %:nome% AND cozinhaId = :id")
	List<Restaurante> consultarPorNome(String nome, @Param("id") Long cozinhaId);
	
	//List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinhaId);
	
	Optional<Restaurante> findFirstByNomeContaining(String nome);
	
	List<Restaurante> findTop2ByNomeContaining(String nome);
	
	int countByCozinhaId(Long cozinhaId);
	
	@Query("FROM Restaurante r JOIN FETCH r.cozinha")
	List<Restaurante> findAll();
	
	/*sql usado para descobrir sequencia para usar dentro no netval
	SELECT column_default
	FROM information_schema.columns
	WHERE table_name = 'nome_da_sua_tabela' AND column_name = 'id';
	*/

    @Query(value = "SELECT nextval('restaurante_id_seq')", nativeQuery = true)
    Long findProximoIdDisponivel();

	Restaurante findByNome(String nome);
	
}
