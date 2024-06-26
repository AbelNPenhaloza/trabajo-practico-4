package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.dto.MateriaDTO;
import ar.edu.unju.fi.mapper.MateriaMapper;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.repository.MateriaRepository;
import ar.edu.unju.fi.service.IMateriaService;

@Service
public class MateriaServiceImpl implements IMateriaService {

	@Autowired
	private MateriaMapper materiaMapper;

	@Autowired
	private MateriaRepository materiaRepository;

	@Override
	public List<MateriaDTO> findAll() {

		List<MateriaDTO> materiasDTO = materiaMapper.toMateriaDTOs(materiaRepository.findAll());
		return materiasDTO;
	}

	@Override
	public MateriaDTO findById(Long idMateria) {
		// Materia materia = materiaRepository.findById(idCodigo).orElse(null);
		// return materiaMapper.toMateriaDTO(materia);
		return materiaMapper.toMateriaDTO(materiaRepository.findById(idMateria).get());
	}

	@Override
	public Materia save(MateriaDTO materiaDTO) {
		// Materia materia = materiaMapper.toMateria(materiaDTO);
		// materiaRepository.save(materia);
		// return true;
		Materia materia = materiaRepository.save(materiaMapper.toMateria(materiaDTO));
		return materia;
	}

	@Override
	public void deleteById(Long idMateria) {
		//materiaRepository.deleteById(idCodigo);
		Materia materia = materiaRepository.findById(idMateria).get();
		materia.setEstado(false);
		materiaRepository.save(materia);
	}

	@Override
	public void editarMateria(MateriaDTO materiaDTO) throws Exception {
		/*
		 * Materia materia = materiaMapper.toMateria(materiaDTO); if
		 * (materiaRepository.existsById(materia.getIdCodigo())) {
		 * materiaRepository.save(materia); } else { throw new
		 * Exception("La materia con codigo: " + materia.getIdCodigo() + " no existe");
		 * }
		 */
		materiaRepository.save(materiaMapper.toMateria(materiaDTO));

	}
}
