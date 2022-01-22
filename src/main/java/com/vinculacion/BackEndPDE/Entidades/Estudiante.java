package com.vinculacion.BackEndPDE.Entidades;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
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
	private String semestre;
	
	@Column(name = "sexoestudiante")
	private String sexoEstudiante;
	
	@OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL)
	private Set<Integra> integras;

	public Estudiante() {
		super();
	}

	public Estudiante(String cedulaEstudiante, String nombreEstudiante, String semestre, String sexoEstudiante,
			Long idCarrera, Integra... integras) {
		super();
		this.cedulaEstudiante = cedulaEstudiante;
		this.nombreEstudiante = nombreEstudiante;
		this.semestre = semestre;
		this.sexoEstudiante = sexoEstudiante;
		this.idCarrera = idCarrera;
		for(Integra integra: integras)integra.setEstudiante(this);
		this.integras = Stream.of(integras).collect(Collectors.toSet());
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

	public String getSemestre() {
		return semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

	public String getSexoEstudiante() {
		return sexoEstudiante;
	}

	public void setSexoEstudiante(String sexoEstudiante) {
		this.sexoEstudiante = sexoEstudiante;
	}

	public Long getIdCarrera() {
		return idCarrera;
	}

	public void setIdCarrera(Long idCarrera) {
		this.idCarrera = idCarrera;
	}

	public void setIntegras(Set<Integra> integras) {
		this.integras = integras;
	}	
}
