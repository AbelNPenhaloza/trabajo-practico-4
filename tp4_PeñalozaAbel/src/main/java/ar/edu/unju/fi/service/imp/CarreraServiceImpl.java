package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import ar.edu.unju.fi.collections.CollectionCarrera;
import ar.edu.unju.fi.dto.CarreraDTO;
import ar.edu.unju.fi.mapper.CarreraMapper;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.repository.CarreraRepository;
import ar.edu.unju.fi.service.ICarreraService;


@Service
public class CarreraServiceImpl implements ICarreraService {
	
	
	@Autowired
	private CarreraMapper carreraMapper;
	
	@Autowired
	private CarreraRepository carreraRepository;
	
	@Override
	public List<CarreraDTO> findAll() {
		
		List<CarreraDTO> carrerasDTO = carreraMapper.toCarreraDTOs(carreraRepository.findAll());
		return carrerasDTO;
	}

	@Override
	public CarreraDTO findById(Integer idCarrera) {
		// Carrera carrera = carreraRepository.findById(idCarrera).orElse(null);
		// return carreraMapper.toCarreraDTO(carrera);
		return carreraMapper.toCarreraDTO(carreraRepository.findById(idCarrera).get());
	}

	@Override
	public Carrera save(CarreraDTO carreraDTO) {
		// Carrera carrera = carreraMapper.toCarrera(carreraDTO);
		// carreraRepository.save(carrera);
		// return true;
		Carrera carrera = carreraRepository.save(carreraMapper.toCarrera(carreraDTO));
		return carrera;
	}

	@Override
	public void deleteById(Integer idCarrera) {
		//carreraRepository.deleteById(idCodigo);
		Carrera carrera = carreraRepository.findById(idCarrera).get();
		carrera.setEstado(false);
		carreraRepository.save(carrera);

	}

	@Override
	public void editarCarrera(CarreraDTO carreraDTO) throws Exception {
		/*
		 * Carrera carrera= carreraMapper.toCarrera(carreraDTO); if
		 * (veRepository.existsById(carrera.getIdCodigo())) {
		 * carreraRepository.save(carrera); } else { throw new
		 * Exception("El carrera con codigo: " + carrera.getIdCarrera() + " no existe");
		 * }
		 */
		carreraRepository.save(carreraMapper.toCarrera(carreraDTO));
	}

}

