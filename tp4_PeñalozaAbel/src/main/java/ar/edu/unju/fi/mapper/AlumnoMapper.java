package ar.edu.unju.fi.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import ar.edu.unju.fi.dto.AlumnoDTO;
import ar.edu.unju.fi.model.Alumno;

@Mapper(componentModel = "spring")
public interface AlumnoMapper {

	AlumnoDTO toAlumnoDTO(Alumno alumno);

	@InheritInverseConfiguration
	Alumno toAlumno(AlumnoDTO alumnoDTO);

	List<AlumnoDTO> toAlumnoDTOs(List<Alumno> alumnos);

	List<Alumno> toAlumnos(List<AlumnoDTO> alumnoDTOs);

}
