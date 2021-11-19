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
import com.vinculacion.BackEndPDE.Excepciones.ResourceNotFoundException;
import com.vinculacion.BackEndPDE.Repositorio.RepositorioParticipa;

@RestController
@RequestMapping("/Participa/")
public class ControladorParticipa {
	@Autowired
	private RepositorioParticipa RepositorioParticipa;
	
	@GetMapping("ListarProyectoDocente/{id}")
	public List<Participa> getParticipa(@PathVariable(value = "id")Long IDProyecto)throws ResourceNotFoundException{
		List<Participa> relacion = RepositorioParticipa.findByIdProyecto(IDProyecto);
		if(relacion.isEmpty()) {
			throw new ResourceNotFoundException("No existe docentes en este proyecto");
		}
		return relacion;
	}
	
	
	@PostMapping("Registrar")
	public Participa setParticipa(@Valid @RequestBody Participa participa)throws ResourceNotFoundException{
		if(RepositorioParticipa.existsByIdProyectoAndIdDocenteAndAnioParticipaDoc(participa.getIdProyecto(),participa.getIdDocente(), participa.getAnioParticipaDoc())){
			throw new ResourceNotFoundException("Ya existe un Docente que participa en este Proyecto y en este mismo Año");
		}
		
		List<Participa> participaComp = RepositorioParticipa.findAllByIdProyectoAndIdDocenteAndAnioParticipacionDocente(participa.getIdProyecto(),participa.getIdDocente(),participa.getAnioParticipaDoc());
		if(participaComp.size() >= 2) {
			throw new ResourceNotFoundException("Este docente ya esta participando en dos Proyectos este Año");
		}
		
		return this.RepositorioParticipa.save(participa);
	}
	
	@PutMapping("Actualizar/{id}")
	public ResponseEntity<Participa> putParticipa(@PathVariable(value = "id")Long IDParticipa,@Valid @RequestBody Participa participa)throws ResourceNotFoundException{
		Participa participaAct = RepositorioParticipa.findById(IDParticipa)
				.orElseThrow(() -> new ResourceNotFoundException("No existe una participacion con este ID"));
		
		participaAct.setAnioParticipaDoc(participa.getAnioParticipaDoc());
		participaAct.setHorasParticipacion(participa.getHorasParticipacion());
		
		return ResponseEntity.ok(this.RepositorioParticipa.save(participaAct));	
	}
}
