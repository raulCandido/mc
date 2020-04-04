package com.mc.dto;

import com.mc.domain.Cidade;

public class CidadeDTO {

	private Integer id;
	private String nome;
	
	public CidadeDTO() {
	}
	
	public CidadeDTO(Cidade cidade) {
		this.id = cidade.getId();
		this.nome = cidade.getNome();
	}

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
	
}
