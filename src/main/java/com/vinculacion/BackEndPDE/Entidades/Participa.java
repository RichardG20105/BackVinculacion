package com.vinculacion.BackEndPDE.Entidades;

import java.util.Date;
import java.util.HashMap;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "Participa")
public class Participa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idparticipa")
	private Long idParticipa;
	
	
	@Column(name = "cargo")
	private String cargo;
	
	@Column(name = "horasparticipacion")
	private int horasParticipacion;
	
	@Column(name = "anioparticipadoc")
	private Date anioParticipaDoc;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "iddocente", referencedColumnName = "iddocente")
	private Docente docente;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "idproyecto", referencedColumnName = "idproyecto")
	private Proyecto proyecto;

	public Participa() {
		super();
	}

	public Participa(String cargo, int horasParticipacion, Date anioParticipaDoc,
			Docente docente) {
		super();
		this.cargo = cargo;
		this.horasParticipacion = horasParticipacion;
		this.anioParticipaDoc = anioParticipaDoc;
		this.docente = docente;
	}

	public Long getIdParticipa() {
		return idParticipa;
	}

	public void setIdParticipa(Long idParticipa) {
		this.idParticipa = idParticipa;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public int getHorasParticipacion() {
		return horasParticipacion;
	}

	public void setHorasParticipacion(int horasParticipacion) {
		this.horasParticipacion = horasParticipacion;
	}

	public Date getAnioParticipaDoc() {
		return anioParticipaDoc;
	}

	public void setAnioParticipaDoc(Date anioParticipaDoc) {
		this.anioParticipaDoc = anioParticipaDoc;
	}

	public Docente getDocente() {
		return docente;
	}

	public void setDocente(Docente docente) {
		this.docente = docente;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}
	
}