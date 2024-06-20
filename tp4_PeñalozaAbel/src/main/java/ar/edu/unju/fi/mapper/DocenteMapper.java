package ar.edu.unju.fi.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import ar.edu.unju.fi.dto.DocenteDTO;
import ar.edu.unju.fi.model.Docente;

@Mapper(componentModel = "spring")
public interface DocenteMapper {
	
	DocenteDTO toDocenteDTO(Docente docente);
	
	@InheritInverseConfiguration
	Docente toDocente(DocenteDTO docenteDTO);
	
	List<DocenteDTO> toDocenteDTOs(List<Docente> docentes);
	
	List<Docente> toDocentes(List<DocenteDTO> DocenteDTOs);

}
