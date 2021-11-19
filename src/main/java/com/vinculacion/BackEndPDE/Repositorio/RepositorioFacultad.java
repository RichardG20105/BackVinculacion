package com.vinculacion.BackEndPDE.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vinculacion.BackEndPDE.Entidades.Facultad;

@Repository
public interface RepositorioFacultad extends JpaRepository <Facultad, Long>{
	Boolean existsByNombreFacultad(String nombreFacultad);
	Facultad findByNombreFacultad(String nombreFacultad);
}
