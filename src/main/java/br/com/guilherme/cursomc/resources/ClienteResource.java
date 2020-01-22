package br.com.guilherme.cursomc.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.guilherme.cursomc.domain.Cliente;
import br.com.guilherme.cursomc.dto.ClienteDTO;
import br.com.guilherme.cursomc.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {
		
		Cliente obj = service.find(id);
		return ResponseEntity.ok().body(obj);		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO categoriaDto, @PathVariable Integer id){
		Cliente categoria = service.fromDto(categoriaDto);
		categoria.setId(id);
		service.update(categoria);
		return ResponseEntity.noContent().build();		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping()
	public ResponseEntity<List<ClienteDTO>> findAll() {
		
		List<Cliente> categorias = service.findAll();
		List<ClienteDTO> listaDtos = categorias.stream().map(categoria -> new ClienteDTO(categoria)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listaDtos);
		
	}
	
	@GetMapping("/page")
	public ResponseEntity<Page<ClienteDTO>> findPage(
			@RequestParam(value="page", defaultValue = "0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value="direction", defaultValue = "ASC")String direction) {
		
		Page<Cliente> categorias = service.findPage(page, linesPerPage, orderBy, direction);
		Page<ClienteDTO> listaDtos = categorias.map(categoria -> new ClienteDTO(categoria));
		return ResponseEntity.ok().body(listaDtos);
		
	} 

}
