package com.vinculacion.BackEndPDE.Controladores;

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

import com.vinculacion.BackEndPDE.Entidades.Facultad;
import com.vinculacion.BackEndPDE.Entidades.Integra;
import com.vinculacion.BackEndPDE.Entidades.Proyecto;
import com.vinculacion.BackEndPDE.Excepciones.ResourceNotFoundException;
import com.vinculacion.BackEndPDE.Repositorio.RepositorioCarrera;
import com.vinculacion.BackEndPDE.Repositorio.RepositorioFacultad;
import com.vinculacion.BackEndPDE.Repositorio.RepositorioIntegra;

@RestController
@RequestMapping("/Integra/")
public class ControladorIntegra {
	@Autowired
	private RepositorioIntegra RepositorioIntegra;

	@Autowired
	private RepositorioFacultad RepositorioFacultad;

	@Autowired
	private RepositorioCarrera RepositorioCarrera;


	@GetMapping("Listado")
	public List<Integra> getListado() throws ResourceNotFoundException{
		List<Integra> integra = RepositorioIntegra.findAll();
		if(integra.isEmpty())
			throw new ResourceNotFoundException("No hay estudiantes registrados en proyectos");
		return integra;
	}

	@GetMapping("Listado/{Facultad}")
	public List<Integra> getListadoFacultad(@PathVariable(value = "Facultad")String Facultad) throws ResourceNotFoundException{
		Long idCarrera1, idCarrera2;

		Facultad facultad = RepositorioFacultad.findByNombreFacultad(Facultad);
		idCarrera1 = RepositorioCarrera.findTopByIdFacultad(facultad.getIdFacultad()).getIdCarrera();
		idCarrera2 = RepositorioCarrera.findLastByIdFacultad(facultad.getIdFacultad()).getIdCarrera();

		List<Integra> Integra = RepositorioIntegra.findEstudianteByEstudiante_idCarreraBetween(idCarrera1, idCarrera2);

		if(Integra.isEmpty())
			throw new ResourceNotFoundException("No hay estudiantes de esta facultad");
		return Integra;
	}

	@GetMapping("{id}")
	public Integra getEstudianteIntegra(@PathVariable(value = "id")Long idIntegra)throws ResourceNotFoundException{
		Integra estudiante = RepositorioIntegra.findById(idIntegra)
				.orElseThrow(() -> new ResourceNotFoundException("No existe un estudiante con ese Id"));
		return estudiante;
	}

	@PostMapping("ListarIntegracion")
	public List<Integra> getIntegra(@Valid @RequestBody Proyecto proyecto)throws ResourceNotFoundException{
		List<Integra> integra = RepositorioIntegra.findAllByProyecto(proyecto);
		if(integra.isEmpty())
			throw new ResourceNotFoundException("No existen estudiante registrados en este proyecto");
		return integra;
	}
	/*@GetMapping("ListarProyectoEstudiante/{id}")
	public List<Integra> getIntegra(@PathVariable(value = "id")Long IDProyecto)throws ResourceNotFoundException{
		List<Integra> relacion = RepositorioIntegra.findAllByIdProyecto(IDProyecto);
		if(relacion.isEmpty()) {
			throw new ResourceNotFoundException("No existe estudiantes en este proyecto");
		}
		return relacion;
	}*/
	@PostMapping("Registrar")
	public Integra setIntegra(@Valid @RequestBody Integra integra)throws ResourceNotFoundException{
		return this.RepositorioIntegra.save(integra);
	}

	@PutMapping("Actualizar/{id}")
	public ResponseEntity<Integra> putIntegra(@PathVariable(value = "id")Long IDIntegra,@Valid @RequestBody Integra integra)throws ResourceNotFoundException{
		Integra integraAct = RepositorioIntegra.findById(IDIntegra)
				.orElseThrow(() -> new ResourceNotFoundException("No existe un Docente en un Proyecto con ese ID"));

		integraAct.setIntegraInicio(integra.getIntegraInicio());
		integraAct.setIntegraFinal(integra.getIntegraFinal());
		integraAct.setFormaParticipacion(integra.getFormaParticipacion());

		return ResponseEntity.ok(this.RepositorioIntegra.save(integraAct));
	}

	@DeleteMapping("Eliminar/{id}")
	public Map<String, Boolean> deleteIntegra(@PathVariable(value = "id")Long IDIntegra)throws ResourceNotFoundException{
		Integra integra = RepositorioIntegra.findById(IDIntegra)
				.orElseThrow(() -> new ResourceNotFoundException("No existe la relacion con ese ID"));

		this.RepositorioIntegra.delete(integra);
		Map<String, Boolean> response = new HashMap<>();
		response.put("El estudiante se elimino correctamente del proyecto", Boolean.TRUE);
		return response;
	}
}
