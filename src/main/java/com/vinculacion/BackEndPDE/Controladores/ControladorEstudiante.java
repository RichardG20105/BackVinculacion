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
import com.vinculacion.BackEndPDE.Excepciones.ResourceNotFoundException;
import com.vinculacion.BackEndPDE.Repositorio.RepositorioEstudiante;
@RestController
@RequestMapping("/Estudiante/")
public class ControladorEstudiante {
	@Autowired
	private RepositorioEstudiante RepositorioEstudiante;
	
	@GetMapping("ListarEstudiantes")
	public List<Estudiante> getEstudiantes()throws ResourceNotFoundException{
		List<Estudiante> Estudiantes = RepositorioEstudiante.findAll();
		
		if(Estudiantes.isEmpty())
			throw new ResourceNotFoundException("No se encontraron Estudiantes registrados");
		return Estudiantes;
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
		estudianteAct.setGeneroEstudiante(estudiante.getGeneroEstudiante());
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
