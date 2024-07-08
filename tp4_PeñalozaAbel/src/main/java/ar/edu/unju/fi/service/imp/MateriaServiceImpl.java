package ar.edu.unju.fi.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unju.fi.dto.AlumnoDTO;
import ar.edu.unju.fi.dto.MateriaDTO;
import ar.edu.unju.fi.mapper.AlumnoMapper;
import ar.edu.unju.fi.mapper.MateriaMapper;
import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.repository.MateriaRepository;
import ar.edu.unju.fi.service.IMateriaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class MateriaServiceImpl implements IMateriaService {

	private final AlumnoMapper alumnoMapper;
	private final MateriaMapper materiaMapper;
	private final MateriaRepository materiaRepository;

	/**
	 * @param alumnoMapper
	 * @param materiaMapper
	 * @param materiaRepository
	 */
	public MateriaServiceImpl(AlumnoMapper alumnoMapper, MateriaMapper materiaMapper,
			MateriaRepository materiaRepository) {
		this.alumnoMapper = alumnoMapper;
		this.materiaMapper = materiaMapper;
		this.materiaRepository = materiaRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<MateriaDTO> findAllActive() {
		log.info("Buscando todas las materias activas");
		List<Materia> materiasActivas = materiaRepository.findByEstadoTrue();
		log.debug("Materias encontradas: {}", materiasActivas);
		log.debug("Se encontraron {} materias activas", materiasActivas.size());
		return materiaMapper.toMateriaDTOs(materiasActivas);
	}

	@Override
	@Transactional(readOnly = true)
	public List<MateriaDTO> findAll() {
		log.info("Buscando todas las materias");
		List<Materia> materias = materiaRepository.findAll();
		log.debug("Se encontraron {} materias en total", materias.size());
		return materiaMapper.toMateriaDTOs(materias);
	}

	@Override
	@Transactional
	public MateriaDTO findById(Long idMateria) {
		log.info("Buscando materia con ID: {}", idMateria);
		Materia materia = materiaRepository.findById(idMateria).orElseThrow(() -> {
			log.error("Materia no encontrada con ID: {}", idMateria);
			return new EntityNotFoundException("Materia no encontrada");
		});
		if (!materia.getEstado()) {
			log.warn("Se ha solicitado una materia inactiva con ID: {}", idMateria);
		}
		log.debug("Materia encontrada: {}", materia);
		return materiaMapper.toMateriaDTO(materia);
	}

	@Override
	@Transactional
	public MateriaDTO save(MateriaDTO materiaDTO) {
		log.info("Guardando nueva materia: {}", materiaDTO);
		Materia materia = materiaMapper.toMateria(materiaDTO);
		materia = materiaRepository.save(materia);
		log.info("Materia guardada con ID: {}", materia.getIdMateria());
		return materiaMapper.toMateriaDTO(materia);
	}

	@Override
	@Transactional
	public void deleteById(Long idMateria) {
		log.info("Intentando eliminar materia con ID: {}", idMateria);
		try {
			Optional<Materia> materiaOptional = materiaRepository.findById(idMateria);
			if (materiaOptional.isPresent()) {
				Materia materia = materiaOptional.get();
				materia.setEstado(false);
				materiaRepository.save(materia);
				log.info("Materia con ID {} marcada como inactiva", idMateria);
			} else {
				log.error("Materia no encontrada con ID: {}", idMateria);
				throw new EntityNotFoundException("Materia no encontrada con id: " + idMateria);
			}
		} catch (Exception e) {
			log.error("Error al eliminar la materia con ID: {}", idMateria, e);
			throw e;
		}
	}

	@Override
	@Transactional
	public void editarMateria(MateriaDTO materiaDTO) throws Exception {
		log.info("Iniciando edición de materia: {}", materiaDTO);
		Materia materia = materiaMapper.toMateria(materiaDTO);
		if (!materiaRepository.existsById(materia.getIdMateria())) {
			log.error("Intento de editar una materia inexistente con ID: {}", materia.getIdMateria());
			throw new Exception("La materia con ID " + materia.getIdMateria() + " no existe.");
		}
		materiaRepository.save(materia);
		log.info("Materia editada exitosamente: {}", materia);
	}

	@Override
	@Transactional(readOnly = true)
	public List<AlumnoDTO> findAlumnosByMateria(Long idMateria) {
		log.info("Buscando alumnos para la materia con ID: {}", idMateria);
		Optional<Materia> materia = materiaRepository.findByIdWithAlumnos(idMateria);
		if (materia.isPresent()) {
			List<Alumno> alumnos = materia.get().getAlumnos();
			log.debug("Se encontraron {} alumnos para la materia con ID: {}", alumnos.size(), idMateria);
			return alumnoMapper.toAlumnoDTOs(alumnos);
		}
		log.warn("No se encontró la materia con ID: {} o no tiene alumnos asociados", idMateria);
		return List.of(); // Devolver una lista vacía si no se encuentra la materia

	}
}
