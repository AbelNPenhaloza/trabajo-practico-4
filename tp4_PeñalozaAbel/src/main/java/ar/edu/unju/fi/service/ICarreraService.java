package ar.edu.unju.fi.service;

import java.util.List;

import ar.edu.unju.fi.dto.CarreraDTO;
import ar.edu.unju.fi.model.Carrera;

public interface ICarreraService {
	
	List<CarreraDTO> findAll();

	CarreraDTO findById(Integer idCarrera);

	Carrera save(CarreraDTO carreraDTO);

	void deleteById(Integer idCarrera);

	void editarCarrera(CarreraDTO carreraDTO) throws Exception;
}
