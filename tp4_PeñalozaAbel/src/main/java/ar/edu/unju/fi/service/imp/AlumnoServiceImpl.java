package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unju.fi.dto.AlumnoDTO;
import ar.edu.unju.fi.mapper.AlumnoMapper;
import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.repository.AlumnoRepository;
import ar.edu.unju.fi.service.IAlumnoService;
import jakarta.persistence.EntityNotFoundException;


@Service
@Transactional
public class AlumnoServiceImpl implements IAlumnoService {

	@Autowired
	private AlumnoMapper alumnoMapper;

	@Autowired
	private AlumnoRepository alumnoRepository;
	

	@Override
	@Transactional(readOnly = true)
	public List<AlumnoDTO> findAllactive() {
		List<Alumno> alumnos = alumnoRepository.findByEstadoTrue();	
		return alumnoMapper.toAlumnoDTOs(alumnos);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<AlumnoDTO> findAll() {
		List<Alumno> alumnos = alumnoRepository.findAll();
		return alumnoMapper.toAlumnoDTOs(alumnos);
	}

	@Override
	@Transactional(readOnly = true)
	public AlumnoDTO findById(Long idAlumno) {
		return alumnoMapper.toAlumnoDTO(alumnoRepository.findById(idAlumno).orElse(null));
	}

	@Override
	@Transactional
	public AlumnoDTO save(AlumnoDTO alumnoDTO) {
	   Alumno alumno = alumnoMapper.toAlumno(alumnoDTO);
	   alumno = alumnoRepository.save(alumno);
	   return alumnoMapper.toAlumnoDTO(alumno);
	}

	@Override
	@Transactional
	public void deleteById(Long idAlumno) {
		    alumnoRepository.findById(idAlumno).ifPresentOrElse(alumno -> {
			alumno.setEstado(false);
			alumnoRepository.save(alumno);
		}, () -> {
			try {
				throw new EntityNotFoundException("Alumno no se encontro con id: " + idAlumno);
			} catch (EntityNotFoundException e) {
				throw new RuntimeException(e);
			}
		});
	}

	@Override
	@Transactional
	public void editarAlumno(AlumnoDTO alumnoDTO) throws Exception {
		Alumno alumno = alumnoMapper.toAlumno(alumnoDTO);
		if(!alumnoRepository.existsById(alumno.getIdAlumno())) {
			throw new Exception("El alumno con ID " + alumno.getIdAlumno() + " no existe.");		
		}
        alumnoRepository.save(alumno);
	}

	@Override
	public boolean existeAlumnoLu(Integer lu) {
		return alumnoRepository.existsByLuAndEstadoTrue(lu);
	}

	
}
