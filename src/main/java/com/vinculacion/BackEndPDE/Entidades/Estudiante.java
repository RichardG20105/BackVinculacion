package com.vinculacion.BackEndPDE.Entidades;

import javax.persistence.*;

@Entity
@Table(name = "Estudiante")
public class Estudiante {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idestudiante")
	private Long idEstudiante;
	
	@Column(name = "idcarrera")
	private Long idCarrera;
	
	@Column(name = "cedulaestudiante")
	private String cedulaEstudiante;
	
	@Column(name = "nombreestudiante")
	private String nombreEstudiante;
	
	@Column(name = "semestre")
	private int semestre;
	
	@Column(name = "generoestudiante")
	private String generoEstudiante;

	public Estudiante() {
		super();
	}

	public Estudiante(String cedulaEstudiante, String nombreEstudiante, int semestre, String generoEstudiante,
			Long idCarrera) {
		super();
		this.cedulaEstudiante = cedulaEstudiante;
		this.nombreEstudiante = nombreEstudiante;
		this.semestre = semestre;
		this.generoEstudiante = generoEstudiante;
		this.idCarrera = idCarrera;
	}

	public Long getIdEstudiante() {
		return idEstudiante;
	}

	public void setIdEstudiante(Long idEstudiante) {
		this.idEstudiante = idEstudiante;
	}

	public String getCedulaEstudiante() {
		return cedulaEstudiante;
	}

	public void setCedulaEstudiante(String cedulaEstudiante) {
		this.cedulaEstudiante = cedulaEstudiante;
	}

	public String getNombreEstudiante() {
		return nombreEstudiante;
	}

	public void setNombreEstudiante(String nombreEstudiante) {
		this.nombreEstudiante = nombreEstudiante;
	}

	public int getSemestre() {
		return semestre;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}

	public String getGeneroEstudiante() {
		return generoEstudiante;
	}

	public void setGeneroEstudiante(String generoEstudiante) {
		this.generoEstudiante = generoEstudiante;
	}

	public Long getIdCarrera() {
		return idCarrera;
	}

	public void setIdCarrera(Long idCarrera) {
		this.idCarrera = idCarrera;
	}
}
