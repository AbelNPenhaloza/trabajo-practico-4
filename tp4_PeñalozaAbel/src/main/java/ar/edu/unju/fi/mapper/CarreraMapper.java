package ar.edu.unju.fi.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import ar.edu.unju.fi.dto.CarreraDTO;
import ar.edu.unju.fi.model.Carrera;

@Mapper(componentModel = "spring", uses = { AlumnoMapper.class, MateriaMapper.class})
public interface CarreraMapper {
	
	@Mappings( { @Mapping(source = "alumnos", target = "alumnos"), 
		@Mapping(source = "materias", target = "materias") } )

	CarreraDTO toCarreraDTO(Carrera carrera);

	@InheritInverseConfiguration
	Carrera toCarrera(CarreraDTO carreraDTO);

	List<CarreraDTO> toCarreraDTOs(List<Carrera> carreras);

	List<Carrera> toCarreras(List<CarreraDTO> CarreraDTOs);


}
