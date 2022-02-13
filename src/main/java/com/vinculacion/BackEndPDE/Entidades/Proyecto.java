package com.vinculacion.BackEndPDE.Entidades;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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

	@Column(name = "estado")
	private String estado;

	@ManyToOne
	@JoinColumn(name="idfacultad")
	private Facultad facultad;

	@OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL)
	private Set<Participa> participas = new HashSet<>();

	@OneToMany(mappedBy="proyecto", cascade = CascadeType.ALL)
	private Set<Integra> integras = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "trabaja", joinColumns = @JoinColumn(name = "idproyecto",referencedColumnName = "idproyecto")
	,inverseJoinColumns = @JoinColumn(name = "idcarrera", referencedColumnName = "idcarrera"))
	private Collection<Carrera> carreras;

	public Proyecto() {
		super();
	}

	public Proyecto(String codigo, String nombreProyecto, String resolucion,  String estado, Facultad facultad,
			Collection<Carrera> carreras) {
		super();
		this.codigo = codigo;
		this.nombreProyecto = nombreProyecto;
		this.resolucion = resolucion;
		this.estado = estado;
		this.facultad = facultad;
		this.carreras = carreras;
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Facultad getFacultad() {
		return facultad;
	}

	public void setFacultad(Facultad facultad) {
		this.facultad = facultad;
	}

	public Collection<Carrera> getCarreras() {
		return carreras;
	}

	public void setCarreras(Collection<Carrera> carreras) {
		this.carreras = carreras;
	}
}
