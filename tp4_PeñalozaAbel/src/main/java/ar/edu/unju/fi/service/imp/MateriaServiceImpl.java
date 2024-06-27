package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unju.fi.dto.MateriaDTO;
import ar.edu.unju.fi.mapper.MateriaMapper;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.repository.MateriaRepository;
import ar.edu.unju.fi.service.IMateriaService;

@Service
@Transactional
public class MateriaServiceImpl implements IMateriaService {

	@Autowired
	private MateriaMapper materiaMapper;

	@Autowired
	private MateriaRepository materiaRepository;

	@Override
	@Transactional(readOnly = true)
	public List<MateriaDTO> findAll() {

		List<MateriaDTO> materiasDTO = materiaMapper.toMateriaDTOs(materiaRepository.findAll());
		return materiasDTO;
	}

	@Override
	@Transactional(readOnly = true)
	public MateriaDTO findById(Long idMateria) {
		return materiaMapper.toMateriaDTO(materiaRepository.findById(idMateria).orElse(null));
	}

	@Override
	public Materia save(MateriaDTO materiaDTO) {
		Materia materia = materiaMapper.toMateria(materiaDTO);
		return materiaRepository.save(materia);
	}

	@Override
	public void deleteById(Long idMateria) {
		Materia materia = materiaRepository.findById(idMateria).orElse(null);
		if (materia != null) {
			materia.setEstado(false);
			materiaRepository.save(materia);
		}
	}

	@Override
	public void editarMateria(MateriaDTO materiaDTO) throws Exception {
		Materia materia = materiaMapper.toMateria(materiaDTO);
		if (!materiaRepository.existsById(materia.getIdMateria())) {
			throw new Exception("La materia con ID " + materia.getIdMateria() + " no existe.");
		}
		materiaRepository.save(materia);
	}
}
