package com.vinculacion.BackEndPDE.Repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vinculacion.BackEndPDE.Entidades.Certificado;
import com.vinculacion.BackEndPDE.Entidades.Integra;
import com.vinculacion.BackEndPDE.Entidades.Participa;

@Repository
public interface RepositorioCertificado extends JpaRepository<Certificado, Long>{
	Boolean existsByParticipa(Participa participa);
	Boolean existsByIntegra(Integra integra);
	List<Certificado> findAllByParticipa(Participa participa);
	List<Certificado> findAllByIntegra(Integra integra);
	List<Certificado> findAllByIntegraIsNull();
	List<Certificado> findAllByParticipaIsNull();
	List<Certificado> findAllByIntegraIsNullAndObservacionCertificado(String observacionCertificado);
	List<Certificado> findAllByIntegraIsNullAndFacultadIntegrante(String facultad);

	List<Certificado> findAllByParticipaIsNullAndFacultadIntegrante(String facultad);
	List<Certificado> findAllByParticipaIsNullAndObservacionCertificado(String observacionCertificado);

	Optional<Certificado> findByCodigoCertificado(String codigoCertificado);
}
