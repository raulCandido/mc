package com.mc.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mc.util.UtilFormat;

@Entity
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date instance;
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
	private Pagamento pagamento;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	@ManyToOne
	@JoinColumn(name = "endereco_id")
	private Endereco endereco;
	
	@OneToMany(mappedBy = "id.pedido")
	private Set<ItemPedido> itens = new HashSet<ItemPedido>();
	
	public Pedido() {
	}

	public Pedido(Integer id, Date instance, Cliente cliente, Endereco endereco) {
		super();
		this.id = id;
		this.instance = instance;
		this.cliente = cliente;
		this.endereco = endereco;
	}

	
	public double getValorTotal() {
		double soma = 0.0;
		for (ItemPedido itemPedido : itens) {
			soma = soma + itemPedido.getSubTotal();
		}
		return soma;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getInstance() {
		return instance;
	}

	public void setInstance(Date instance) {
		this.instance = instance;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Set<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Pedido número: ");
		builder.append(getId());
		builder.append(", Instance: ");
		builder.append(UtilFormat.convertDataToBR(getInstance()));
		builder.append(", Cliente: ");
		builder.append(getCliente().getNome());
		builder.append(", Situação do pagamento: ");
		builder.append(getPagamento().getEstadoPagamento().getDescricao());
		builder.append("\nDescrição:\n");
		for (ItemPedido itemPedido : itens) {
			builder.append(itemPedido.toString());
		}
		builder.append("Valor total: ");
		builder.append(UtilFormat.convertMoneyToBR(getValorTotal()));
		return builder.toString();
	}
	

}
