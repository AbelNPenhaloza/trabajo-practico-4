package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.dto.DocenteDTO;
import ar.edu.unju.fi.mapper.DocenteMapper;
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.repository.DocenteRepository;
import ar.edu.unju.fi.service.IDocenteService;

@Service
public class DocenteServiceImpl implements IDocenteService {
	
	@Autowired
	private DocenteMapper docenteMapper;
	
	@Autowired
	private DocenteRepository docenteRepository;

	@Override
	public List<DocenteDTO> findAll() {
		
		List<DocenteDTO> docentesDTO = docenteMapper.toDocenteDTOs(docenteRepository.findAll());
		return docentesDTO;
	}

	@Override
	public DocenteDTO findById(Long idDocente) {
		// Docente docente = docenteRepository.findById(idDocente).orElse(null);
		// return docenteMapper.toDocenteDTO(docente);
		return docenteMapper.toDocenteDTO(docenteRepository.findById(idDocente).get());
	}

	@Override
	public Docente save(DocenteDTO docenteDTO) {
		// Docente docente = docenteMapper.toDocente(docenteDTO);
		// docenteRepository.save(docente);
		// return true;
		Docente docente = docenteRepository.save(docenteMapper.toDocente(docenteDTO));
		return docente;
	}

	@Override
	public void deleteById(Long idDocente) {
		//docenteRepository.deleteById(idCodigo);
		Docente docente = docenteRepository.findById(idDocente).get();
		docente.setEstado(false);
		docenteRepository.save(docente);

	}

	@Override
	public void editarDocente(DocenteDTO docenteDTO) throws Exception {
		/*
		 * Docente docente= docenteMapper.toDocente(docenteDTO); if
		 * (docenteRepository.existsById(docente.getIdCodigo())) {
		 * docenteRepository.save(docente); } else { throw new
		 * Exception("El docente con codigo: " + docente.getIdDocente() + " no existe");
		 * }
		 */
		docenteRepository.save(docenteMapper.toDocente(docenteDTO));
	}
}
