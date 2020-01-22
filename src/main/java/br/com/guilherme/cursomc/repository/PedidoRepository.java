package br.com.guilherme.cursomc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.guilherme.cursomc.domain.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

}
