package com.vinculacion.BackEndPDE.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vinculacion.BackEndPDE.Entidades.Carrera;

@Repository
public interface RepositorioCarrera extends JpaRepository<Carrera, Long> {
	Boolean existsByNombreCarrera(String nombreCarrera);
	List<Carrera> findAllByIdFacultad(Long idFacultad);
}
