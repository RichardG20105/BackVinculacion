package com.vinculacion.BackEndPDE.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vinculacion.BackEndPDE.Entidades.Integra;

@Repository
public interface RepositorioIntegra extends JpaRepository<Integra, Long>{
	List<Integra> findAllByIdProyecto(Long idProyecto);
}
