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
@Table(name = "Participa")
public class Participa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idparticipa")
	private Long idParticipa;

	@Column(name = "facultad")
	private String facultad;

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

	@OneToMany(mappedBy = "participa", cascade = CascadeType.ALL)
	private Set<Certificado> certificados = new HashSet<>();

	public Participa() {
		super();
	}

	public Participa(String facultad, String cargo, int horasParticipacion, Date anioParticipaDoc, Docente docente,
			Proyecto proyecto, Certificado... certificados) {
		super();
		this.facultad = facultad;
		this.cargo = cargo;
		this.horasParticipacion = horasParticipacion;
		this.anioParticipaDoc = anioParticipaDoc;
		this.docente = docente;
		this.proyecto = proyecto;
		for(Certificado certificado: certificados) certificado.setParticipa(this);
		this.certificados = Stream.of(certificados).collect(Collectors.toSet());
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

	public String getFacultad() {
		return facultad;
	}

	public void setFacultad(String facultad) {
		this.facultad = facultad;
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