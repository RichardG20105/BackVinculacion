package com.vinculacion.BackEndPDE.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vinculacion.BackEndPDE.Entidades.Certificado;

@Repository
public interface RepositorioCertificado extends JpaRepository<Certificado, Long>{
	Boolean existsByIdProyectoAndCedulaIntegrante(Long idProyecto, String cedulaIntegrante);
}
