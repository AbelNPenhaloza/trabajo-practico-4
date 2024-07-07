package ar.edu.unju.fi.service;

import java.util.List;

import ar.edu.unju.fi.dto.AlumnoDTO;

public interface IAlumnoService {

	List<AlumnoDTO> findAllactive();
	List<AlumnoDTO> findAll();

	AlumnoDTO findById(Long idAlumno);
	
	boolean existeAlumnoLu(Integer lu);
	
	void inscribirAlumnoEnMateria(Long idAlumno, Long idMateria);

	AlumnoDTO save(AlumnoDTO alumnoDTO);

	void deleteById(Long idAlumno);

	void editarAlumno(AlumnoDTO alumnoDTO) throws Exception;
}
