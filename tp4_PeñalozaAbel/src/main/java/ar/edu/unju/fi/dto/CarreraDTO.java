package ar.edu.unju.fi.dto;

import java.util.ArrayList;
import java.util.List;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor


public class CarreraDTO {
	
	private Integer idCarrera;

	@NotNull(message = "Debe ingresar el codigo de la carrera. ")
	private Integer codigo;
	
	@NotBlank(message = "Debe ingresar el nombre de la Carrera. ")
	@Pattern(regexp = "^[a-zA-Z\\s]{3,40}$", message = "Debe ingresar unicamente letras y numeros")
	private String nombre;
	
	@NotNull(message = "Debe ingresar la cantidad de anios de la carrera. ")
	private Byte cantidadAnios;
	
	@NotNull(message = "Debe seleccionar un estado")
	private boolean estado;
	
	@NotNull(message = "Debe seleccionar uno o mas alumnos.")
	private List<AlumnoDTO> alumnos = new ArrayList<AlumnoDTO>();
	
	@NotNull(message = "Debe seleccionar una o mas materias.")
	private List<MateriaDTO> materias = new ArrayList<MateriaDTO>();

}
