package com.mc.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import com.mc.domain.enums.Perfil;
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
	@Autowired
	private BCryptPasswordEncoder encoder;

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
		Produto p14 = new Produto(null, "Produto 14", 10.00);
		Produto p15 = new Produto(null, "Produto 15", 10.00);
		Produto p16 = new Produto(null, "Produto 16", 10.00);
		Produto p17 = new Produto(null, "Produto 17", 10.00);
		Produto p18 = new Produto(null, "Produto 18", 10.00);
		Produto p19 = new Produto(null, "Produto 19", 10.00);
		Produto p20 = new Produto(null, "Produto 20", 10.00);
		Produto p21 = new Produto(null, "Produto 21", 10.00);
		Produto p22 = new Produto(null, "Produto 22", 10.00);
		Produto p23 = new Produto(null, "Produto 23", 10.00);
		Produto p24 = new Produto(null, "Produto 24", 10.00);
		Produto p25 = new Produto(null, "Produto 25", 10.00);
		Produto p26 = new Produto(null, "Produto 26", 10.00);
		Produto p27 = new Produto(null, "Produto 27", 10.00);
		Produto p28 = new Produto(null, "Produto 28", 10.00);
		Produto p29 = new Produto(null, "Produto 29", 10.00);
		Produto p30 = new Produto(null, "Produto 30", 10.00);
		Produto p31 = new Produto(null, "Produto 31", 10.00);
		Produto p32 = new Produto(null, "Produto 32", 10.00);
		Produto p33 = new Produto(null, "Produto 33", 10.00);
		Produto p34 = new Produto(null, "Produto 34", 10.00);
		Produto p35 = new Produto(null, "Produto 35", 10.00);
		Produto p36 = new Produto(null, "Produto 36", 10.00);
		Produto p37 = new Produto(null, "Produto 37", 10.00);
		Produto p38 = new Produto(null, "Produto 38", 10.00);
		Produto p39 = new Produto(null, "Produto 39", 10.00);
		Produto p40 = new Produto(null, "Produto 40", 10.00);
		Produto p41 = new Produto(null, "Produto 41", 10.00);
		Produto p42 = new Produto(null, "Produto 42", 10.00);
		Produto p43 = new Produto(null, "Produto 43", 10.00);
		Produto p44 = new Produto(null, "Produto 44", 10.00);
		Produto p45 = new Produto(null, "Produto 45", 10.00);
		Produto p46 = new Produto(null, "Produto 46", 10.00);
		Produto p47 = new Produto(null, "Produto 47", 10.00);
		Produto p48 = new Produto(null, "Produto 48", 10.00);
		Produto p49 = new Produto(null, "Produto 49", 10.00);
		Produto p50 = new Produto(null, "Produto 50", 10.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2, p4));
		cat3.getProdutos().addAll(Arrays.asList(p5, p6));
		cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9, p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));
		cat1.getProdutos().addAll(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20,
				p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p34, p35, p36, p37, p38,
				p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));

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
		p12.getCategorias().add(cat1);
		p13.getCategorias().add(cat1);
		p14.getCategorias().add(cat1);
		p15.getCategorias().add(cat1);
		p16.getCategorias().add(cat1);
		p17.getCategorias().add(cat1);
		p18.getCategorias().add(cat1);
		p19.getCategorias().add(cat1);
		p20.getCategorias().add(cat1);
		p21.getCategorias().add(cat1);
		p22.getCategorias().add(cat1);
		p23.getCategorias().add(cat1);
		p24.getCategorias().add(cat1);
		p25.getCategorias().add(cat1);
		p26.getCategorias().add(cat1);
		p27.getCategorias().add(cat1);
		p28.getCategorias().add(cat1);
		p29.getCategorias().add(cat1);
		p30.getCategorias().add(cat1);
		p31.getCategorias().add(cat1);
		p32.getCategorias().add(cat1);
		p33.getCategorias().add(cat1);
		p34.getCategorias().add(cat1);
		p35.getCategorias().add(cat1);
		p36.getCategorias().add(cat1);
		p37.getCategorias().add(cat1);
		p38.getCategorias().add(cat1);
		p39.getCategorias().add(cat1);
		p40.getCategorias().add(cat1);
		p41.getCategorias().add(cat1);
		p42.getCategorias().add(cat1);
		p43.getCategorias().add(cat1);
		p44.getCategorias().add(cat1);
		p45.getCategorias().add(cat1);
		p46.getCategorias().add(cat1);
		p47.getCategorias().add(cat1);
		p48.getCategorias().add(cat1);
		p49.getCategorias().add(cat1);
		p50.getCategorias().add(cat1);
		
		Estado est1 = new Estado(null, "Minhas Gerais");
		Estado est2 = new Estado(null, "Ceará");

		Cidade cid1 = new Cidade(null, "Uberlândia", est1);
		Cidade cid2 = new Cidade(null, "Caucaia", est2);
		Cidade cid3 = new Cidade(null, "Fortaleza", est2);

		est1.getCidades().addAll(Arrays.asList(cid1));
		est2.getCidades().addAll(Arrays.asList(cid2,cid3));
		
		Cliente cli1 = new Cliente(null, "Taina de Souza", "taina.souza@gmail.com", "06235715399", TipoCliente.PESSOAFISICA, encoder.encode("teste"));
		cli1.getTelefones().addAll(Arrays.asList("996515305","996515306"));
		
		Cliente cli2 = new Cliente(null, "Raul Candido", "raul.cand@hotmail.com", "83843439001", TipoCliente.PESSOAFISICA, encoder.encode("123"));
		cli2.getTelefones().addAll(Arrays.asList("996515345","996515453"));
		cli2.addPerfil(Perfil.ADMIN);
		
		Endereco end1 = new Endereco(null, "Rua Rita Barbosa Lima","48", "Proximo a mercadinho serve bem", "Jardim Icarai", "61621390", cli1, cid2);
		Endereco end2 = new Endereco(null, "Rua Marcelino de Oliveira", "04", "Proximo a Nordeste Emergencia", "Jardim Icarai", "61621070", cli1, cid2);
		
		Endereco end3 = new Endereco(null, "Rua Marcelino de Oliveira", "04", "Proximo a Nordeste Emergencia", "Jardim Icarai", "61621070", cli2, cid2);
		
		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));
		
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
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20,
				p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p34, p35, p36, p37, p38,
				p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3));
		
		clienteRepository.saveAll(Arrays.asList(cli1,cli2));
		enderecoRepository.saveAll(Arrays.asList(end1, end2, end3));
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pag1,pag2));
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
		
	}
}
