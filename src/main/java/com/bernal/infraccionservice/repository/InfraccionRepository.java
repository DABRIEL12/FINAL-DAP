package com.bernal.infraccionservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bernal.infraccionservice.entity.Infraccion;

public interface InfraccionRepository  extends JpaRepository<Infraccion, Integer>{
	public Infraccion findByDni(String dni);
}
