package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.collections.CollectionDocente;
import ar.edu.unju.fi.dto.DocenteDTO;
import ar.edu.unju.fi.mapper.DocenteMapper;
import ar.edu.unju.fi.service.IDocenteService;

@Service
public class DocenteServiceImpl implements IDocenteService {
	
	@Autowired
	private DocenteMapper docenteMapper;

	@Override
	public List<DocenteDTO> findAll() {
		List<DocenteDTO> docentesDTO = docenteMapper.toDocenteDTOs(CollectionDocente.getDocentes());
		return docentesDTO;
	}

	@Override
	public DocenteDTO findById(Integer legajo) {
		DocenteDTO docenteDTO = docenteMapper.toDocenteDTO(CollectionDocente.buscarDocente(legajo));
		return docenteDTO;
	}

	@Override
	public boolean save(DocenteDTO docenteDTO) {
		boolean respuesta = CollectionDocente.agregarDocente(docenteMapper.toDocente(docenteDTO));
		return respuesta;
	}

	@Override
	public void deleteById(Integer legajo) {
		CollectionDocente.eliminarDocente(legajo);

	}

	@Override
	public void edit(DocenteDTO docenteDTO) throws Exception {
		CollectionDocente.modificarDocente(docenteMapper.toDocente(docenteDTO));

	}
}
