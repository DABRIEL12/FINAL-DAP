package com.bernal.infraccionservice.validator;

import com.bernal.infraccionservice.entity.Infraccion;
import com.bernal.infraccionservice.exception.ValidateServiceException;

public class InfraccionValidator {
	public static void save(Infraccion obj) {
		if(obj.getDni()==null || obj.getDni().trim().isEmpty()) {
			throw new ValidateServiceException("El nombre es requerido");
		}
		if(obj.getDni().length()> 8) {
			throw new ValidateServiceException("El dni no debe tener más de 8 caracteres");
		}
		
	}
}
