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

import com.vinculacion.BackEndPDE.Entidades.Proyecto;
import com.vinculacion.BackEndPDE.Excepciones.ResourceNotFoundException;
import com.vinculacion.BackEndPDE.Repositorio.RepositorioProyecto;
@RestController
@RequestMapping("/Proyecto/")
public class ControladorProyecto {
	@Autowired
	private RepositorioProyecto RepositorioProyecto;

	@GetMapping("ListarProyectos")
	public List<Proyecto> getProyectos(){
		List<Proyecto> Proyectos = RepositorioProyecto.findAllByOrderByIdProyectoDesc();

		if(Proyectos.isEmpty())
			new ResourceNotFoundException("No se encontraron Proyectos almacenados");
		return Proyectos;
	}

	@GetMapping("Listado/Facultad/{facultad}")
	public List<Proyecto> getProyectosFacultad(@PathVariable(name = "facultad")String Facultad)throws ResourceNotFoundException{
		List<Proyecto> proyectos = RepositorioProyecto.findAllByFacultad_nombreFacultad(Facultad);
		if(proyectos.isEmpty()) {
			throw new ResourceNotFoundException("No existen proyectos de esa Facultad");
		}

		return proyectos;
	}

	@GetMapping("Listado/Carrera/{carrera}")
	public List<Proyecto> getProyectosCarrera(@PathVariable(name = "carrera")String Carrera)throws ResourceNotFoundException{
		List<Proyecto> proyectos = RepositorioProyecto.findAllByCarreras_nombreCarrera(Carrera);

		if(proyectos.isEmpty()) {
			throw new ResourceNotFoundException("No existen proyectos de esa carrera");
		}

		return proyectos;
	}

	@GetMapping("Listado/Estado/{estado}")
	public List<Proyecto> getProyectosEstado(@PathVariable(name = "estado")String Estado)throws ResourceNotFoundException{
		List<Proyecto> proyectos = RepositorioProyecto.findAllByEstado(Estado);

		if(proyectos.isEmpty()) {
			throw new ResourceNotFoundException("No existen proyectos con ese Estado");
		}

		return proyectos;
	}

	@GetMapping("{id}")
	public Proyecto getProyecto(@PathVariable(value = "id")Long idProyecto)throws ResourceNotFoundException{
		Proyecto proyecto = RepositorioProyecto.findById(idProyecto)
				.orElseThrow(() -> new ResourceNotFoundException("No existe el Proyecto con ese ID"));
		return proyecto;
	}

	@PostMapping("Registrar")
	public Proyecto setProyecto(@Valid @RequestBody Proyecto proyecto)throws ResourceNotFoundException{
		if(RepositorioProyecto.existsByCodigo(proyecto.getCodigo())) {
			throw new ResourceNotFoundException("Ya existe un proyecto con ese Codigo");
		}
		return this.RepositorioProyecto.save(proyecto);
	}

	@PutMapping("Actualizar/{id}")
	public ResponseEntity<Proyecto> putProyecto(@PathVariable(value = "id")Long IDProyecto,@Valid @RequestBody Proyecto proyecto)throws ResourceNotFoundException{
		Proyecto proyectoAct = RepositorioProyecto.findById(IDProyecto)
				.orElseThrow(() -> new ResourceNotFoundException("No existe un proyecto con ese ID"));
		if(RepositorioProyecto.existsByCodigo(proyecto.getCodigo())) {
			Proyecto proyectoComp = RepositorioProyecto.findByCodigo(proyecto.getCodigo());
			if(proyectoComp.getIdProyecto() != IDProyecto) {
				throw new ResourceNotFoundException("Ya existe proyecto con ese codigo");
			}
		}

		proyectoAct.setCodigo(proyecto.getCodigo());
		proyectoAct.setNombreProyecto(proyecto.getNombreProyecto());
		proyectoAct.setResolucion(proyecto.getResolucion());
		proyectoAct.setEstado(proyecto.getEstado());
		proyectoAct.setFacultad(proyecto.getFacultad());
		proyectoAct.setCarreras(proyecto.getCarreras());

		return ResponseEntity.ok(this.RepositorioProyecto.save(proyectoAct));
	}
}
