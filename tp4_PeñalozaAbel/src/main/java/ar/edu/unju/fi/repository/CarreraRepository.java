package ar.edu.unju.fi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.model.Carrera;

@Repository
public interface CarreraRepository extends JpaRepository<Carrera, Integer> {
	List<Carrera> findByEstadoTrue();
	
	@Query("SELECT DISTINCT a FROM Carrera c JOIN c.alumnos a WHERE c.idCarrera = :idCarrera")
	List<Alumno> findAlumnosByCarrera(@Param("idCarrera") Integer idCarrera);
}

