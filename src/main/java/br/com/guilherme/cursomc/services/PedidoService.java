package br.com.guilherme.cursomc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.guilherme.cursomc.domain.ItemPedido;
import br.com.guilherme.cursomc.domain.PagamentoComBoleto;
import br.com.guilherme.cursomc.domain.Pedido;
import br.com.guilherme.cursomc.domain.enums.EstadoPagamento;
import br.com.guilherme.cursomc.repository.ItemPedidoRepository;
import br.com.guilherme.cursomc.repository.PagamentoRepository;
import br.com.guilherme.cursomc.repository.PedidoRepository;
import br.com.guilherme.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repository;
	@Autowired
	private BoletoService boletoService;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ProdutoService produtoService;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	@Autowired
	private ClienteService clienteService;
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

	@Transactional
	public Pedido insert(Pedido pedido) {
		pedido.setId(null);
		pedido.setInstante(new Date());
		pedido.setCliente(clienteService.find(pedido.getCliente().getId()));
		pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		pedido.getPagamento().setPedido(pedido);
		if (pedido.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) pedido.getPagamento();
			boletoService.preenchimentoPagamentoComBoleto(pagto, pedido.getInstante());
		}
		pedido = repository.save(pedido);
		pagamentoRepository.save(pedido.getPagamento());
		
		for (ItemPedido item : pedido.getItens()) {
			item.setDesconto(0.0);
			item.setProduto(produtoService.find(item.getProduto().getId()));
			item.setPreco(item.getProduto().getPreco());
			item.setPedido(pedido);
		}
		itemPedidoRepository.saveAll(pedido.getItens());
		System.out.println(pedido);
		return pedido;
	}
	
	

}
