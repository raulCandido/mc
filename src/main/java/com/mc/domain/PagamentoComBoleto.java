package com.mc.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mc.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComBoleto extends Pagamento {

	private static final long serialVersionUID = 1L;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataVencimento;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataPagamento;

	public PagamentoComBoleto() {
	}

	public PagamentoComBoleto(Integer id, Pedido pedido, EstadoPagamento estadoPagamento, Date dataVencimento,
			Date dataPagamento) {
		super(id, pedido, estadoPagamento);
		this.dataPagamento = dataPagamento;
		this.dataVencimento = dataVencimento;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

}
