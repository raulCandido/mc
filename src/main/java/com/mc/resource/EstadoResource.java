package com.mc.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mc.domain.Cidade;
import com.mc.domain.Estado;
import com.mc.dto.CidadeDTO;
import com.mc.dto.EstadoDTO;
import com.mc.service.CidadeService;
import com.mc.service.EstadoService;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {

	@Autowired	
	private EstadoService estadoService;
	
	@Autowired
	private CidadeService cidadeService;

	@GetMapping
	public ResponseEntity<List<EstadoDTO>> pegarTodosEstadosOrdenadosPorNome(){
		List<Estado> estados = estadoService.pegarTodosEstadosOrdenadosPorNome();
		List<EstadoDTO> estadosDTO = estados.stream().map(estado -> new EstadoDTO(estado)).collect(Collectors.toList());
		return ResponseEntity.ok(estadosDTO);
	}
	@GetMapping(value = "/{estadoId}/cidades")
	public ResponseEntity<List<CidadeDTO>> pegarCidadesPorEstado(@PathVariable Integer estadoId){
		List<Cidade> estados = cidadeService.findByEstado(estadoId);
		List<CidadeDTO> cidadeDTOs = estados.stream().map(cidade -> new CidadeDTO(cidade)).collect(Collectors.toList());
		return ResponseEntity.ok(cidadeDTOs);
	}
//	@GetMapping(value = "/{estadoId}/cidades")
//	public ResponseEntity<List<CidadeDTO>> pegarEstadoCidades(@PathVariable Integer estadoId){
//		List<Cidade> estados = cidadeService.findByEstado(estadoId);
//		List<CidadeDTO> cidadeDTOs = estados.stream().map(cidade -> new CidadeDTO(cidade)).collect(Collectors.toList());
//		return ResponseEntity.ok(cidadeDTOs);
//	}
}





