package com.bernal.infraccionservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bernal.infraccionservice.entity.Infraccion;
import com.bernal.infraccionservice.service.InfraccionService;



@RestController
@RequestMapping("/v1/infracciones")
public class InfraccionController {
	@Autowired
	private InfraccionService service;
	
	@GetMapping
	public ResponseEntity<List<Infraccion>> findAll(
				@RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
				@RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize
			){
		Pageable page = PageRequest.of(pageNumber, pageSize);
		List<Infraccion> registros = service.findAll(page);
		return new ResponseEntity<>(registros, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Infraccion> getById(@PathVariable("id") int id){
		Infraccion registro =service.findById(id);
		return new ResponseEntity<>(registro, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Infraccion> create(@RequestBody Infraccion registro){
		Infraccion denuncia = service.save(registro);
		return new ResponseEntity<>(denuncia, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Infraccion> update(@PathVariable(value = "id") int id, @RequestBody Infraccion registro){
		Infraccion denuncia = service.save(registro);
		return new ResponseEntity<>(denuncia, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id){
		
		service.delete(id);
		
		return new ResponseEntity<>(null, HttpStatus.OK);
		
		
	}

}
