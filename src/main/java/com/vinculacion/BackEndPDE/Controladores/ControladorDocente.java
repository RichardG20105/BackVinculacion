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
import com.vinculacion.BackEndPDE.Entidades.Facultad;
import com.vinculacion.BackEndPDE.Excepciones.ResourceNotFoundException;
import com.vinculacion.BackEndPDE.Repositorio.RepositorioCarrera;
import com.vinculacion.BackEndPDE.Repositorio.RepositorioDocente;
import com.vinculacion.BackEndPDE.Repositorio.RepositorioFacultad;
@RestController
@RequestMapping("/Docente/")
public class ControladorDocente {
	@Autowired
	private RepositorioDocente RepositorioDocente;

	@Autowired
	private RepositorioFacultad RepositorioFacultad;

	@Autowired
	private RepositorioCarrera RepositorioCarrera;

	@GetMapping("ListarDocentes")
	public List<Docente> getDocentes()throws ResourceNotFoundException{
		List<Docente> Docentes = RepositorioDocente.findAllByOrderByIdDocenteDesc();
		if(Docentes.isEmpty())
			new ResourceNotFoundException("No existen Docentes ingresados");
		return Docentes;
	}

	@GetMapping("{id}")
	public Docente getDocente(@PathVariable(value = "id")Long IDDocente)throws ResourceNotFoundException{
		Docente docente = RepositorioDocente.findById(IDDocente)
				.orElseThrow(() -> new ResourceNotFoundException("No se encontro el Docente con ese ID"));
		return docente;
	}

	@GetMapping("Cedula/{cedula}")
	public Docente getDocenteCedula(@PathVariable(value="cedula")String cedula)throws ResourceNotFoundException{
		if(RepositorioDocente.existsByCedulaDocente(cedula)) {
			Docente docente = RepositorioDocente.findByCedulaDocente(cedula);
			return docente;
		}else {
			throw new ResourceNotFoundException("No existen un docente con ese n??mero de cedula");
		}
	}

	@GetMapping("Listado/{facultad}/{sexo}")
	public List<Docente> getDocentesFacultadSexo(@PathVariable(value = "facultad")String Facultad,@PathVariable(value="sexo")String Sexo)throws ResourceNotFoundException{
		Long idCarrera1, idCarrera2;

		Facultad facultad = RepositorioFacultad.findByNombreFacultad(Facultad);
		idCarrera1 = RepositorioCarrera.findTopByIdFacultad(facultad.getIdFacultad()).getIdCarrera();
		idCarrera2 = RepositorioCarrera.findLastByIdFacultad(facultad.getIdFacultad()).getIdCarrera();

		List<Docente> docentes = RepositorioDocente.findAllBySexoDocenteAndIdCarreraBetween(Sexo,idCarrera1, idCarrera2);

		if(docentes.isEmpty())
			throw new ResourceNotFoundException("No existen Docentes");
		return docentes;
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

		docenteAct.setIdCarrera(docente.getIdCarrera());
		docenteAct.setNombreDocente(docente.getNombreDocente());
		docenteAct.setCedulaDocente(docente.getCedulaDocente());
		docenteAct.setContacto(docente.getContacto());
		docenteAct.setCorreoElectronico(docente.getCorreoElectronico());
		docenteAct.setSexoDocente(docente.getSexoDocente());
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
