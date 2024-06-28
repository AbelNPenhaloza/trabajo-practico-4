package ar.edu.unju.fi.service;

import java.util.List;

import ar.edu.unju.fi.dto.MateriaDTO;

public interface IMateriaService {

	List<MateriaDTO> findAllActive();
	List<MateriaDTO> findAll();

	MateriaDTO findById(Long idMateria);

	MateriaDTO save(MateriaDTO materiaDTO);

	void deleteById(Long idMateria);

	void editarMateria(MateriaDTO materiaDTO) throws Exception;
}
