package com.mc.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mc.domain.Categoria;
import com.mc.domain.Produto;
import com.mc.dto.CategoriaDTO;
import com.mc.dto.ProdutoDTO;
import com.mc.resource.utils.URL;
import com.mc.service.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Produto produto = service.buscar(id);
		return ResponseEntity.ok(produto);

	}
	
	@GetMapping()
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(value = "nome", defaultValue = "") String nome,
			@RequestParam(value = "categorias", defaultValue = "") String categorias,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String ordeBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Produto> produtos = service.search(URL.decodeParam(nome), URL.decodeIntList(categorias), page, linesPerPage, ordeBy, direction);
		Page<ProdutoDTO> listDTO = produtos.map(produto -> new ProdutoDTO(produto));
		return ResponseEntity.ok(listDTO);
	}
}
