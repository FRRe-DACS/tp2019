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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Médico:
 * Representa a un Profesional Médico en el sistema.
 * @author Dr. Jorge Villaverde
 * @version 1.0
 */
@Entity
@Table(name = "medico")
@ApiModel(value = "Médico", description = "Representa a un Profesional Médico en el sistema.")
public class Medico extends Persona {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6379686073909367286L;
	
	// Properties -------------------------------------------------------------
	
	/**
	 * Número de Matricula del profesional médico.
	 */
	@NotNull
	@Column(name = "nro_matricula", unique = true)
    @ApiModelProperty(notes = "Número de Matricula del profesional médico.", example = "1234", required = true, position = 0)
	private Integer numeroMatricula;

	/**
	 * Especialidades del médico
	 */
	@NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_especialidad", nullable = false)
    @ApiModelProperty(notes = "Especialidad del Medico", position = 1)
	private Especialidad especialidad;
	
	// Getters/Setters --------------------------------------------------------
	
	public Integer getNumeroMatricula() {
		return numeroMatricula;
	}

	public void setNumeroMatricula(Integer numeroMatricula) {
		this.numeroMatricula = numeroMatricula;
	}

	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}
}
