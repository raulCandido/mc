package com.mc.dto;

import java.io.Serializable;

public class CredenciasDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private String userName;
	private String senha;

	public CredenciasDTO(String userName, String senha) {
		super();
		this.userName = userName;
		this.senha = senha;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
