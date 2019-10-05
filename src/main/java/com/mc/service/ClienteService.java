package com.mc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mc.domain.Cliente;
import com.mc.dto.ClienteDTO;
import com.mc.repository.ClienteRepository;
import com.mc.service.exception.DataIntegrityException;
import com.mc.service.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	public Cliente find(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado ID: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	public Cliente insert(Cliente obj) {
		// garantir que o objeto é uma insercao, caso o id fosse preenchido o metodo
		// save iria realizar uma edicao
		obj.setId(null);
		return repository.save(obj);
	}

	public Cliente update(Cliente obj) {
		find(obj.getId());
		return repository.save(obj);
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String ordeBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), ordeBy);
		return repository.findAll(pageRequest);
	}

	public List<Cliente> findall() {
		return repository.findAll();
	}

	public void delete(Integer id) {
		find(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel  excluir uma cliente que tem produtos");
		}
	}

	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail());
	
	}
}
