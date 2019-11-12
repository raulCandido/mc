package com.mc.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mc.domain.Categoria;
import com.mc.domain.Cidade;
import com.mc.domain.Cliente;
import com.mc.domain.Endereco;
import com.mc.domain.Estado;
import com.mc.domain.ItemPedido;
import com.mc.domain.Pagamento;
import com.mc.domain.PagamentoComBoleto;
import com.mc.domain.PagamentoComCartao;
import com.mc.domain.Pedido;
import com.mc.domain.Produto;
import com.mc.domain.enums.EstadoPagamento;
import com.mc.domain.enums.TipoCliente;
import com.mc.repository.CategoriaRepository;
import com.mc.repository.CidadeRepository;
import com.mc.repository.ClienteRepository;
import com.mc.repository.EnderecoRepository;
import com.mc.repository.EstadoRepository;
import com.mc.repository.ItemPedidoRepository;
import com.mc.repository.PagamentoRepository;
import com.mc.repository.PedidoRepository;
import com.mc.repository.ProdutoRepository;

@Service
public class DBService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public void instantiateTestDataBase() throws ParseException {


		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Profissionais");
		Categoria cat3 = new Categoria(null, "TESTE 01");
		Categoria cat4 = new Categoria(null, "TESTE 02");
		Categoria cat5 = new Categoria(null, "TESTE 03");
		Categoria cat6 = new Categoria(null, "TESTE 04");
		Categoria cat7 = new Categoria(null, "TESTE 05");
		Categoria cat8 = new Categoria(null, "TESTE 06");
		Categoria cat9 = new Categoria(null, "TESTE 07");
		Categoria cat10 = new Categoria(null, "TESTE 08");
		Categoria cat11 = new Categoria(null, "TESTE 09");
		Categoria cat12 = new Categoria(null, "TESTE 10");
		Categoria cat13 = new Categoria(null, "TESTE 11");
		Categoria cat14 = new Categoria(null, "TESTE 12");
		Categoria cat15 = new Categoria(null, "TESTE 13");
		Categoria cat16 = new Categoria(null, "TESTE 14");
		Categoria cat17 = new Categoria(null, "TESTE 15");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 45.00);
		Produto p4 = new Produto(null, "Toalha", 45.00);
		Produto p5 = new Produto(null, "Colcha", 45.00);
		Produto p6 = new Produto(null, "TV true color", 45.00);
		Produto p7 = new Produto(null, "Roçadeira", 45.00);
		Produto p8 = new Produto(null, "Abajour", 45.00);
		Produto p9 = new Produto(null, "Pendente", 45.00);
		Produto p10 = new Produto(null, "Condicionador", 45.00);
		Produto p11 = new Produto(null, "Shampoo", 45.00);
		Produto p12 = new Produto(null, "Balde", 45.00);
		Produto p13 = new Produto(null, "Tinta", 45.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2, p4));
		cat3.getProdutos().addAll(Arrays.asList(p5, p6));
		cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9, p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));

		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));
		
		Estado est1 = new Estado(null, "Minhas Gerais");
		Estado est2 = new Estado(null, "Ceará");

		Cidade cid1 = new Cidade(null, "Uberlândia", est1);
		Cidade cid2 = new Cidade(null, "Caucaia", est2);
		Cidade cid3 = new Cidade(null, "Fortaleza", est2);

		est1.getCidades().addAll(Arrays.asList(cid1));
		est2.getCidades().addAll(Arrays.asList(cid2,cid3));
		
		Cliente cli1 = new Cliente(null, "Taina de Souza", "raulcanddev@gmail.com", "06235715399", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("996515305","996515306"));
		
		Endereco end1 = new Endereco(null, "Rua Rita Barbosa Lima","48", "Proximo a mercadinho serve bem", "Jardim Icarai", "61621390", cli1, cid2);
		Endereco end2 = new Endereco(null, "Rua Marcelino de Oliveira", "04", "Proximo a Nordeste Emergencia", "Jardim Icarai", "61621070", cli1, cid2);
		
		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, end2);
		
		Pagamento pag1 = new PagamentoComCartao(null, ped1, EstadoPagamento.QUITADO, 6);
		ped1.setPagamento(pag1);

		Pagamento pag2 = new PagamentoComBoleto(null, ped2, EstadoPagamento.PENDENTE, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pag2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped1, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat2, cat3, cat4, cat5, cat6, cat7, cat8, cat9, cat10, cat11, cat12, cat13, cat14, cat15, cat16, cat17));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13));
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3));
		
		clienteRepository.save(cli1);
		enderecoRepository.saveAll(Arrays.asList(end1, end2));
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pag1,pag2));
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
		
	}
}
