package br.com.guilherme.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.guilherme.cursomc.domain.Categoria;
import br.com.guilherme.cursomc.repository.CategoriaRepository;
import br.com.guilherme.cursomc.services.exceptions.DataIntegrityException;
import br.com.guilherme.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repository;
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	public Categoria insert(Categoria categoria) {
		categoria.setId(null);
		return repository.save(categoria);		
	}

	public Categoria update(Categoria categoria) {
		find(categoria.getId());
		return repository.save(categoria);
		
	}

	public void delete(Integer id) {
		find(id);
		try {
			repository.deleteById(id);				
		}catch(DataIntegrityViolationException ex){
			throw new DataIntegrityException("Não é possível excluir uma categoria que possuí produtos");
		}
	}

	public List<Categoria> findAll() {
		return repository.findAll();
	}
	
	

}
