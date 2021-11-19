package com.vinculacion.BackEndPDE.Repositorio;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vinculacion.BackEndPDE.Entidades.Participa;

@Repository
public interface RepositorioParticipa extends JpaRepository<Participa, Long>{
	Boolean existsByIdProyectoAndIdDocenteAndAnioParticipaDoc(Long idProyecto, Long idDocente, Date anioParticipaDoc);
	List<Participa> findByIdProyecto(Long idProyecto);
	List<Participa> findAllByIdProyectoAndIdDocenteAndAnioParticipacionDocente(Long idProyecto, Long idDocente, Date anioParticipacionDocente);
}
