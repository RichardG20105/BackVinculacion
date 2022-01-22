package com.vinculacion.BackEndPDE.Repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vinculacion.BackEndPDE.Entidades.Estudiante;

@Repository
public interface RepositorioEstudiante extends JpaRepository<Estudiante, Long>{
	List<Estudiante> findAllByOrderByIdEstudianteDesc();
	Boolean existsByCedulaEstudiante(String cedulaEstudiante);
	Estudiante findByCedulaEstudiante(String cedulaEstudiante);
}
