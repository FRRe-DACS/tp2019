/*
 * Copyright (C) 2015-2019 UTN-FRRe
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ar.edu.utn.frre.dacs.hospital.profesionales.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.utn.frre.dacs.hospital.profesionales.dao.ObraSocialRepository;
import ar.edu.utn.frre.dacs.hospital.profesionales.model.ObraSocial;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * 
 * @author Dr. Jorge Villaverde
 * @version 1.0
 */
@RestController
@RequestMapping("/api/obraSocial")
@Api("Conjunto de operaciones para Buscar, Crear, Actualizar y Eliminar Obras Sociales")
public class ObraSocialController {
	
	// Dependencies -----------------------------------------------------------

	@Autowired
	private ObraSocialRepository repository;
	
	// Operation --------------------------------------------------------------

	@ApiOperation(value = "Pagina de Obras Sociales", response = Page.class)
	@GetMapping()
	public Page<ObraSocial> getPage(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Retorna una Obra Social por su Id.", response = ResponseEntity.class)
	public ResponseEntity<ObraSocial> findById(@PathVariable Long id) {
		Optional<ObraSocial> opt = repository.findById(id);
		if (opt.isPresent())
			return ResponseEntity.ok(opt.get());
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/rnos/{rnos}")
	@ApiOperation(value = "Retorna una Obra Social por su RNOS.", response = ResponseEntity.class)
	public ResponseEntity<ObraSocial> findByNroMatricula(@PathVariable Integer rnos) {
		Optional<ObraSocial> opt = repository.findByRnos(rnos);
		if (opt.isPresent())
			return ResponseEntity.ok(opt.get());
		return ResponseEntity.notFound().build();
	}	
	
	@PostMapping()
	@ApiOperation(value = "Crea una Obra Social.", response = ResponseEntity.class)
	public ResponseEntity<ObraSocial> create(@Valid @RequestBody ObraSocial createRequest) {
		return ResponseEntity.ok(repository.save(createRequest));
	}
	
	@PutMapping()
	@ApiOperation(value = "Actualiza un Obra Social.", response = ResponseEntity.class)
	public ResponseEntity<ObraSocial> update(@Valid @RequestBody ObraSocial updateRequest) {
		boolean exists = repository.existsById(updateRequest.getId());
		if (exists) {
			return ResponseEntity.ok(repository.save(updateRequest));
		}
		return ResponseEntity.notFound().build();		
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Elimina un Obra Social.", response = ResponseEntity.class)	
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Optional<ObraSocial> opt = repository.findById(id);
		if (opt.isPresent()) {
			repository.delete(opt.get());
			return ResponseEntity.ok().build();			
		}
		return ResponseEntity.notFound().build();
	}

}
