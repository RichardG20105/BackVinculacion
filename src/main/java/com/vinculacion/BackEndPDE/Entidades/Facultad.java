package com.vinculacion.BackEndPDE.Entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Facultad")
public class Facultad {
	//Especificación de Atributos segun la tabla Facultad de la BASE DE DATOS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idfacultad")
	private Long idFacultad;

	@Column(name = "nombrefacultad")
	private String nombreFacultad;

	public Facultad() {
		super();
	}
	public Facultad(String nombreFacultad) {
		super();
		this.nombreFacultad = nombreFacultad;
	}

	public Long getIdFacultad() {
		return idFacultad;
	}

	public void setIdFacultad(Long idFacultad) {
		this.idFacultad = idFacultad;
	}

	public String getNombreFacultad() {
		return nombreFacultad;
	}

	public void setNombreFacultad(String nombreFacultad) {
		this.nombreFacultad = nombreFacultad;
	}
}