package com.vinculacion.BackEndPDE.Controladores;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinculacion.BackEndPDE.Entidades.Certificado;
import com.vinculacion.BackEndPDE.Excepciones.ResourceNotFoundException;
import com.vinculacion.BackEndPDE.Repositorio.RepositorioCertificado;

@RestController
@RequestMapping("/Certificado/")
public class ControladorCertificado {
	@Autowired
	private RepositorioCertificado RepositorioCertificado;
	
	@GetMapping("ListarCertificados")
	public List<Certificado> getCertificados(){
		List<Certificado> Certificados = RepositorioCertificado.findAll();
		
		if(Certificados.isEmpty())
			new ResourceNotFoundException("No existen Certificados almacenados.");
		return Certificados;
	}
	
	@PostMapping("Registrar")
	public Certificado setCertificado(@Valid @RequestBody Certificado certificado)throws ResourceNotFoundException{
		if(RepositorioCertificado.existsByIdProyectoAndCedulaIntegrante(certificado.getIdProyecto(),certificado.getCedulaIntegrante())) {
			throw new ResourceNotFoundException("Ya existe un certificado generado");
		}
		return this.RepositorioCertificado.save(certificado);
	}
	
	@PutMapping("Actualizar/{id}")
	public ResponseEntity<Certificado> putCertificado(@PathVariable(value = "id")Long IDCertificado, @Valid @RequestBody Certificado certificado)throws ResourceNotFoundException{
		Certificado certificadoAct = RepositorioCertificado.findById(IDCertificado)
				.orElseThrow(() -> new ResourceNotFoundException("No existe un certificado con ese ID"));
		
		certificadoAct.setFechaEntrega(certificado.getFechaEntrega());
		certificadoAct.setFechaRecepcion(certificado.getFechaRecepcion());
		certificadoAct.setObservacionCertificado(certificado.getObservacionCertificado());
		
		return ResponseEntity.ok(this.RepositorioCertificado.save(certificadoAct));
	}
}
