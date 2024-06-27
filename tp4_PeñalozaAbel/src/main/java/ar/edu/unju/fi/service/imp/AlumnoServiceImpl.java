package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.dto.AlumnoDTO;
import ar.edu.unju.fi.mapper.AlumnoMapper;
import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.repository.AlumnoRepository;
import ar.edu.unju.fi.service.IAlumnoService;

@Service
public class AlumnoServiceImpl implements IAlumnoService {

	@Autowired
	private AlumnoMapper alumnoMapper;

	@Autowired
	private AlumnoRepository alumnoRepository;
	
	@Override
	public List<AlumnoDTO> findAll() {
		List<AlumnoDTO> alumnosDTO = alumnoMapper.toAlumnoDTOs(alumnoRepository.findAll());
		return alumnosDTO;
	}

	@Override
	public AlumnoDTO findById(Long idAlumno) {
		return alumnoMapper.toAlumnoDTO(alumnoRepository.findById(idAlumno).get());
	}

	@Override
	public Alumno save(AlumnoDTO alumnoDTO) {
	   Alumno alumno = alumnoRepository.save(alumnoMapper.toAlumno(alumnoDTO));
	   return alumno;
	}

	@Override
	public void deleteById(Long idAlumno) {
        Alumno alumno = alumnoRepository.findById(idAlumno).get();
        alumnoRepository.save(alumno);
        alumno.setEstado(false);
	}

	@Override
	public void editarAlumno(AlumnoDTO alumnoDTO) throws Exception {
        alumnoRepository.save(alumnoMapper.toAlumno(alumnoDTO));
	}

}
