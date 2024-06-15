package ar.edu.unju.fi.service;

import java.util.List;

import ar.edu.unju.fi.dto.DocenteDTO;

public interface IDocenteService {
	
	List<DocenteDTO> findAll();

	DocenteDTO findById(Integer legajo);

	boolean save(DocenteDTO docenteDTO);

	void deleteById(Integer legajo);

	void edit(DocenteDTO docenteDTO) throws Exception;
}
