package com.vinculacion.BackEndPDE.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vinculacion.BackEndPDE.Entidades.Proyecto;

@Repository
public interface RepositorioProyecto extends JpaRepository<Proyecto, Long>{
	Boolean existsByCodigo(String codigo);
	Proyecto findByCodigo(String codigo);
}
