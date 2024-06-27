package ar.edu.unju.fi.service;

import java.util.List;

import ar.edu.unju.fi.dto.AlumnoDTO;
import ar.edu.unju.fi.model.Alumno;

public interface IAlumnoService {

	List<AlumnoDTO> findAll();

	AlumnoDTO findById(Long idAlumno);

	Alumno save(AlumnoDTO alumnoDTO);

	void deleteById(Long idAlumno);

	void editarAlumno(AlumnoDTO alumnoDTO) throws Exception;
}
