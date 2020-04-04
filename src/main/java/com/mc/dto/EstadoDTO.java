package com.mc.dto;

import java.io.Serializable;
import java.util.List;

import com.mc.domain.Cidade;
import com.mc.domain.Estado;

public class EstadoDTO implements Serializable {

	/**
	 @author Raul
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private List<Cidade> cidades;
	
	public EstadoDTO() {
	}

	public EstadoDTO(Estado estado) {
		this.id = estado.getId();
		this.nome = estado.getNome();
	}
	//o construtor altera a consulta trazendo a lista de cidades
//	public EstadoDTO(Estado estado) {
//		this.id = estado.getId();
//		this.nome = estado.getNome();
//		this.cidades = estado.getCidades();
//	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}
	
	
	

}
