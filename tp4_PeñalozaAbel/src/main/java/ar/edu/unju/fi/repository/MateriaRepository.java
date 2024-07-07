package ar.edu.unju.fi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.model.Materia;

@Repository
public interface MateriaRepository extends JpaRepository<Materia, Long> {

	List<Materia> findByEstadoTrue();

	@Query("SELECT m FROM Materia m LEFT JOIN FETCH m.alumnos WHERE m.idMateria = :id")
	Optional<Materia> findByIdWithAlumnos(@Param("id") Long id);
}

