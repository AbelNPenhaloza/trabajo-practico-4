package ar.edu.unju.fi.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import ar.edu.unju.fi.dto.AlumnoDTO;
import ar.edu.unju.fi.model.Alumno;

@Mapper(componentModel = "spring", uses = { MateriaMapper.class, CarreraMapper.class})
public interface AlumnoMapper{

	@Mappings({ @Mapping(source = "carrera", target = "carreraDTO"),
		        @Mapping(source = "materias", target = "materias") })
	AlumnoDTO toAlumnoDTO(Alumno alumno);

	@InheritInverseConfiguration
	Alumno toAlumno(AlumnoDTO alumnoDTO);

	List<AlumnoDTO> toAlumnoDTOs(List<Alumno> alumnos);

	List<Alumno> toAlumnos(List<AlumnoDTO> alumnoDTOs);

}
