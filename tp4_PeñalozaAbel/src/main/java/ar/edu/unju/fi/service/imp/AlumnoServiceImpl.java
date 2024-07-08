package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unju.fi.dto.AlumnoDTO;
import ar.edu.unju.fi.mapper.AlumnoMapper;
import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.repository.AlumnoRepository;
import ar.edu.unju.fi.repository.MateriaRepository;
import ar.edu.unju.fi.service.IAlumnoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;


@Service
@Transactional
@Slf4j
public class AlumnoServiceImpl implements IAlumnoService {

	
	private final AlumnoMapper alumnoMapper;
	private final AlumnoRepository alumnoRepository;
	
	public AlumnoServiceImpl(AlumnoMapper alumnoMapper, AlumnoRepository alumnoRepository) {
		this.alumnoMapper= alumnoMapper;
		this.alumnoRepository= alumnoRepository;
	}
	
	@Autowired
	private MateriaRepository materiaRepository;
	

	@Override
	@Transactional(readOnly = true)
	public List<AlumnoDTO> findAllactive() {
		log.info("Buscando todos los Alumnos activos");
		List<Alumno> alumnos = alumnoRepository.findByEstadoTrue();	
		log.debug("Alumnos encontrados: {}", alumnos);
		log.debug("Se encontraron {} alumnos en total", alumnos.size());
		return alumnoMapper.toAlumnoDTOs(alumnos);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<AlumnoDTO> findAll() {
		log.info("Buscando todos los Alumnos");
		List<Alumno> alumnos = alumnoRepository.findAll();
		log.debug("Se encontraron {} alumnos en total", alumnos.size());
		return alumnoMapper.toAlumnoDTOs(alumnos);
	}

	@Override
	@Transactional(readOnly = true)
	public AlumnoDTO findById(Long idAlumno) {
		log.info("Buscando Alumno con la ID: {}", idAlumno);
		Alumno alumno = alumnoRepository.findById(idAlumno).orElse(null);
	    if (alumno == null) {
	        log.warn("No se encontró Alumno con la ID: {}", idAlumno);
	    } else {
	        log.debug("Alumno encontrado: {}", alumno);
	    }
	    return alumnoMapper.toAlumnoDTO(alumno);
	}

	@Override
	@Transactional
	public AlumnoDTO save(AlumnoDTO alumnoDTO) {
	   log.info("Guardando alumno: {}", alumnoDTO);
	   Alumno alumno = alumnoMapper.toAlumno(alumnoDTO);
	   alumno = alumnoRepository.save(alumno);
	   log.info("Alumno guardado con la ID: {}", alumno.getIdAlumno());
	   return alumnoMapper.toAlumnoDTO(alumno);
	}

	@Override
	@Transactional
	public void deleteById(Long idAlumno) {
		    log.info("Iniciando el proceso de eliminacion del alumno con la ID: {}", idAlumno);
		    alumnoRepository.findById(idAlumno).ifPresentOrElse(alumno -> {
		    log.info("Alumno encontrado: {}", alumno);
			alumno.setEstado(false);
			log.info("Alumno con la ID: {} se marca como inactivo", idAlumno);
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
		log.info("Iniciando edicion del alumno: {}", alumnoDTO);
		Alumno alumno = alumnoMapper.toAlumno(alumnoDTO);
		if(!alumnoRepository.existsById(alumno.getIdAlumno())) {
			log.warn("Intento de editar un alumno inexistente con ID: {}", alumno.getIdAlumno());
			throw new Exception("El alumno con ID " + alumno.getIdAlumno() + " no existe.");		
		}
        alumnoRepository.save(alumno);
        log.info("Alumno editado con exito: {}", alumno);
	}

	@Override
	public boolean existeAlumnoLu(Integer lu) {
		log.info("Iniciando verificacion de la existencia de la LU: {}", lu);
		boolean exists = alumnoRepository.existsByLuAndEstadoTrue(lu);
		log.debug("La LU: {} existe: {}", lu, exists);
		return exists;
	}
	
	@Override
    public void inscribirAlumnoEnMateria(Long alumnoId, Long materiaId) {
        Alumno alumno = alumnoRepository.findById(alumnoId).orElseThrow(() -> new RuntimeException("Alumno no encontrado"));
        Materia materia = materiaRepository.findById(materiaId).orElseThrow(() -> new RuntimeException("Materia no encontrada"));

        // Verificar si el alumno ya está inscrito en la materia
        if (materia.getAlumnos().contains(alumno)) {
            throw new RuntimeException("El alumno ya está inscrito en esta materia");
        }

        materia.getAlumnos().add(alumno);
        materiaRepository.save(materia);
    }

	
}
