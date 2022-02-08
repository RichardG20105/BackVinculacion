package com.vinculacion.BackEndPDE.Repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vinculacion.BackEndPDE.Entidades.Participa;
import com.vinculacion.BackEndPDE.Entidades.Proyecto;

@Repository
public interface RepositorioParticipa extends JpaRepository<Participa, Long>{
	List<Participa> findAllByProyectoAndCargo(Proyecto proyecto,String cargo);
	List<Participa> findAllByFacultad(String facultad);
	Optional<Participa> findByProyectoAndCargo(Proyecto proyecto, String cargo);
	@Query(value = "SELECT * FROM participa WHERE iddocente = ?1 and extract(year from anioparticipadoc) = ?2", nativeQuery = true)
	List<Participa> findAllByDocenteAnioParticipa(Long idDocente, int anioParticipa);
	
	List<Participa> findDocenteDistinctByDocente_relacionLaboral(String relacionLaboral);
}
