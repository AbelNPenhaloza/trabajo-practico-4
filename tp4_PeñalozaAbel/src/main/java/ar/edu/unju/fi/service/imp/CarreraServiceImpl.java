package ar.edu.unju.fi.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unju.fi.dto.AlumnoDTO;
import ar.edu.unju.fi.dto.CarreraDTO;
import ar.edu.unju.fi.mapper.AlumnoMapper;
import ar.edu.unju.fi.mapper.CarreraMapper;
import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.repository.CarreraRepository;
import ar.edu.unju.fi.service.ICarreraService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;


@Service
@Transactional
@Slf4j
public class CarreraServiceImpl implements ICarreraService {
	
	
	@Autowired
	private CarreraMapper carreraMapper;
	
	@Autowired
	private CarreraRepository carreraRepository;
	
	@Autowired
	private AlumnoMapper alumnoMapper;
	
	@Override
	@Transactional(readOnly = true)
	public List<CarreraDTO> findAllActive() {
		log.info("Buscando todas las carreras activas. ");
		List<Carrera> carrerasActivas = carreraRepository.findByEstadoTrue();
		log.debug("Carreras encontradas: {}", carrerasActivas);
		log.debug("Se encontraron {} carreras activas", carrerasActivas.size());
		return carreraMapper.toCarreraDTOs(carrerasActivas);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CarreraDTO> findAll() {
		log.info("Buscando todas las carreras. ");
		List<Carrera> carreras = carreraRepository.findAll();
		log.debug("Se encontraron {} carreras activas", carreras.size());
		return carreraMapper.toCarreraDTOs(carreras);
	}
	
	@Override
	@Transactional(readOnly = true)
	public CarreraDTO findById(Integer idCarrera) {
		log.info("Buscando carrera con ID: {}", idCarrera);
		Carrera carrera = carreraRepository.findById(idCarrera).orElseThrow(() -> {
			log.error("Carrera no encontrada con ID: {}", idCarrera);
			return new EntityNotFoundException("Carrera no encontrada");
		});
		if (!carrera.getEstado()) {
			log.warn("Se ha solicitado una carrera inactiva con ID: {}", idCarrera);
		}
		log.debug("Carrera encontrada: {}", carrera);

		return carreraMapper.toCarreraDTO(carrera);
	}
	

	@Override
	@Transactional()
	public CarreraDTO save(CarreraDTO carreraDTO) {
		log.info("Guardando nueva carrera: {}", carreraDTO);
		Carrera carrera = carreraMapper.toCarrera(carreraDTO);
		carrera = carreraRepository.save(carrera);
		log.info("Carrera guardada con ID: {}", carrera.getIdCarrera());
		return carreraMapper.toCarreraDTO(carrera);
	}

	@Override
	@Transactional
	public void deleteById(Integer idCarrera) {
		log.info("Intentando eliminar carrera con ID: {}", idCarrera);
		try {
			Optional<Carrera> carreraOptional = carreraRepository.findById(idCarrera);
			if (carreraOptional.isPresent()) {
				Carrera carrera = carreraOptional.get();
				carrera.setEstado(false);
				carreraRepository.save(carrera);
				log.info("Carrera con ID {} marcada como inactiva", idCarrera);
			} else {
				log.error("Carrera no encontrada con ID: {}", idCarrera);
				throw new EntityNotFoundException("Carrera no encontrada con id: " + idCarrera);
			}
		} catch (Exception e) {
			log.error("Error al eliminar la carrera con ID: {}", idCarrera, e);
			throw e;
		}
	}

	@Override
	@Transactional
	public void editarCarrera(CarreraDTO carreraDTO) throws Exception {
		log.info("Iniciando edición de carrera: {}", carreraDTO);
		Carrera carrera = carreraMapper.toCarrera(carreraDTO);
		if (!carreraRepository.existsById(carrera.getIdCarrera())) {
			log.error("Intento de editar una carrera inexistente con ID: {}", carrera.getIdCarrera());
			throw new Exception("La carrera con ID " + carrera.getIdCarrera() + " no existe.");
		}
		carreraRepository.save(carrera);
		log.info("Carrera editada exitosamente: {}", carrera);

	}
	
	@Override
	@Transactional(readOnly = true)
	public List<AlumnoDTO> findAlumnosByCarrera(Integer idCarrera){
		log.info("Buscando alumnos para la carrera con ID: {}", idCarrera);
		Optional<Carrera> carrera = carreraRepository.findByIdWithAlumnos(idCarrera);
		if (carrera.isPresent()) {
			List<Alumno> alumnos = carrera.get().getAlumnos();
			log.debug("Se encontraron {} alumnos para la carrera con ID: {}", alumnos.size(), idCarrera);
			return alumnoMapper.toAlumnoDTOs(alumnos);
		}
		log.warn("No se encontró la carrera con ID: {} o no tiene alumnos asociados", idCarrera);
		return List.of(); // Devolver una lista vacía si no se encuentra la carrera

	}
	

}

