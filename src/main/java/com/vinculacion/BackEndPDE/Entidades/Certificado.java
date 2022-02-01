package com.vinculacion.BackEndPDE.Entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "Certificado")
public class Certificado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idcertificado")
	private Long idCertificado;

	@Column(name = "fechaentrega")
	private Date fechaEntrega;

	@Column(name = "fecharecepcion")
	private Date fechaRecepcion;

	@Column(name = "facultadintegrante")
	private String facultadIntegrante;

	@Column(name = "observacioncertificado")
	private String observacionCertificado;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "idparticipa", referencedColumnName = "idparticipa")
	private Participa participa;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "idintegra", referencedColumnName = "idintegra")
	private Integra integra;

	public Certificado() {
		super();
	}

	public Certificado(Date fechaEntrega, Date fechaRecepcion, String facultadIntegrante,String observacionCertificado,
			Participa participa, Integra integra) {
		super();
		this.fechaEntrega = fechaEntrega;
		this.fechaRecepcion = fechaRecepcion;
		this.facultadIntegrante = facultadIntegrante;
		this.observacionCertificado = observacionCertificado;
		this.participa = participa;
		this.integra = integra;
	}

	public Long getIdCertificado() {
		return idCertificado;
	}

	public void setIdCertificado(Long idCertificado) {
		this.idCertificado = idCertificado;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public Date getFechaRecepcion() {
		return fechaRecepcion;
	}

	public void setFechaRecepcion(Date fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}

	public String getFacultadIntegrante() {
		return facultadIntegrante;
	}

	public void setFacultadIntegrante(String facultadIntegrante) {
		this.facultadIntegrante = facultadIntegrante;
	}

	public String getObservacionCertificado() {
		return observacionCertificado;
	}

	public void setObservacionCertificado(String observacionCertificado) {
		this.observacionCertificado = observacionCertificado;
	}

	public Participa getParticipa() {
		return participa;
	}

	public void setParticipa(Participa participa) {
		this.participa = participa;
	}

	public Integra getIntegra() {
		return integra;
	}

	public void setIntegra(Integra integra) {
		this.integra = integra;
	}
}
