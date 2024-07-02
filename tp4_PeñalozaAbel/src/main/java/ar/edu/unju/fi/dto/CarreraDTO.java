package ar.edu.unju.fi.dto;

import java.util.ArrayList;
import java.util.List;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor


public class CarreraDTO {
	
	private Integer idCarrera;

	@NotBlank(message = "Debe ingresar el codigo de la carrera. ")
	@Size(min= 1, max=5, message = "El codigo debe tener como minimo un digito. ")
	@Pattern(regexp= "[0-9]")
	private Integer codigo;
	
	@NotBlank(message = "Debe ingresar el nombre de la Carrera. ")
	@Size(min = 10, max = 100, message = "El nombre de la carrera debe tener entre 10 y 100 caracteres. ")
	@Pattern(regexp = "[a-z A-Z]*", message = "Debe ingresar unicamente letras")
	private String nombre;
	
	@NotNull(message = "Debe ingresar la cantidad de anios de la carrera. ")
	@Pattern(regexp= "[1-7]")
	private Byte cantidadAnios;
	
	@NotNull(message = "Debe seleccionar un estado")
	private boolean estado;
	
	@NotNull(message = "Debe seleccionar uno o mas alumnos.")
	private List<AlumnoDTO> alumnos = new ArrayList<AlumnoDTO>();
	
	@NotNull(message = "Debe seleccionar una o mas materias.")
	private List<MateriaDTO> materias = new ArrayList<MateriaDTO>();

}
