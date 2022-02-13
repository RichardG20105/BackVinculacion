package com.vinculacion.BackEndPDE.Entidades;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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

	@OneToMany(mappedBy = "facultad")
	private Set<Proyecto> proyectos;

	public Facultad() {
		super();
	}
	public Facultad(String nombreFacultad, Proyecto... proyectos) {
		super();
		this.nombreFacultad = nombreFacultad;
		for(Proyecto proyecto: proyectos) proyecto.setFacultad(this);
		this.proyectos = Stream.of(proyectos).collect(Collectors.toSet());
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