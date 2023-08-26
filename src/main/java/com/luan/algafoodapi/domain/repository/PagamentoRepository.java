package com.luan.algafoodapi.domain.repository;

import java.util.List;

import com.luan.algafoodapi.domain.model.Pagamento;

public interface PagamentoRepository {
	
	List<Pagamento> listar();
	
	Pagamento buscar(Long id);
	
	Pagamento salvar(Pagamento pagamento);
	
	void remover(Pagamento pagamento);
	

}
