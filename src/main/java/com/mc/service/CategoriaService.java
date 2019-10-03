package com.mc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.mc.domain.Categoria;
import com.mc.repository.CategoriaRepository;
import com.mc.service.exception.DataIntegrityException;
import com.mc.service.exception.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado ID: "+ id + ", Tipo: " + Categoria.class.getName()));
	}

	public Categoria insert(Categoria obj) {
		//garantir que o objeto é uma insercao, caso o id fosse preenchido o metodo save iria realizar uma edicao
		obj.setId(null);
		return repository.save(obj);
	}

	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repository.save(obj);
	}

	public List<Categoria> findall() {
		return repository.findAll();
	}

	public void delete(Integer id) {
		find(id);
		try {
			repository.deleteById(id);			
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel  excluir uma categoria que tem produtos");
		}
		
	}
}
