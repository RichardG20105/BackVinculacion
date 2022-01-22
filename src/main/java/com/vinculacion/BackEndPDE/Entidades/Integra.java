package com.vinculacion.BackEndPDE.Entidades;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "Integra")
public class Integra {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idintegra")
	private Long idIntegra;
	
	@Column(name="carrera")
	private String carrera;
	
	@Column(name = "formaparticipacion")
	private String formaParticipacion;
	
	@Column(name = "anioparticipaest")
	private Date anioParticipaEst;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "idestudiante",referencedColumnName="idestudiante")
	private Estudiante estudiante;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "idproyecto", referencedColumnName = "idproyecto")
	private Proyecto proyecto;

	public Integra() {
		super();
	}

	public Integra(String carrera,String formaParticipacion, Date anioParticipaEst, Estudiante estudiante, Proyecto proyecto) {
		super();
		this.carrera = carrera;
		this.formaParticipacion = formaParticipacion;
		this.anioParticipaEst = anioParticipaEst;
		this.estudiante = estudiante;
		this.proyecto = proyecto;
	}

	public Long getIdIntegra() {
		return idIntegra;
	}

	public void setIdIntegra(Long idIntegra) {
		this.idIntegra = idIntegra;
	}

	public String getCarrera() {
		return carrera;
	}

	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}

	public String getFormaParticipacion() {
		return formaParticipacion;
	}

	public void setFormaParticipacion(String formaParticipacion) {
		this.formaParticipacion = formaParticipacion;
	}

	public Date getAnioParticipaEst() {
		return anioParticipaEst;
	}

	public void setAnioParticipaEst(Date anioParticipaEst) {
		this.anioParticipaEst = anioParticipaEst;
	}

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}
}
