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

import com.vinculacion.BackEndPDE.Entidades.Participa;
import com.vinculacion.BackEndPDE.Entidades.Proyecto;
import com.vinculacion.BackEndPDE.Excepciones.ResourceNotFoundException;
import com.vinculacion.BackEndPDE.Repositorio.RepositorioParticipa;

@RestController
@RequestMapping("/Participa/")
public class ControladorParticipa {
	@Autowired
	private RepositorioParticipa RepositorioParticipa;
	
	@GetMapping("Listado")
	public List<Participa> getListado()throws ResourceNotFoundException{
		List<Participa> participacion = RepositorioParticipa.findAll();
		if(participacion.isEmpty())
			throw new ResourceNotFoundException("No hay participaciones registradas");
		return participacion;
	}
	
	@GetMapping("{id}")
	public Participa getDocenteParticipacion(@PathVariable(value = "id")Long idParticipacion)throws ResourceNotFoundException {
		Participa docente = RepositorioParticipa.findById(idParticipacion)
				.orElseThrow(() -> new ResourceNotFoundException("No existe la participacion con ese Id"));
		return docente;
	}
	
	@PostMapping("ListarParticipacion")
	public List<Participa> getParticipa(@Valid @RequestBody Proyecto proyecto)throws ResourceNotFoundException{
		List<Participa> participa = RepositorioParticipa.findAllByProyectoAndCargo(proyecto,"Integrante");
		if(participa.isEmpty())
			throw new ResourceNotFoundException("No existen Docentes registrados en este Proyecto");
		return participa;
	}
	
	@PostMapping("Coordinador")
	public Participa getCoordinador(@Valid @RequestBody Proyecto proyecto)throws ResourceNotFoundException{
		Participa participa = RepositorioParticipa.findByProyectoAndCargo(proyecto,"Coordinador")
				.orElseThrow(() -> new ResourceNotFoundException("No existe un coordinador en este Proyecto"));
		return participa;
	}
	
	@PostMapping("Registrar")
	public Participa setParticipa(@Valid @RequestBody Participa participa) throws ResourceNotFoundException{
		return this.RepositorioParticipa.save(participa);
	}
	/*@GetMapping("ListarProyectoDocente/{id}")
	public List<Optional<Object>> getParticipa(@PathVariable(value = "id")Long IDProyecto)throws ResourceNotFoundException{
		List<Optional<Object>> relacion = RepositorioParticipa.findAllByPartipacion(IDProyecto);
		if(relacion.isEmpty()) {
			throw new ResourceNotFoundException("No existe docentes en este proyecto");
		}
		return relacion;
	}*/
	
	@PutMapping("Actualizar/{id}")
	public ResponseEntity<Participa> putParticipa(@PathVariable(value = "id")Long IDParticipa,@Valid @RequestBody Participa participa)throws ResourceNotFoundException{
		Participa participaAct = RepositorioParticipa.findById(IDParticipa)
				.orElseThrow(() -> new ResourceNotFoundException("No existe una participacion con este ID"));
		List<Participa> participaciones = RepositorioParticipa.findAllByDocenteAndProyectoAndAnioParticipaDoc(participa.getDocente(),participa.getProyecto(),participa.getAnioParticipaDoc());
		if(participaciones.size() > 1) {
			throw new ResourceNotFoundException("El docente ya se encuentra en 2 proyectos");
		}
		participaAct.setAnioParticipaDoc(participa.getAnioParticipaDoc());
		participaAct.setHorasParticipacion(participa.getHorasParticipacion());
		
		return ResponseEntity.ok(this.RepositorioParticipa.save(participaAct));
	}
}
