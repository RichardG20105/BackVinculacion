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

import com.vinculacion.BackEndPDE.Entidades.Carrera;
import com.vinculacion.BackEndPDE.Excepciones.ResourceNotFoundException;
import com.vinculacion.BackEndPDE.Repositorio.RepositorioCarrera;
@RestController
@RequestMapping("/Carrera/")
public class ControladorCarrera {
	@Autowired
	private RepositorioCarrera RepositorioCarrera;
	
	@GetMapping("ListarCarreras")
	public List<Carrera> getCarreras()throws ResourceNotFoundException{
		List<Carrera> Carreras = RepositorioCarrera.findAll();
		
		if(Carreras.isEmpty()) {
			throw new ResourceNotFoundException("No existen carreras almacenadas");
		}
		return Carreras;
	}
	
	@GetMapping("ListarCarrerasFacultad/{id}")
	public List<Carrera> getCarrerasFacultad(@PathVariable(value = "id")Long IDFacultad)throws ResourceNotFoundException{
		List<Carrera> Carreras = RepositorioCarrera.findAllByIdFacultad(IDFacultad);
		if(Carreras.isEmpty()) {
			throw new ResourceNotFoundException("No existen Carreras registradas de esa Facultad");
		}
		return Carreras;
	}
	
	@PostMapping("Registrar")
	public Carrera registrarCarrera(@Valid @RequestBody Carrera carrera)throws ResourceNotFoundException {
		if(RepositorioCarrera.existsByNombreCarrera(carrera.getNombreCarrera())) {
			throw new ResourceNotFoundException("Ya existe una carrera con ese Nombre");
		}
		return this.RepositorioCarrera.save(carrera);
	}
	
	@PutMapping("Actualizar/{id}")
	public ResponseEntity<Carrera> putCarrera(@PathVariable(value = "id")Long IDCarrera, @Valid @RequestBody Carrera carrera)throws ResourceNotFoundException{
		Carrera carreraAct = RepositorioCarrera.findById(IDCarrera)
				.orElseThrow(() -> new ResourceNotFoundException("No existe la carrera con ese ID"));
		if(RepositorioCarrera.existsByNombreCarrera(carrera.getNombreCarrera())) {
			throw new ResourceNotFoundException("Ya existe una Carrera con ese Nombre");
		}
		
		carreraAct.setNombreCarrera(carrera.getNombreCarrera());
		
		return ResponseEntity.ok(this.RepositorioCarrera.save(carreraAct));
	}
}
