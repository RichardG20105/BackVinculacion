package com.vinculacion.BackEndPDE.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vinculacion.BackEndPDE.Entidades.Proyecto;

@Repository
public interface RepositorioProyecto extends JpaRepository<Proyecto, Long>{
	List<Proyecto> findAllByOrderByIdProyectoDesc();
	Boolean existsByCodigo(String codigo);
	Proyecto findByCodigo(String codigo);
}
