package com.pdz.cartaocredito.service.validations;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.pdz.cartaocredito.entity.dto.LojaNovoDTO;
import com.pdz.cartaocredito.resource.exception.FieldMessage;
import com.pdz.cartaocredito.service.validations.util.CpfCnpj;

public class LojaNovoValidador implements ConstraintValidator<LojaNovo, LojaNovoDTO>{


	@Override
	public void initialize(LojaNovo constraintAnnotation) {
	
	}
	
	@Override
	public boolean isValid(LojaNovoDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		if(!CpfCnpj.isValidCNPJ(objDto.getCnpj())) {
			list.add(new FieldMessage("Cnpj", "CNPJ Inválido"));
		}
		
		for( FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
		}
		return list.isEmpty();
	}
}
