package ar.edu.unju.fi.service;

import java.util.List;

import ar.edu.unju.fi.dto.AlumnoDTO;
import ar.edu.unju.fi.dto.CarreraDTO;

public interface ICarreraService {
	
	List<CarreraDTO> findAllActive();
	List<CarreraDTO> findAll();

	CarreraDTO findById(Integer idCarrera);

	CarreraDTO save(CarreraDTO carreraDTO);

	void deleteById(Integer idCarrera);

	void editarCarrera(CarreraDTO carreraDTO) throws Exception;
	
	public List<AlumnoDTO> findAlumnosByCarrera(Integer idCarrera);
}
