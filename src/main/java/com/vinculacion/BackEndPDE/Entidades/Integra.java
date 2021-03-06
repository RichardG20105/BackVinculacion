package com.vinculacion.BackEndPDE.Entidades;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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

	@Column(name = "integrainicio")
	private Date integraInicio;

	@Column(name = "integrafinal")
	private Date integraFinal;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "idestudiante",referencedColumnName="idestudiante")
	private Estudiante estudiante;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "idproyecto", referencedColumnName = "idproyecto")
	private Proyecto proyecto;

	@OneToMany(mappedBy = "integra", cascade = CascadeType.ALL)
	private Set<Certificado> certificados = new HashSet<>();

	public Integra() {
		super();
	}

	public Integra(String carrera,String formaParticipacion, Date integraInicio, Date integraFinal,
			Estudiante estudiante, Proyecto proyecto, Certificado... certificados) {
		super();
		this.carrera = carrera;
		this.formaParticipacion = formaParticipacion;
		this.integraInicio = integraInicio;
		this.integraFinal = integraFinal;
		this.estudiante = estudiante;
		this.proyecto = proyecto;
		for(Certificado certificado: certificados) certificado.setIntegra(this);
		this.certificados = Stream.of(certificados).collect(Collectors.toSet());
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

	public Date getIntegraInicio() {
		return integraInicio;
	}

	public void setIntegraInicio(Date integraInicio) {
		this.integraInicio = integraInicio;
	}

	public Date getIntegraFinal() {
		return integraFinal;
	}

	public void setIntegraFinal(Date integraFinal) {
		this.integraFinal = integraFinal;
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

	public void setCertificados(Set<Certificado> certificados) {
		this.certificados = certificados;
	}
}
