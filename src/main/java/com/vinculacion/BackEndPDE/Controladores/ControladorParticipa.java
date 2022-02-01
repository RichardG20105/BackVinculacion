package com.vinculacion.BackEndPDE.Controladores;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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

	@GetMapping("Listado/{facultad}")
	public List<Participa> getListadoFacultad(@PathVariable(value = "facultad")String Facultad)throws ResourceNotFoundException{
		List<Participa> participacion = RepositorioParticipa.findAllByFacultad(Facultad);
		if(participacion.isEmpty())
			throw new ResourceNotFoundException("No existen participaciones de docentes de es Facultad");
		return participacion;
	}

	@GetMapping("Lista/{relacionLaboral}")
	public List<Participa> getListadoRelacionLaboral(@PathVariable(value = "relacionLaboral")String RelacionLaboral)throws ResourceNotFoundException{
		List<Participa> participacion = RepositorioParticipa.findDocenteDistinctByDocente_relacionLaboral(RelacionLaboral);
		return participacion;
	}

	//@GetMapping("Lista/{relacionLaboral}/{anioDefinido}")

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

	@PutMapping("Actualizar/{id}")
	public ResponseEntity<Participa> putParticipa(@PathVariable(value = "id")Long IDParticipa,@Valid @RequestBody Participa participa)throws ResourceNotFoundException{
		Participa participaAct = RepositorioParticipa.findById(IDParticipa)
				.orElseThrow(() -> new ResourceNotFoundException("No existe una participacion con este ID"));
		SimpleDateFormat getAnio = new SimpleDateFormat("yyyy");
		int anio = Integer.parseInt(getAnio.format(participa.getAnioParticipaDoc()));
		List<Participa> participaciones = RepositorioParticipa.findAllByDocenteAnioParticipa(participa.getProyecto().getIdProyecto(),participa.getDocente().getIdDocente(),anio);
		if(participaciones.size() > 1) {
			throw new ResourceNotFoundException("El docente ya se encuentra en 2 proyectos");
		}
		participaAct.setAnioParticipaDoc(participa.getAnioParticipaDoc());
		participaAct.setHorasParticipacion(participa.getHorasParticipacion());

		return ResponseEntity.ok(this.RepositorioParticipa.save(participaAct));
	}

	@DeleteMapping("Eliminar/{id}")
	public Map<String, Boolean> deleteParticipa(@PathVariable(value = "id")Long IdParticipa)throws ResourceNotFoundException{
		Participa participa = RepositorioParticipa.findById(IdParticipa)
				.orElseThrow(() -> new ResourceNotFoundException("No existe una participacion con ese ID"));

		this.RepositorioParticipa.delete(participa);
		Map<String,Boolean> response = new HashMap<>();
		response.put("El docente se elmino correctamente del proyecto", Boolean.TRUE);
		return response;
	}
}
