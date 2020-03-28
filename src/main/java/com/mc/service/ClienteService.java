package com.mc.service;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.base.Strings;
import com.mc.domain.Cidade;
import com.mc.domain.Cliente;
import com.mc.domain.Endereco;
import com.mc.domain.enums.Perfil;
import com.mc.domain.enums.TipoCliente;
import com.mc.dto.ClienteDTO;
import com.mc.dto.ClienteNewDTO;
import com.mc.repository.ClienteRepository;
import com.mc.repository.EnderecoRepository;
import com.mc.security.UserSS;
import com.mc.service.exception.AuthorizationException;
import com.mc.service.exception.DataIntegrityException;
import com.mc.service.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private S3Service s3Service;

	@Autowired
	private ImageService imageService;

	@Value("${img.prefix.client.profile}")
	private String prefixImage;

	public Cliente find(Integer id) {

		// validacao para conferir se usuario logado tem permissao para acesso a alguma
		// informacao
		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}

		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado ID: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	public Cliente pesquisarClientePorCodigo(Integer id) {

		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado ID: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	@Transactional
	public Cliente insert(Cliente obj) {
		// garantir que o objeto é uma insercao, caso o id fosse preenchido o metodo
		// save iria realizar uma edicao
		obj.setId(null);
		repository.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}

	public Cliente update(Cliente obj) {
		find(obj.getId());
		return repository.save(obj);
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String ordeBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), ordeBy);
		return repository.findAll(pageRequest);
	}

	public List<Cliente> findall() {
		return repository.findAll();
	}

	public void delete(Integer id) {
		find(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir um cliente que tem produtos");
		}
	}

	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail());

	}

	public Cliente fromDTO(ClienteNewDTO objDTO) {
		Cliente cliente = new Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfOuCnpj(),
				TipoCliente.toEnum(objDTO.getTipoCliente()), encoder.encode(objDTO.getSenha()));
		Cidade cidade = new Cidade(objDTO.getCidadeId(), null, null);
		Endereco endereco = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(),
				objDTO.getBairro(), objDTO.getCep(), cliente, cidade);
		cliente.getEnderecos().add(endereco);
		cliente.getTelefones().add(objDTO.getTelefone1());
		if (!Strings.isNullOrEmpty(objDTO.getTelefone2())) {
			cliente.getTelefones().add(objDTO.getTelefone2());
		}
		if (!Strings.isNullOrEmpty(objDTO.getTelefone3())) {
			cliente.getTelefones().add(objDTO.getTelefone3());
		}
		return cliente;
	}

	public URI uploadFilePicture(MultipartFile multipartFile) {

		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado.");
		}

		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
		String fileName = prefixImage + user.getId() + ".jpg";

		return s3Service.uploadFile(fileName, imageService.getInputStream(jpgImage, "jpg"), "image");
	}
}
