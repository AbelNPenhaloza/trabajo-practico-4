package ar.edu.unju.fi.service;

import java.util.List;

import ar.edu.unju.fi.dto.DocenteDTO;

public interface IDocenteService {
	
	List<DocenteDTO> findAllActive();
	List<DocenteDTO> findAll();

	DocenteDTO findById(Long idDocente);

	DocenteDTO save(DocenteDTO docenteDTO);

	void deleteById(Long idDocente);

	void editarDocente(DocenteDTO docenteDTO) throws Exception;
}