package ar.edu.unju.fi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.model.Alumno;
import java.util.List;


@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
     List<Alumno>findByEstadoTrue();
    // boolean existsByLu(Integer lu);
     boolean existsByLuAndEstadoTrue(Integer lu);
}

