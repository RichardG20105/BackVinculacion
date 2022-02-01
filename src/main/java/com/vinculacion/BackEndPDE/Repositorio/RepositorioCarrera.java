package com.vinculacion.BackEndPDE.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vinculacion.BackEndPDE.Entidades.Carrera;

@Repository
public interface RepositorioCarrera extends JpaRepository<Carrera, Long> {
	Boolean existsByNombreCarrera(String nombreCarrera);
	List<Carrera> findAllByIdFacultad(Long idFacultad);
	Carrera findTopByIdFacultad(Long idFacultad);

	@Query(value = "select * from carrera where idFacultad = ?1 order by idcarrera desc limit 1",nativeQuery = true)
	Carrera findLastByIdFacultad(Long idFacultad);
}
