package com.bernal.infraccionservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bernal.infraccionservice.entity.Infraccion;
import com.bernal.infraccionservice.exception.GeneralServiceException;
import com.bernal.infraccionservice.exception.NoDataFoundException;
import com.bernal.infraccionservice.exception.ValidateServiceException;
import com.bernal.infraccionservice.repository.InfraccionRepository;
import com.bernal.infraccionservice.service.InfraccionService;
import com.bernal.infraccionservice.validator.InfraccionValidator;

@Service
public class InfraccionServiceImpl implements InfraccionService{
	@Autowired
	private InfraccionRepository repository;

	@Override
	public List<Infraccion> findAll(Pageable page) {
		try {
			List<Infraccion> registros=repository.findAll(page).toList();
			return registros;
		} catch (ValidateServiceException | NoDataFoundException e) {
			throw e;
		}catch (Exception e) {
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	public Infraccion findByDni(String dni) {
		try {
			Infraccion registro=repository.findByDni(dni);
			return registro;
		} catch (ValidateServiceException | NoDataFoundException e) {
			throw e;
		}catch (Exception e) {
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	public Infraccion findById(int id) {
		try {
			Infraccion registro=repository.findById(id).orElseThrow(
					()->new NoDataFoundException("No existe un registro con ese ID"));
			return registro;
		} catch (ValidateServiceException | NoDataFoundException e) {
			throw e;
		}catch (Exception e) {
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	public Infraccion save(Infraccion obj) {
		try {
			InfraccionValidator.save(obj);
			if(obj.getId()==0) {
				Infraccion registroNuevo= repository.save(obj);
				return registroNuevo;
			}
			Infraccion registroExiste=findById(obj.getId());
			registroExiste.setDni(obj.getDni());
			registroExiste.setFecha(obj.getFecha());
			registroExiste.setTitulo(obj.getTitulo());
			registroExiste.setDescripcion(obj.getDescripcion());
			registroExiste.setDireccion(obj.getDireccion());
			return repository.save(registroExiste);
			
		} catch (ValidateServiceException | NoDataFoundException e) {
			throw e;
		}catch (Exception e) {
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	public boolean delete(int id) {
		try {
			Infraccion registro=findById(id);
			repository.delete(registro);
			return true;
		} catch (ValidateServiceException | NoDataFoundException e) {
			throw e;
		}catch (Exception e) {
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}
}
