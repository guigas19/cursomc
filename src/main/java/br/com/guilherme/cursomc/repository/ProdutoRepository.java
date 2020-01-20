package br.com.guilherme.cursomc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.guilherme.cursomc.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

}
