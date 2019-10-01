package com.mc.domain.enums;

public enum TipoCliente {

	PESSOAFISICA(1, "Pessoa Física"), PESSOAJURIDICA(2, "Pessoa Jurídica");

	private int codigo;
	private String descricao;

	private TipoCliente(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoCliente toEnum(Integer codigo) {
		if(codigo == null) {
			return null;
		}
		for (TipoCliente tipoCliente : TipoCliente.values()) {
			if(codigo.equals(tipoCliente.getCodigo())) {
				return tipoCliente;
			}
		}
		throw new IllegalArgumentException("ID inválido: "+ codigo);
	}
}
