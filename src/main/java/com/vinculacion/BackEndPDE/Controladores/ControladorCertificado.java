package com.vinculacion.BackEndPDE.Controladores;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

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

	@GetMapping("ListarCertificadosIntegra")
	public List<Certificado> getCertificadosIntegra()throws ResourceNotFoundException{
		List<Certificado> Certificados = RepositorioCertificado.findAllByParticipaIsNull();
		if(Certificados.isEmpty())
			throw new ResourceNotFoundException("No existen certificados de Estudiantes");
		return Certificados;
	}

	@GetMapping("ListarCertificadosParticipa")
	public List<Certificado> getCertificadosParticipa()throws ResourceNotFoundException {
		List<Certificado> certificados = RepositorioCertificado.findAllByIntegraIsNull();
		if(certificados.isEmpty())
			throw new ResourceNotFoundException("No existen certificados de Docentes");
		return certificados;
	}

	@GetMapping("ListadoDocente/{Facultad}")
	public List<Certificado> getCertificadosParticipaFacultad(@PathVariable(value = "Facultad")String Facultad)throws ResourceNotFoundException{
		List<Certificado> certificados = RepositorioCertificado.findAllByIntegraIsNullAndFacultadIntegrante(Facultad);

		if(certificados.isEmpty())
			throw new ResourceNotFoundException("No existen certificados de Docentes de esa facultad");
		return certificados;
	}

	@GetMapping("ListadoEstudiante/{Facultad}")
	public List<Certificado> getCertificadosIntegraFacultad(@PathVariable(value = "Facultad")String Facultad)throws ResourceNotFoundException{
		List<Certificado> certificados = RepositorioCertificado.findAllByParticipaIsNullAndFacultadIntegrante(Facultad);

		if(certificados.isEmpty())
			throw new ResourceNotFoundException("No existen certificados de Estudiantes de esa facultad");
		return certificados;
	}

	@GetMapping("{id}")
	public Certificado getCertificado(@PathVariable(value = "id")Long IDCertificado)throws ResourceNotFoundException{
		Certificado certificado = RepositorioCertificado.findById(IDCertificado)
				.orElseThrow(() -> new ResourceNotFoundException("No se encontro el Certificado con ese ID"));
		return certificado;
	}

	@GetMapping("ListadoCertificadoDocente/{observacion}")
	public List<Certificado> getCertificadosDocenteObservacion(@PathVariable(value = "observacion")String Observacion)throws ResourceNotFoundException{
		List<Certificado> certificados = RepositorioCertificado.findAllByIntegraIsNullAndObservacionCertificado(Observacion);

		if(certificados.isEmpty())
			throw new ResourceNotFoundException("No existen certificados de Docentes con es observacion");
		return certificados;
	}
	
	@GetMapping("ListadoCertificadoEstudiante/{observacion}")
	public List<Certificado> getCertificadosEstudianteObservacion(@PathVariable(value = "observacion")String Observacion)throws ResourceNotFoundException{
		List<Certificado> certificados = RepositorioCertificado.findAllByParticipaIsNullAndObservacionCertificado(Observacion);

		if(certificados.isEmpty())
			throw new ResourceNotFoundException("No existen certificados de Estudiantes con es observacion");
		return certificados;
	}

	@PostMapping("Registrar")
	public Certificado setCertificado(@Valid @RequestBody Certificado certificado)throws ResourceNotFoundException{
		Calendar fecha = Calendar.getInstance(TimeZone.getTimeZone("GMT-5"));
		fecha.add(Calendar.DATE, 0);
		
		String Codigo;
		
		if(certificado.getParticipa() != null) {
			if(RepositorioCertificado.existsByParticipa(certificado.getParticipa())){
				throw new ResourceNotFoundException("Ya existe un certificado generado");
			}
			
			if(certificado.getParticipa().getAnioParticipaDoc() == null) {
				throw new ResourceNotFoundException("Se necesita deifinir las fechas de Participación del Docente");
			} 
			
			Codigo = "DV-" + certificado.getParticipa().getDocente().getIdDocente() + certificado.getParticipa().getIdParticipa() + 
					fecha.get(Calendar.DAY_OF_MONTH) + fecha.get(Calendar.DAY_OF_WEEK) + fecha.get(Calendar.HOUR_OF_DAY) + fecha.get(Calendar.MINUTE);;
			
			certificado.setCodigoCertificado(Codigo);
			return this.RepositorioCertificado.save(certificado);
		}
		
		if(RepositorioCertificado.existsByIntegra(certificado.getIntegra())) {
			throw new ResourceNotFoundException("Ya existe un certificado generado");
		}
		
		if(certificado.getIntegra().getFormaParticipacion() == null) {
			throw new ResourceNotFoundException("Se necesita definir la Forma de Participacion del Estudiante");
		}
		
		Codigo = "DV-" + certificado.getIntegra().getEstudiante().getIdEstudiante() + certificado.getIntegra().getIdIntegra() + 
				fecha.get(Calendar.DAY_OF_MONTH) + fecha.get(Calendar.DAY_OF_WEEK) + fecha.get(Calendar.HOUR_OF_DAY) + fecha.get(Calendar.MINUTE);
		
		certificado.setCodigoCertificado(Codigo);
		return this.RepositorioCertificado.save(certificado);
	}
	
	@GetMapping("ValidarCertificado/{codigo}")
	public Certificado getCertificado(@PathVariable(value = "codigo") String codigo)throws ResourceNotFoundException{
		Certificado certificado = RepositorioCertificado.findByCodigoCertificado(codigo)
				.orElseThrow(() -> new ResourceNotFoundException("No existe un certificado con ese codigo"));
		
		return certificado;
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
