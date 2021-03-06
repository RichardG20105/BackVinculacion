package com.vinculacion.BackEndPDE.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vinculacion.BackEndPDE.Entidades.Integra;
import com.vinculacion.BackEndPDE.Entidades.Proyecto;

@Repository
public interface RepositorioIntegra extends JpaRepository<Integra, Long>{
	List<Integra> findAllByProyecto(Proyecto proyecto);
	List<Integra> findEstudianteByEstudiante_idCarreraBetween(Long id1, Long id2);
}
