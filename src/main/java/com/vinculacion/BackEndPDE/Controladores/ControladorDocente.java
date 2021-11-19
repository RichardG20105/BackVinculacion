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

import com.vinculacion.BackEndPDE.Entidades.Docente;
import com.vinculacion.BackEndPDE.Excepciones.ResourceNotFoundException;
import com.vinculacion.BackEndPDE.Repositorio.RepositorioDocente;
@RestController
@RequestMapping("/Docente/")
public class ControladorDocente {
	@Autowired
	private RepositorioDocente RepositorioDocente;
	
	@GetMapping("ListarDocentes")
	public List<Docente> getDocentes()throws ResourceNotFoundException{
		List<Docente> Docentes = RepositorioDocente.findAll();
		if(Docentes.isEmpty())
			new ResourceNotFoundException("No existen Docentes ingresados");
		return Docentes;
	}
	
	@PostMapping("Registrar")
	public Docente setDocente(@Valid @RequestBody Docente docente)throws ResourceNotFoundException{
		if(RepositorioDocente.existsByCedulaDocente(docente.getCedulaDocente())) {
			throw new ResourceNotFoundException("Ya existe un docente con esa Cedula");
		}
		return this.RepositorioDocente.save(docente);
	}
	
	@PutMapping("Actualizar/{id}")
	public ResponseEntity<Docente> putDocente(@PathVariable(value = "id")Long IDDocente,@Valid @RequestBody Docente docente)throws ResourceNotFoundException{
		Docente docenteAct = RepositorioDocente.findById(IDDocente)
				.orElseThrow(() -> new ResourceNotFoundException("No existe un docente con ese ID"));
		if(RepositorioDocente.existsByCedulaDocente(docente.getCedulaDocente())) {
			Docente docenteComp = RepositorioDocente.findByCedulaDocente(docente.getCedulaDocente());
			if(docenteComp.getIdDocente() != IDDocente) {
				throw new ResourceNotFoundException("Ya existe un docente con esa Cedula");
			}
		}
		
		docenteAct.setNombreDocente(docente.getNombreDocente());
		docenteAct.setCedulaDocente(docente.getCedulaDocente());
		docenteAct.setContacto(docente.getContacto());
		docenteAct.setCorreoElectronico(docente.getCorreoElectronico());
		docenteAct.setGeneroDocente(docente.getGeneroDocente());
		docenteAct.setRelacionLaboral(docente.getRelacionLaboral());
		
		return ResponseEntity.ok(this.RepositorioDocente.save(docenteAct));
	}
	
	@DeleteMapping("Eliminar/{id}")
	public Map<String, Boolean> deleteDocente(@PathVariable(value = "id")Long IDDocente)throws ResourceNotFoundException{
		Docente docente = RepositorioDocente.findById(IDDocente)
				.orElseThrow(() -> new ResourceNotFoundException("No existe el docente con ese ID"));
		
		this.RepositorioDocente.delete(docente);
		Map<String, Boolean> response = new HashMap<>();
		response.put("El docente se elimino correctamente", Boolean.TRUE);
		return response;
	}
}
