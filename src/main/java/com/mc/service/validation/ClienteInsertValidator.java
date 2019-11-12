package com.mc.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.mc.domain.Cliente;
import com.mc.domain.enums.TipoCliente;
import com.mc.dto.ClienteNewDTO;
import com.mc.repository.ClienteRepository;
import com.mc.resource.exception.FieldMessage;
import com.mc.service.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

	@Autowired
	private ClienteRepository repository;
	
	private static final String FIELDNAME = "cpfOuCnpj";
	
	@Override
	public void initialize(ClienteInsert constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(ClienteNewDTO value, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<FieldMessage>();

		if (value.getTipoCliente().equals(TipoCliente.PESSOAFISICA.getCodigo()) && !BR.isValidCPF(value.getCpfOuCnpj())) {
			
			list.add(new FieldMessage(FIELDNAME, "CPF inválido"));
			
		}
		if (value.getTipoCliente().equals(TipoCliente.PESSOAJURIDICA.getCodigo()) && !BR.isValidCNPJ(value.getCpfOuCnpj())) {
			list.add(new FieldMessage(FIELDNAME, "CNPJ inválido"));
		}
		
		Cliente cliente = repository.findByCpfOuCnpj(value.getCpfOuCnpj());
		
		if (cliente != null) {
			if (cliente.getEmail() != null) {
				list.add(new FieldMessage("email", "Email: " +cliente.getEmail()+ " já cadastrado."));
			}
			if (cliente.getCpfOuCnpj() != null) {
				list.add(new FieldMessage(FIELDNAME, "CPF/CNPJ já cadastrado."));
			}
		}
		
		for (FieldMessage fieldMessage : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(fieldMessage.getMessage()).addPropertyNode(fieldMessage.getFieldName()).addConstraintViolation();
		}
		return list.isEmpty();
	}

}






