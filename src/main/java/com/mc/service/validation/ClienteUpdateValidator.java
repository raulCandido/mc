package com.mc.service.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.mc.domain.Cliente;
import com.mc.dto.ClienteDTO;
import com.mc.repository.ClienteRepository;
import com.mc.resource.exception.FieldMessage;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

	@Autowired
	private ClienteRepository repository;

	@Autowired
	private HttpServletRequest request;
	
	@Override
	public void initialize(ClienteUpdate constraintAnnotation) {
		// TODO Auto-generated method stub
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	private Integer pegarUriId () {
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String> ) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
		return uriId;
	}
	
	@Override
	public boolean isValid(ClienteDTO value, ConstraintValidatorContext context) {
		

		List<FieldMessage> list = new ArrayList<FieldMessage>();

		Cliente cliente = repository.findByEmail(value.getEmail());
	
		Integer uriId = pegarUriId();

		if (cliente != null && !cliente.getId().equals(uriId)) {
			if (cliente.getEmail() != null) {
				list.add(new FieldMessage("email", "Email: " + cliente.getEmail() + " já cadastrado."));
			}
		}

		for (FieldMessage fieldMessage : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(fieldMessage.getMessage()).addPropertyNode(fieldMessage.getFieldName()).addConstraintViolation();
		}
		return list.isEmpty();
	}

}






