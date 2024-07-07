package ar.edu.unju.fi.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unju.fi.dto.DocenteDTO;
import ar.edu.unju.fi.mapper.DocenteMapper;
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.repository.DocenteRepository;
import ar.edu.unju.fi.service.IDocenteService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class DocenteServiceImpl implements IDocenteService {
	
	private final DocenteMapper docenteMapper;
	private final DocenteRepository docenteRepository;
	
	public DocenteServiceImpl(DocenteMapper docenteMapper, DocenteRepository docenteRepository) {
		this.docenteMapper = docenteMapper;
		this.docenteRepository = docenteRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<DocenteDTO> findAllActive() {
		log.info("Buscando todas los docentes activos");
		List<Docente> docentesActivos = docenteRepository.findByEstadoTrue();
		log.debug("Docentes encontrados: {}", docentesActivos);
		log.debug("Se encontraron {} docentes activos", docentesActivos.size());
		return docenteMapper.toDocenteDTOs(docentesActivos);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<DocenteDTO> findAll() {
		log.info("Buscando todas los docentes");
		List<Docente> docentes = docenteRepository.findAll();
		log.debug("Se encontraron {} docentes en total", docentes.size());
		return docenteMapper.toDocenteDTOs(docentes);
	}

	@Override
	@Transactional
	public DocenteDTO findById(Long idDocente) {
		log.info("Buscando docente con ID: {}", idDocente);
		Docente docente = docenteRepository.findById(idDocente).orElseThrow(() -> {
			log.error("Docente no encontrado con ID: {}", idDocente);
			return new EntityNotFoundException("Docente no encontrado");
		});
		if (!docente.getEstado()) {
			log.warn("Se ha solicitado un docente inactivo con ID: {}", idDocente);
		}
		log.debug("Docente encontrado: {}", docente);
		return docenteMapper.toDocenteDTO(docente);
	}

	@Override
	@Transactional
	public DocenteDTO save(DocenteDTO docenteDTO) {
		log.info("Guardando nuevo docente: {}", docenteDTO);
		Docente docente = docenteMapper.toDocente(docenteDTO);
		docente = docenteRepository.save(docente);
		log.info("Docente guardado con ID: {}", docente.getIdDocente());
		return docenteMapper.toDocenteDTO(docente);
	}

	@Override
	@Transactional
	public void deleteById(Long idDocente) {
		log.info("Intentando eliminar docente con ID: {}", idDocente);
		try {
			Optional<Docente> docenteOptional = docenteRepository.findById(idDocente);
			if (docenteOptional.isPresent()) {
				Docente docente = docenteOptional.get();
				docente.setEstado(false);
				docenteRepository.save(docente);
				log.info("Docente con ID {} marcada como inactiva", idDocente);
			} else {
				log.error("Docente no encontrado con ID: {}", idDocente);
				throw new EntityNotFoundException("Docente no encontrado con id: " + idDocente);
			}
		} catch (Exception e) {
			log.error("Error al eliminar al docente con ID: {}", idDocente, e);
			throw e;
		}
	}

	@Override
	@Transactional
	public void editarDocente(DocenteDTO docenteDTO) throws Exception {
		log.info("Iniciando edición de docente: {}", docenteDTO);
		Docente docente = docenteMapper.toDocente(docenteDTO);
		if (!docenteRepository.existsById(docente.getIdDocente())) {
			log.error("Intento de editar un docente inexistente con ID: {}", docente.getIdDocente());
			throw new Exception("El docente con ID " + docente.getIdDocente() + " no existe.");
		}
		docenteRepository.save(docente);
		log.info("Docente editado exitosamente: {}", docente);
	}
}
