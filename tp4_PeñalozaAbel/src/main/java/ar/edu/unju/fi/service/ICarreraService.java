package ar.edu.unju.fi.service;

import java.util.List;

import ar.edu.unju.fi.dto.CarreraDTO;

public interface ICarreraService {

	List<CarreraDTO> findAll();

	CarreraDTO findById(Integer codigo);

	boolean save(CarreraDTO carreraDTO);

	void deleteById(Integer codigo);

	void edit(CarreraDTO carreraDTO) throws Exception;


}
