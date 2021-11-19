package com.vinculacion.BackEndPDE.Entidades;

import javax.persistence.*;

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
	
	@Column(name = "generodocente")
	private String generoDocente;
	
	public Docente() {
		super();
	}
	
	public Docente(String cedulaDocente, String nombreDocente, String contacto, String correoElectronico,
			String relacionLaboral, String generoDocente, Long idCarrera) {
		super();
		this.cedulaDocente = cedulaDocente;
		this.nombreDocente = nombreDocente;
		this.contacto = contacto;
		this.correoElectronico = correoElectronico;
		this.relacionLaboral = relacionLaboral;
		this.generoDocente = generoDocente;
		this.idCarrera = idCarrera;
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

	public String getGeneroDocente() {
		return generoDocente;
	}

	public void setGeneroDocente(String generoDocente) {
		this.generoDocente = generoDocente;
	}

	public Long getIdCarrera() {
		return idCarrera;
	}

	public void setIdCarrera(Long idCarrera) {
		this.idCarrera = idCarrera;
	}
}
