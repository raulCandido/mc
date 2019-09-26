package com.mc.resource;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mc.domain.Categoria;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	
	@GetMapping
	public ArrayList<Categoria> listar() {

		Categoria cat1 = new Categoria(1, "Informatica");
		Categoria cat2 = new Categoria(2, "Engenharia");
		
		ArrayList<Categoria> lista = new ArrayList<Categoria>();
		
		lista.add(cat1);
		lista.add(cat2);
		
		return lista;
	}
}
