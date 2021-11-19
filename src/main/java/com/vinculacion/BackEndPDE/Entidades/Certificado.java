package com.vinculacion.BackEndPDE.Entidades;

import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name = "Certificado")
public class Certificado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idcertificado")
	private Long idCertificado;
	
	@Column(name = "idproyecto")
	private Long idProyecto;
	
	@Column(name = "cedulaintegrante")
	private String cedulaIntegrante;
	
	@Column(name = "fechaentrega")
	private Date fechaEntrega;
	
	@Column(name = "fecharecepcion")
	private Date fechaRecepcion;
	
	@Column(name = "observacioncertificado")
	private String observacionCertificado;

	public Certificado() {
		super();
	}

	public Certificado(Long idProyecto, String cedulaIntegrante, Date fechaEntrega, Date fechaRecepcion,
			String observacionCertificado) {
		super();
		this.idProyecto = idProyecto;
		this.cedulaIntegrante = cedulaIntegrante;
		this.fechaEntrega = fechaEntrega;
		this.fechaRecepcion = fechaRecepcion;
		this.observacionCertificado = observacionCertificado;
	}

	public Long getIdCertificado() {
		return idCertificado;
	}

	public void setIdCertificado(Long idCertificado) {
		this.idCertificado = idCertificado;
	}

	public Long getIdProyecto() {
		return idProyecto;
	}

	public void setIdProyecto(Long idProyecto) {
		this.idProyecto = idProyecto;
	}

	public String getCedulaIntegrante() {
		return cedulaIntegrante;
	}

	public void setCedulaIntegrante(String cedulaIntegrante) {
		this.cedulaIntegrante = cedulaIntegrante;
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

	public String getObservacionCertificado() {
		return observacionCertificado;
	}

	public void setObservacionCertificado(String observacionCertificado) {
		this.observacionCertificado = observacionCertificado;
	}
}
