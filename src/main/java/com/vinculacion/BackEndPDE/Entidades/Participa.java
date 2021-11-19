package com.vinculacion.BackEndPDE.Entidades;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "Participa")
public class Participa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idparticipa")
	private Long idParticipa;
	
	@Column(name = "idproyecto")
	private Long idProyecto;
	
	@Column(name = "iddocente")
	private Long idDocente;
	
	@Column(name = "cargo")
	private String cargo;
	
	@Column(name = "horasParticion")
	private int horasParticipacion;
	
	@Column(name = "anoparticipadoc")
	private Date anioParticipaDoc;

	public Participa() {
		super();
	}

	public Participa(Long idProyecto, Long idDocente, String cargo, int horasParticipacion, Date anioParticipaDoc) {
		super();
		this.idProyecto = idProyecto;
		this.idDocente = idDocente;
		this.cargo = cargo;
		this.horasParticipacion = horasParticipacion;
		this.anioParticipaDoc = anioParticipaDoc;
	}

	public Long getIdParticipa() {
		return idParticipa;
	}

	public void setIdParticipa(Long idParticipa) {
		this.idParticipa = idParticipa;
	}

	public Long getIdProyecto() {
		return idProyecto;
	}

	public void setIdProyecto(Long idProyecto) {
		this.idProyecto = idProyecto;
	}

	public Long getIdDocente() {
		return idDocente;
	}

	public void setIdDocente(Long idDocente) {
		this.idDocente = idDocente;
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
}