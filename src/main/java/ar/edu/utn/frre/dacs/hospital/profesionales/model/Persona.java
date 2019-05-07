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
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 
 * 
 * @author Dr. Jorge Villaverde
 * @version 1.0
 */
@MappedSuperclass
public abstract class Persona extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 14442223840185530L;
	
	// Properties -------------------------------------------------------------
	
	@NotNull
	@Size(min = 1, max = 50)
	@Column(name = "nombre", length = 50, nullable = false)
	private String nombre;
	
	@NotNull
	@Size(min = 1, max = 50)
	@Column(name = "apellido", length = 50, nullable = false)
	private String apellido;
	
	// Getters/Setters --------------------------------------------------------

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

}
