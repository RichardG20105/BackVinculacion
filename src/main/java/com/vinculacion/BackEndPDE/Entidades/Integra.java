package com.vinculacion.BackEndPDE.Entidades;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "Integra")
public class Integra {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idintegra")
	private Long idIntegra;
	
	@Column(name = "idproyecto")
	private Long idProyecto;
	
	@Column(name = "idestudiante")
	private Long idEstudiante;
	
	@Column(name = "formaparticipacion")
	private String formaParticipacion;
	
	@Column(name = "anoparticipaest")
	private Date anoParticipaEst;

	public Integra() {
		super();
	}

	public Integra(Long idProyecto, Long idEstudiante, String formaParticipacion, Date anoParticipaEst) {
		super();
		this.idProyecto = idProyecto;
		this.idEstudiante = idEstudiante;
		this.formaParticipacion = formaParticipacion;
		this.anoParticipaEst = anoParticipaEst;
	}

	public Long getIdIntegra() {
		return idIntegra;
	}

	public void setIdIntegra(Long idIntegra) {
		this.idIntegra = idIntegra;
	}

	public Long getIdProyecto() {
		return idProyecto;
	}

	public void setIdDocente(Long idProyecto) {
		this.idProyecto = idProyecto;
	}

	public Long getIdEstudiante() {
		return idEstudiante;
	}

	public void setIdEstudiante(Long idEstudiante) {
		this.idEstudiante = idEstudiante;
	}

	public String getFormaParticipacion() {
		return formaParticipacion;
	}

	public void setFormaParticipacion(String formaParticipacion) {
		this.formaParticipacion = formaParticipacion;
	}

	public Date getAnoParticipaEst() {
		return anoParticipaEst;
	}

	public void setAnoParticipaEst(Date anoParticipaEst) {
		this.anoParticipaEst = anoParticipaEst;
	}
}
