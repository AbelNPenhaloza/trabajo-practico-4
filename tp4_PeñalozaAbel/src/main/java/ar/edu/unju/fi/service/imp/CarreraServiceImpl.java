package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unju.fi.dto.CarreraDTO;
import ar.edu.unju.fi.mapper.CarreraMapper;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.repository.CarreraRepository;
import ar.edu.unju.fi.service.ICarreraService;
import jakarta.persistence.EntityNotFoundException;


@Service
public class CarreraServiceImpl implements ICarreraService {
	
	
	@Autowired
	private CarreraMapper carreraMapper;
	
	@Autowired
	private CarreraRepository carreraRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<CarreraDTO> findAllActive() {
		List<Carrera> carreras = carreraRepository.findByEstadoTrue();
		return carreraMapper.toCarreraDTOs(carreras);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CarreraDTO> findAll() {
		List<Carrera> carreras = carreraRepository.findAll();
		return carreraMapper.toCarreraDTOs(carreras);
	}
	
	@Override
	@Transactional(readOnly = true)
	public CarreraDTO findById(Integer idCarrera) {
		return carreraMapper.toCarreraDTO(carreraRepository.findById(idCarrera).orElse(null));
	}
	

	@Override
	@Transactional()
	public CarreraDTO save(CarreraDTO carreraDTO) {
		Carrera carrera = carreraMapper.toCarrera(carreraDTO);
		carrera = carreraRepository.save(carrera);
		return carreraMapper.toCarreraDTO(carrera);
	}

	@Override
	@Transactional
	public void deleteById(Integer idCarrera) {
		carreraRepository.findById(idCarrera).ifPresentOrElse(carrera -> {
			carrera.setEstado(false);
			carreraRepository.save(carrera);
		}, () -> {
			try {
				throw new EntityNotFoundException("Carrera no encontrada con id: " + idCarrera);
			} catch (EntityNotFoundException e) {
				throw new RuntimeException(e);
			}
		});
	}

	@Override
	@Transactional
	public void editarCarrera(CarreraDTO carreraDTO) throws Exception {
		Carrera carrera = carreraMapper.toCarrera(carreraDTO);
		if (!carreraRepository.existsById(carrera.getIdCarrera())) {
			throw new Exception("La carrera con ID " + carrera.getIdCarrera() + " no existe.");
		}
		carreraRepository.save(carrera);
	}

}

