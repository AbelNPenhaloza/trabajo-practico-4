package ar.edu.unju.fi.service;

import java.util.List;

import ar.edu.unju.fi.dto.MateriaDTO;
import ar.edu.unju.fi.model.Materia;

public interface IMateriaService {

	List<MateriaDTO> findAll();

	MateriaDTO findById(Long idMateria);

	Materia save(MateriaDTO materiaDTO);

	void deleteById(Long idMateria);

	void editarMateria(MateriaDTO materiaDTO) throws Exception;
}
