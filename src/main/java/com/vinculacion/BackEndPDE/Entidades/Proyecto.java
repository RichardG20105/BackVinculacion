package com.vinculacion.BackEndPDE.Entidades;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "proyecto")
public class Proyecto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idproyecto")
	private Long idProyecto;
	
	@Column(name = "codigo")
	private String codigo;
	
	@Column(name = "nombreproyecto")
	private String nombreProyecto;
	
	@Column(name = "resolucion")
	private String resolucion;
	
	@OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL)
	private Set<Participa> participas = new HashSet<>();

	public Proyecto() {
		super();
	}

	public Proyecto(String codigo, String nombreProyecto, String resolucion) {
		super();
		this.codigo = codigo;
		this.nombreProyecto = nombreProyecto;
		this.resolucion = resolucion;
	}

	public Long getIdProyecto() {
		return idProyecto;
	}

	public void setIdProyecto(Long idProyecto) {
		this.idProyecto = idProyecto;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombreProyecto() {
		return nombreProyecto;
	}

	public void setNombreProyecto(String nombreProyecto) {
		this.nombreProyecto = nombreProyecto;
	}

	public String getResolucion() {
		return resolucion;
	}

	public void setResolucion(String resolucion) {
		this.resolucion = resolucion;
	}
}
