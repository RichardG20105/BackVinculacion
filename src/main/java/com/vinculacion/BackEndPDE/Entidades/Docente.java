package com.vinculacion.BackEndPDE.Entidades;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "Docente")
public class Docente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "iddocente")
	private Long idDocente;

	@Column(name = "idcarrera")
	private Long idCarrera;

	@Column(name = "ceduladocente")
	private String cedulaDocente;

	@Column(name = "nombredocente")
	private String nombreDocente;

	@Column(name = "contacto")
	private String contacto;

	@Column(name = "correoelectronico")
	private String correoElectronico;

	@Column(name = "relacionlaboral")
	private String relacionLaboral;

	@Column(name = "sexodocente")
	private String sexoDocente;

	@OneToMany(mappedBy="docente", cascade = CascadeType.ALL)
	private Set<Participa> participas;


	public Docente() {
		super();
	}

	public Docente(String cedulaDocente, String nombreDocente, String contacto, String correoElectronico,
			String relacionLaboral, String sexoDocente, Long idCarrera, Participa... participas) {
		super();
		this.cedulaDocente = cedulaDocente;
		this.nombreDocente = nombreDocente;
		this.contacto = contacto;
		this.correoElectronico = correoElectronico;
		this.relacionLaboral = relacionLaboral;
		this.sexoDocente = sexoDocente;
		this.idCarrera = idCarrera;
		for(Participa participa : participas) participa.setDocente(this);
		this.participas = Stream.of(participas).collect(Collectors.toSet());
	}

	public Long getIdDocente() {
		return idDocente;
	}

	public void setIdDocente(Long idDocente) {
		this.idDocente = idDocente;
	}

	public String getCedulaDocente() {
		return cedulaDocente;
	}

	public void setCedulaDocente(String cedulaDocente) {
		this.cedulaDocente = cedulaDocente;
	}

	public String getNombreDocente() {
		return nombreDocente;
	}

	public void setNombreDocente(String nombreDocente) {
		this.nombreDocente = nombreDocente;
	}

	public String getContacto() {
		return contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getRelacionLaboral() {
		return relacionLaboral;
	}

	public void setRelacionLaboral(String relacionLaboral) {
		this.relacionLaboral = relacionLaboral;
	}

	public String getSexoDocente() {
		return sexoDocente;
	}

	public void setSexoDocente(String sexoDocente) {
		this.sexoDocente = sexoDocente;
	}

	public Long getIdCarrera() {
		return idCarrera;
	}

	public void setIdCarrera(Long idCarrera) {
		this.idCarrera = idCarrera;
	}

	public void setParticipas(Set<Participa> participas) {
		this.participas = participas;
	}
}
