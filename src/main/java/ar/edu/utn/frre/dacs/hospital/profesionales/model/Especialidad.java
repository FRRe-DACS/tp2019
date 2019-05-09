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
package ar.edu.utn.frre.dacs.hospital.profesionales.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * 
 * @author Dr. Jorge Villaverde
 * @version 1.0
 */
@Entity
@Table(name = "especialidad")
@ApiModel(value = "Escialidad", description = "Representa a un Especialidad Médica en el sistema.")
public class Especialidad extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4652710655087505364L;
	
	// Properties -------------------------------------------------------------
	
	/**
	 * Nombre de la especialidad.
	 */
	@NotNull
	@Size(min = 1, max = 50)
	@Column(name = "nombre", length = 50, nullable = false)
    @ApiModelProperty(notes = "Nombre de la especialidad.", example = "Pediatría", required = true, position = 0)
	private String nombre;
	
	/**
	 * Descripción de la especialidad.
	 */
	@Size(max = 255)
	@Column(name = "descripcion", length = 255, nullable = true)
    @ApiModelProperty(notes = "Descripción de la especialidad.", example = "Descripción de Pediatría", required = false, position = 1)
	private String descripcion;

	/**
	 * Médicos que poseen la especialidad
	 */
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "especialidad")
	private Set<Medico> medicos;
	
	// Getters/Setters --------------------------------------------------------
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set<Medico> getMedicos() {
		return medicos;
	}

	public void setMedicos(Set<Medico> medicos) {
		this.medicos = medicos;
	}

}
