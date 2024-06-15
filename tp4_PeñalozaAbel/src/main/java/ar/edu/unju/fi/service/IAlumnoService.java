package ar.edu.unju.fi.service;

import java.util.List;

import ar.edu.unju.fi.dto.AlumnoDTO;

public interface IAlumnoService {

	List<AlumnoDTO> findAll();

	AlumnoDTO findById(Integer lu);

	boolean save(AlumnoDTO alumnoDTO);

	void deleteById(Integer lu);

	void edit(AlumnoDTO alumnoDTO) throws Exception;
}
