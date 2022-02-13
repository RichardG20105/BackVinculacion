package com.vinculacion.BackEndPDE.Entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "carrera")
public class Carrera {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idcarrera")
	private Long idCarrera;

	@Column(name = "idfacultad")
	private Long idFacultad;

	@Column(name = "nombrecarrera")
	private String nombreCarrera;

	public Carrera() {
		super();
	}

	//Constructor sin el IDCarrera por que se auto incrementa
	public Carrera(Long idFacultad, String nombreCarrera) {
		super();
		this.idFacultad = idFacultad;
		this.nombreCarrera = nombreCarrera;
	}

	public Long getIdCarrera() {
		return idCarrera;
	}

	public void setIdCarrera(Long idCarrera) {
		this.idCarrera = idCarrera;
	}

	public Long getIdFacultad() {
		return idFacultad;
	}

	public void setIdFacultad(Long idFacultad) {
		this.idFacultad = idFacultad;
	}

	public String getNombreCarrera() {
		return nombreCarrera;
	}

	public void setNombreCarrera(String nombreCarrera) {
		this.nombreCarrera = nombreCarrera;
	}
}