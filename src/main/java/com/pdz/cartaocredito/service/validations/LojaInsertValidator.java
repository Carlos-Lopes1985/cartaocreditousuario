package com.pdz.cartaocredito.service.validations;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.pdz.cartaocredito.entity.dto.LojaNewDTO;
import com.pdz.cartaocredito.resource.exception.FieldMessage;
import com.pdz.cartaocredito.service.validations.util.BR;

public class LojaInsertValidator implements ConstraintValidator<LojaInsert, LojaNewDTO>{


	@Override
	public void initialize(LojaInsert constraintAnnotation) {
	
	}
	
	@Override
	public boolean isValid(LojaNewDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		if(!BR.isValidCNPJ(objDto.getCnpj())) {
			list.add(new FieldMessage("Cnpj", "CNPJ Inválido"));
		}
		
		for( FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
		}
		return list.isEmpty();
	}
}
