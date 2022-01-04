package com.vinculacion.BackEndPDE.Repositorio;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vinculacion.BackEndPDE.Entidades.Participa;
import com.vinculacion.BackEndPDE.Entidades.Proyecto;

@Repository
public interface RepositorioParticipa extends JpaRepository<Participa, Long>{
	/*Boolean existsByIdProyectoAndIdDocenteAndAnioParticipaDoc(Long idProyecto, Long idDocente, Date anioParticipaDoc);
	List<Participa> findByIdProyecto(Long idProyecto);
	List<Participa> findAllByIdProyectoAndIdDocenteAndAnioParticipaDoc(Long idProyecto, Long idDocente, Date anioParticipaDoc);
	@Query(value = "Select docente.* , participa.idparticipa, participa.cargo, participa.anioparticipadoc, participa.horasparticipacion\r\n"
			+ "from docente INNER JOIN participa \r\n"
			+ "ON docente.iddocente = participa.iddocente\r\n"
			+ "where idproyecto = ?1",nativeQuery = true)
	List<Optional<Object>> findAllByPartipacion(Long idProyecto);*/
	List<Participa> findAllByProyecto(Proyecto proyecto);
}
