package com.mc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mc.domain.Estado;
import com.mc.repository.EstadoRepository;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository estadoRepository;
	
	public List<Estado> pegarTodosEstadosOrdenadosPorNome(){
		return estadoRepository.findAllByOrderByNome();
	}
	
}
