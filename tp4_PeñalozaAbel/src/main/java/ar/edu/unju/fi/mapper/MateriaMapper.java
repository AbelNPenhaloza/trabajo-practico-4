package ar.edu.unju.fi.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import ar.edu.unju.fi.dto.MateriaDTO;
import ar.edu.unju.fi.model.Materia;

@Mapper(componentModel = "spring", uses = { DocenteMapper.class, CarreraMapper.class, AlumnoMapper.class })
public interface MateriaMapper {
	@Mappings({ @Mapping(source = "docente", target = "docenteDTO"),
		@Mapping(source = "carrera", target = "carreraDTO"),
		@Mapping(source = "alumnos", target = "alumnos", ignore = true) })
	MateriaDTO toMateriaDTO(Materia materia);

	@InheritInverseConfiguration
	Materia toMateria(MateriaDTO materiaDTO);

	List<MateriaDTO> toMateriaDTOs(List<Materia> materias);

	List<Materia> toMaterias(List<MateriaDTO> materiaDTOs);

}
