package com.vinculacion.BackEndPDE.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vinculacion.BackEndPDE.Entidades.Docente;

@Repository
public interface RepositorioDocente extends JpaRepository<Docente, Long>{
	List<Docente> findAllByOrderByIdDocenteDesc();
	Boolean existsByCedulaDocente(String cedulaDocente);
	Docente findByCedulaDocente(String cedulaDocente);

	List<Docente> findAllBySexoDocenteAndIdCarreraBetween(String sexoDocente,Long idCarrera1, Long idCarrera2);
}
