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
	public List<AlumnoDTO> findAllactive() {
		List<Alumno> alumnos = alumnoRepository.findByEstadoTrue();	
		return alumnoMapper.toAlumnoDTOs(alumnos);
	}
	
	@Override
	public List<AlumnoDTO> findAll() {
		List<Alumno> alumnos = alumnoRepository.findAll();
		return alumnoMapper.toAlumnoDTOs(alumnos);
	}

	@Override
	public AlumnoDTO findById(Long idAlumno) {
		return alumnoMapper.toAlumnoDTO(alumnoRepository.findById(idAlumno).orElse(null));
	}

	@Override
	public AlumnoDTO save(AlumnoDTO alumnoDTO) {
	   Alumno alumno = alumnoMapper.toAlumno(alumnoDTO);
	   alumno = alumnoRepository.save(alumno);
	   return alumnoMapper.toAlumnoDTO(alumno);
	}

	@Override
	public void deleteById(Long idAlumno) {
        Alumno alumno = alumnoRepository.findById(idAlumno).get();
        alumnoRepository.save(alumno);
        alumno.setEstado(false);
	}

	@Override
	public void editarAlumno(AlumnoDTO alumnoDTO) throws Exception {
		Alumno alumno = alumnoMapper.toAlumno(alumnoDTO);
		if(!alumnoRepository.existsById(alumno.getIdAlumno())) {
			throw new Exception("El alumno con ID " + alumno.getIdAlumno() + " no existe.");		
		}
        alumnoRepository.save(alumno);
	}
}
