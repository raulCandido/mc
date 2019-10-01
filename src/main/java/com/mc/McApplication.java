package com.mc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mc.domain.Categoria;
import com.mc.domain.Cidade;
import com.mc.domain.Cliente;
import com.mc.domain.Endereco;
import com.mc.domain.Estado;
import com.mc.domain.Produto;
import com.mc.domain.enums.TipoCliente;
import com.mc.repository.CategoriaRepository;
import com.mc.repository.CidadeRepository;
import com.mc.repository.ClienteRepository;
import com.mc.repository.EnderecoRepository;
import com.mc.repository.EstadoRepository;
import com.mc.repository.ProdutoRepository;

@SpringBootApplication
public class McApplication implements CommandLineRunner {

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

	public static void main(String[] args) {
		SpringApplication.run(McApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "DBA");
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 45.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));

		Estado est1 = new Estado(null, "Minhas Gerais");
		Estado est2 = new Estado(null, "Ceará");

		Cidade cid1 = new Cidade(null, "Uberlândia", est1);
		Cidade cid2 = new Cidade(null, "Caucaia", est2);
		Cidade cid3 = new Cidade(null, "Fortaleza", est2);

		est1.getCidades().addAll(Arrays.asList(cid1));
		est2.getCidades().addAll(Arrays.asList(cid2,cid3));
		
		Cliente cli1 = new Cliente(null, "Taina de Souza", "taina.souza@gmail.com", "06235715399", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("996515305","996515306"));
		
		Endereco end1 = new Endereco(null, "Rua Rita Barbosa Lima", 48, "Proximo a mercadinho serve bem", "Jardim Icarai", "61621390", cli1, cid2);
		Endereco end2 = new Endereco(null, "Rua Marcelino de Oliveira", 04, "Proximo a Nordeste Emergencia", "Jardim Icarai", "61621070", cli1, cid2);
		
		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3));
		
		clienteRepository.save(cli1);
		enderecoRepository.saveAll(Arrays.asList(end1, end2));
	}

}
