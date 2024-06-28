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
import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class MateriaServiceImpl implements IMateriaService {

	@Autowired
	private MateriaMapper materiaMapper;

	@Autowired
	private MateriaRepository materiaRepository;

	@Override
	@Transactional(readOnly = true)
	public List<MateriaDTO> findAllActive() {
		List<Materia> materias = materiaRepository.findByEstadoTrue();
		return materiaMapper.toMateriaDTOs(materias);
	}

	@Override
	@Transactional(readOnly = true)
	public List<MateriaDTO> findAll() {
		List<Materia> materias = materiaRepository.findAll();
		return materiaMapper.toMateriaDTOs(materias);
	}

	@Override
	@Transactional(readOnly = true)
	public MateriaDTO findById(Long idMateria) {
		return materiaMapper.toMateriaDTO(materiaRepository.findById(idMateria).orElse(null));
	}

	@Override
	@Transactional
	public MateriaDTO save(MateriaDTO materiaDTO) {
		Materia materia = materiaMapper.toMateria(materiaDTO);
		materia = materiaRepository.save(materia);
		return materiaMapper.toMateriaDTO(materia);
	}

	@Override
	@Transactional
	public void deleteById(Long idMateria) {
		materiaRepository.findById(idMateria).ifPresentOrElse(materia -> {
			materia.setEstado(false);
			materiaRepository.save(materia);
		}, () -> {
			try {
				throw new EntityNotFoundException("Materia no encontrada con id: " + idMateria);
			} catch (EntityNotFoundException e) {
				throw new RuntimeException(e);
			}
		});
	}

	@Override
	@Transactional
	public void editarMateria(MateriaDTO materiaDTO) throws Exception {
		Materia materia = materiaMapper.toMateria(materiaDTO);
		if (!materiaRepository.existsById(materia.getIdMateria())) {
			throw new Exception("La materia con ID " + materia.getIdMateria() + " no existe.");
		}
		materiaRepository.save(materia);
	}
}
