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

import com.vinculacion.BackEndPDE.Entidades.Estudiante;
import com.vinculacion.BackEndPDE.Entidades.Facultad;
import com.vinculacion.BackEndPDE.Excepciones.ResourceNotFoundException;
import com.vinculacion.BackEndPDE.Repositorio.RepositorioCarrera;
import com.vinculacion.BackEndPDE.Repositorio.RepositorioEstudiante;
import com.vinculacion.BackEndPDE.Repositorio.RepositorioFacultad;
@RestController
@RequestMapping("/Estudiante/")
public class ControladorEstudiante {
	@Autowired
	private RepositorioEstudiante RepositorioEstudiante;

	@Autowired
	private RepositorioFacultad RepositorioFacultad;

	@Autowired
	private RepositorioCarrera RepositorioCarrera;

	@GetMapping("ListarEstudiantes")
	public List<Estudiante> getEstudiantes()throws ResourceNotFoundException{
		List<Estudiante> Estudiantes = RepositorioEstudiante.findAllByOrderByIdEstudianteDesc();

		if(Estudiantes.isEmpty())
			throw new ResourceNotFoundException("No se encontraron Estudiantes registrados");
		return Estudiantes;
	}

	@GetMapping("{id}")
	public Estudiante getEstudiante(@PathVariable(value = "id")Long IDEstudiante)throws ResourceNotFoundException{
		Estudiante estudiante = RepositorioEstudiante.findById(IDEstudiante)
				.orElseThrow(() -> new ResourceNotFoundException("No se encontro el Estudiante con ese ID"));
		return estudiante;
	}

	@GetMapping("Listado/{facultad}/{sexo}")
	public List<Estudiante> getEstudianteFacultadSexo(@PathVariable(value = "facultad")String Facultad, @PathVariable(value = "sexo")String SexoEstudiante)throws ResourceNotFoundException{
		Long idCarrera1, idCarrera2;

		Facultad facultad = RepositorioFacultad.findByNombreFacultad(Facultad);
		idCarrera1 = RepositorioCarrera.findTopByIdFacultad(facultad.getIdFacultad()).getIdCarrera();
		idCarrera2 = RepositorioCarrera.findLastByIdFacultad(facultad.getIdFacultad()).getIdCarrera();

		List<Estudiante> estudiantes = RepositorioEstudiante.findAllBySexoEstudianteAndIdCarreraBetween(SexoEstudiante,idCarrera1,idCarrera2);

		if(estudiantes.isEmpty())
			throw new ResourceNotFoundException("No existen Estudiantes");
		return estudiantes;
	}

	@GetMapping("Cedula/{cedula}")
	public Estudiante getEstudianteCedula(@PathVariable(value = "cedula")String cedula)throws ResourceNotFoundException{
		if(RepositorioEstudiante.existsByCedulaEstudiante(cedula)) {
			Estudiante estudiante = RepositorioEstudiante.findByCedulaEstudiante(cedula);
			return estudiante;
		}else {
			throw new ResourceNotFoundException("No existe un estudiante con ese n??mero de cedula");
		}
	}

	@PostMapping("Registrar")
	public Estudiante setEstudiante(@Valid @RequestBody Estudiante estudiante)throws ResourceNotFoundException{
		if(RepositorioEstudiante.existsByCedulaEstudiante(estudiante.getCedulaEstudiante())) {
			throw new ResourceNotFoundException("Ya existe un estudiante con esa Cedula");
		}
		return this.RepositorioEstudiante.save(estudiante);
	}

	@PutMapping("Actualizar/{id}")
	public ResponseEntity<Estudiante> putEstudiante(@PathVariable(value = "id")Long IDEstudiante, @Valid @RequestBody Estudiante estudiante)throws ResourceNotFoundException{
		Estudiante estudianteAct = RepositorioEstudiante.findById(IDEstudiante)
				.orElseThrow(() -> new ResourceNotFoundException("No existe un estudiante con ese ID"));
		if(RepositorioEstudiante.existsByCedulaEstudiante(estudiante.getCedulaEstudiante())) {
			Estudiante estudianteComp = RepositorioEstudiante.findByCedulaEstudiante(estudiante.getCedulaEstudiante());
			if(estudianteComp.getIdEstudiante() != IDEstudiante) {
				throw new ResourceNotFoundException("Ya existe un estudiante con esa Cedula");
			}
		}

		estudianteAct.setCedulaEstudiante(estudiante.getCedulaEstudiante());
		estudianteAct.setNombreEstudiante(estudiante.getNombreEstudiante());
		estudianteAct.setSemestre(estudiante.getSemestre());
		estudianteAct.setSexoEstudiante(estudiante.getSexoEstudiante());
		estudianteAct.setIdCarrera(estudiante.getIdCarrera());

		return ResponseEntity.ok(this.RepositorioEstudiante.save(estudianteAct));
	}

	@DeleteMapping("Eliminar/{id}")
	public Map<String, Boolean> deleteEstudiante(@PathVariable(value = "id")Long IDEstudiante)throws ResourceNotFoundException{
		Estudiante estudiante = RepositorioEstudiante.findById(IDEstudiante)
				.orElseThrow(() -> new ResourceNotFoundException("No existe un estudiante con ese ID"));
		this.RepositorioEstudiante.delete(estudiante);
		Map<String, Boolean> response = new HashMap<>();
		response.put("El estudiante se elimino correctamente", Boolean.TRUE);
		return response;
	}
}
