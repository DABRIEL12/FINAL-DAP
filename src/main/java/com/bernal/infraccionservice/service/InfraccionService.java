package com.bernal.infraccionservice.service;


import java.util.List;

import org.springframework.data.domain.Pageable;

import com.bernal.infraccionservice.entity.Infraccion;

public interface InfraccionService {
	public List<Infraccion> findAll(Pageable page);
	public Infraccion findByDni(String dni);
	public Infraccion findById(int id);
	public Infraccion save(Infraccion obj);
	public boolean delete(int id);
}
