package br.com.guilherme.cursomc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.guilherme.cursomc.domain.Categoria;
import br.com.guilherme.cursomc.domain.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer>{

}
