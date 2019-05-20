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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Representa una Obra Social en el Sistema de Hospitales.
 * 
 * @author Dr. Jorge Villaverde
 * @version 1.0
 */
@Entity
@Table(name = "obra_social")
@SequenceGenerator(name = "entity_gen", sequenceName = "obra_social_id_seq", initialValue = 100)
@ApiModel(value = "Médico", description = "Representa a un Profesional Médico en el sistema.")
public class ObraSocial extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4110133875658503892L;

	// Properties -------------------------------------------------------------
	
	/**
	 * Número de Matricula del profesional médico.
	 */
	@NotNull
	@Column(name = "rnos", unique = true)
    @ApiModelProperty(notes = "Número de Registro Nacional de Obra Social", example = " 126700", required = true, position = 0)
	private Integer rnos;
	
	/**
	 * Denominacion de la Obra Social.
	 */
	@NotNull
	@Size(min = 1, max = 200)
	@Column(name = "denominacion", length = 200, nullable = false)
    @ApiModelProperty(notes = "Denominacion de la Obra Social.", example = "OBRA SOCIAL DEL PERSONAL DE LA ACTIVIDAD AZUCARERA TUCUMANA", required = true, position = 1)
	private String denominacion;

	/**
	 * Sigla de la Obra Social.
	 */
	@Size(max = 50)
	@Column(name = "sigla", length = 50, nullable = true)
    @ApiModelProperty(notes = "Sigla de la Obra Social.", example = "OSPIB", required = false, position = 2)
	private String sigla;

	/**
	 * Domicilio de la Obra Social.
	 */
	@NotNull
	@Size(min = 1, max = 55)
	@Column(name = "domicilio", length = 55, nullable = false)
    @ApiModelProperty(notes = "Domicilio de la Obra Social.", example = "CONGRESO DE TUCUMAN  341", required = true, position = 3)
	private String domicilio;
	
	/**
	 * Código Postal de la Obra Social.
	 */
	@NotNull
	@Column(name = "codigo_postal", length = 55, nullable = false)
    @ApiModelProperty(notes = "Código Postal de la Obra Social.", example = "4000", required = true, position = 4)
	private Integer codigoPostal;
	
	/**
	 * Provincia de la Obra Social.
	 */
	@NotNull
	@Size(min = 1, max = 50)
	@Column(name = "provincia", length = 50, nullable = false)
    @ApiModelProperty(notes = "Provincia de la Obra Social.", example = "TUCUMAN", required = true, position = 5)
	private String provincia;

	/**
	 * Teléfono de la Obra Social.
	 */
	@Size(min = 1, max = 50)
	@Column(name = "telefono", length = 50, nullable = true)
    @ApiModelProperty(notes = "Teléfono de la Obra Social.", example = "", required = false, position = 6)
	private String telefono;

	/**
	 * Otro Teléfono de la Obra Social.
	 */
	@Size(min = 1, max = 50)
	@Column(name = "otros_telefonos", length = 50, nullable = true)
    @ApiModelProperty(notes = "Otro Teléfono de la Obra Social.", example = "", required = false, position = 7)
	private String otroTelefono;
	
	/**
	 * e-mail de la Obra Social.
	 */
	@Size(min = 1, max = 50)
	@Column(name = "e_mail", length = 50, nullable = true)
    @ApiModelProperty(notes = "e-mail de la Obra Social.", example = "", required = false, position = 8)
	private String email;
	
	/**
	 * URL de la Web de la Obra Social.
	 */
	@Size(min = 1, max = 255)
	@Column(name = "web", length = 255, nullable = true)
    @ApiModelProperty(notes = "e-mail de la Obra Social.", example = "", required = false, position = 9)
	private String web;
	
	// Getters/Setters --------------------------------------------------------

	public Integer getRnos() {
		return rnos;
	}

	public void setRnos(Integer rnos) {
		this.rnos = rnos;
	}

	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public Integer getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(Integer codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getOtroTelefono() {
		return otroTelefono;
	}

	public void setOtroTelefono(String otroTelefono) {
		this.otroTelefono = otroTelefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

}
