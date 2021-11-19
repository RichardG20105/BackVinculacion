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

import com.vinculacion.BackEndPDE.Entidades.Facultad;
import com.vinculacion.BackEndPDE.Excepciones.ResourceNotFoundException;
import com.vinculacion.BackEndPDE.Repositorio.RepositorioFacultad;
@RestController
@RequestMapping("/Facultad/")
public class ControladorFacultad {
	@Autowired
	private RepositorioFacultad RepositorioFacultad;
	
	@GetMapping("ListarFacultades")
	public List<Facultad> getFacultades(){
		List<Facultad> Facultades= RepositorioFacultad.findAll();
		if(Facultades.isEmpty())
			//Lanza un error
			new ResourceNotFoundException("No existen registros almacenados");
		return Facultades;
	}
	
	@PostMapping("Registrar")
	public Facultad setFacultad(@Valid @RequestBody Facultad facultad)throws ResourceNotFoundException{
		if(RepositorioFacultad.existsByNombreFacultad(facultad.getNombreFacultad())) {
			throw new ResourceNotFoundException("Ya existe una Facultad con ese Nombre");
		}
		return this.RepositorioFacultad.save(facultad);
	}
	
	@PutMapping("Actualizar/{id}")
	public ResponseEntity<Facultad> putFacultad(@PathVariable(value = "id")Long IDFacultad,@Valid @RequestBody Facultad facultad)throws ResourceNotFoundException{
		Facultad facultadAct = RepositorioFacultad.findById(IDFacultad)
				.orElseThrow(() -> new ResourceNotFoundException("No existe una facultad con ese ID"));
		
		if(RepositorioFacultad.existsByNombreFacultad(facultad.getNombreFacultad())){
			Facultad facultadComp = RepositorioFacultad.findByNombreFacultad(facultad.getNombreFacultad());
			if(facultadComp.getIdFacultad() != IDFacultad) {
				throw new ResourceNotFoundException("Ya existe una facultad con ese nombre");
			}
		}
		
		facultadAct.setNombreFacultad(facultad.getNombreFacultad());
		
		return ResponseEntity.ok(this.RepositorioFacultad.save(facultadAct));
	}
}
