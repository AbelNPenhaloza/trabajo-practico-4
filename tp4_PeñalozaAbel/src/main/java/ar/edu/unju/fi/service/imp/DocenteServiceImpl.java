package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unju.fi.dto.DocenteDTO;
import ar.edu.unju.fi.mapper.DocenteMapper;
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.repository.DocenteRepository;
import ar.edu.unju.fi.service.IDocenteService;
import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class DocenteServiceImpl implements IDocenteService {
	
	@Autowired
	private DocenteMapper docenteMapper;
	
	@Autowired
	private DocenteRepository docenteRepository;

	@Override
	@Transactional(readOnly = true)
	public List<DocenteDTO> findAllActive() {
		List<Docente> docentes = docenteRepository.findByEstadoTrue();
		return docenteMapper.toDocenteDTOs(docentes);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<DocenteDTO> findAll() {
		List<Docente> docentes = docenteRepository.findAll();
		return docenteMapper.toDocenteDTOs(docentes);
	}

	@Override
	@Transactional(readOnly = true)
	public DocenteDTO findById(Long idDocente) {
		return docenteMapper.toDocenteDTO(docenteRepository.findById(idDocente).orElse(null));
	}

	@Override
	@Transactional
	public DocenteDTO save(DocenteDTO docenteDTO) {
		Docente docente = docenteMapper.toDocente(docenteDTO);
		docente = docenteRepository.save(docente);
		return docenteMapper.toDocenteDTO(docente);
	}

	@Override
	@Transactional
	public void deleteById(Long idDocente) {
		docenteRepository.findById(idDocente).ifPresentOrElse(docente -> {
			docente.setEstado(false);
			docenteRepository.save(docente);
		}, () -> {
			try {
				throw new EntityNotFoundException("Docente no encontrado con id: " + idDocente);
			} catch (EntityNotFoundException e) {
				throw new RuntimeException(e);
			}
		});
	}

	@Override
	@Transactional
	public void editarDocente(DocenteDTO docenteDTO) throws Exception {
		Docente docente = docenteMapper.toDocente(docenteDTO);
		if(!docenteRepository.existsById(docente.getIdDocente())) {
			throw new Exception("La materia con ID " + docente.getIdDocente() + " no existe.");
		}
		docenteRepository.save(docente);
	}
}
