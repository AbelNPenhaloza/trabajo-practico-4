package ar.edu.unju.fi.service;

import java.util.List;

import ar.edu.unju.fi.dto.DocenteDTO;
import ar.edu.unju.fi.model.Docente;

public interface IDocenteService {
	
	List<DocenteDTO> findAll();

	DocenteDTO findById(Long idDocente);

	Docente save(DocenteDTO docenteDTO);

	void deleteById(Long idDocente);

	void editarDocente(DocenteDTO docenteDTO) throws Exception;
}
