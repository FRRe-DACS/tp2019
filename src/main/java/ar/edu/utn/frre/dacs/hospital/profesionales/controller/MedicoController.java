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
import java.util.Set;

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

import ar.edu.utn.frre.dacs.hospital.profesionales.dao.EspecialidadRepository;
import ar.edu.utn.frre.dacs.hospital.profesionales.dao.MedicoRepository;
import ar.edu.utn.frre.dacs.hospital.profesionales.dao.ObraSocialRepository;
import ar.edu.utn.frre.dacs.hospital.profesionales.model.Especialidad;
import ar.edu.utn.frre.dacs.hospital.profesionales.model.Medico;
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
@RequestMapping("/api/medico")
@Api("Conjunto de operaciones para Buscar, Crear, Actualizar y Eliminar Médicos")
public class MedicoController {

	// Dependencies -----------------------------------------------------------

	@Autowired
	private MedicoRepository repository;
	
	@Autowired
	private EspecialidadRepository especialidadRepository;

	@Autowired
	private ObraSocialRepository obraSocialRepository;
	
	// Operation --------------------------------------------------------------

	@ApiOperation(value = "Pagina de Medicos", response = Page.class)
	@GetMapping()
	public Page<Medico> getPage(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Retorna un Médico por su Id.", response = ResponseEntity.class)
	public ResponseEntity<Medico> findById(@PathVariable Long id) {
		Optional<Medico> opt = repository.findById(id);
		if (opt.isPresent())
			return ResponseEntity.ok(opt.get());
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/matricula/{numeroMatricula}")
	@ApiOperation(value = "Retorna un Médico por su nro de matrícula.", response = ResponseEntity.class)
	public ResponseEntity<Medico> findByNroMatricula(@PathVariable Integer numeroMatricula) {
		Optional<Medico> opt = repository.findByNumeroMatricula(numeroMatricula);
		if (opt.isPresent())
			return ResponseEntity.ok(opt.get());
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/especialidad/{idEspecialidad}")
	@ApiOperation(value = ".", response = ResponseEntity.class)
	public ResponseEntity<Set<Medico>> findByEspecialidad(@PathVariable Long idEspecialidad) {
		Especialidad especialidad = especialidadRepository.getOne(idEspecialidad);
		Set<Medico> ret = repository.findByEspecialidad(especialidad);
		return ResponseEntity.ok(ret);
	}

	@GetMapping("/especialidad/{idEspecialidad}/obraSocial/{idObraSocial}")
	@ApiOperation(value = ".", response = ResponseEntity.class)
	public ResponseEntity<Set<Medico>> findByEspecialidadAndObraSocial(@PathVariable Long idEspecialidad, @PathVariable Long idObraSocial) {
		Especialidad especialidad = especialidadRepository.getOne(idEspecialidad);
		ObraSocial obraSocial = obraSocialRepository.getOne(idObraSocial);
		Set<Medico> ret = repository.findByEspecialidadAndObraSociales(especialidad, obraSocial);
		return ResponseEntity.ok(ret);
	}
	
	@GetMapping("/obraSocial/{idObraSocial}")
	@ApiOperation(value = ".", response = ResponseEntity.class)
	public ResponseEntity<Set<Medico>> findByObraSocial(@PathVariable Long idObraSocial) {
		ObraSocial obraSocial = obraSocialRepository.getOne(idObraSocial);
		Set<Medico> ret = repository.findByObraSociales(obraSocial);
		return ResponseEntity.ok(ret);
	}
	
	@PostMapping()
	@ApiOperation(value = "Crea un Medico.", response = ResponseEntity.class)
	public ResponseEntity<Medico> create(@Valid @RequestBody Medico createRequest) {
		return ResponseEntity.ok(repository.save(createRequest));
	}
	
	@PutMapping()
	@ApiOperation(value = "Actualiza un Medico.", response = ResponseEntity.class)
	public ResponseEntity<Medico> update(@Valid @RequestBody Medico updateRequest) {
		boolean exists = repository.existsById(updateRequest.getId());
		if (exists) {
			return ResponseEntity.ok(repository.save(updateRequest));
		}
		return ResponseEntity.notFound().build();		
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Elimina un Medico.", response = ResponseEntity.class)	
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Optional<Medico> opt = repository.findById(id);
		if (opt.isPresent()) {
			repository.delete(opt.get());
			return ResponseEntity.ok().build();			
		}
		return ResponseEntity.notFound().build();
	}

}
