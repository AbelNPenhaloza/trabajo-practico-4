package ar.edu.unju.fi.dto;

import java.util.List;

import ar.edu.unju.fi.model.Curso;
import ar.edu.unju.fi.model.Modalidad;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class MateriaDTO {
	private Long idMateria;

	@NotBlank(message = "Debe ingresar el nombre de la Materia")
	@Pattern(regexp = "^[\\p{L}A-Z ]{3,100}$", message = "El nombre debe contener solo letras y espacios, y tener entre 3 y 100 caracteres")
	private String nombre;


	@NotNull(message = "Debe seleccionar un Curso")
	@Enumerated(EnumType.STRING)
	private Curso curso;

	@NotNull(message = "Debe ingresar la cantidad de horas de la Materia")
	@Min(value = 1, message = "La cantidad de horas debe ser al menos 1")
	@Max(value = 200, message = "La cantidad de horas no puede exceder 200")
	private Integer cantidadDeHoras;

	@NotNull(message = "Debe seleccionar una Modalidad")
	@Enumerated(EnumType.STRING)
	private Modalidad modalidad;

	@NotNull(message = "Debe seleccionar un estado")
	@Column(nullable = false, columnDefinition = "boolean default true")
	private Boolean estado;

	@NotNull(message = "Debe seleccionar un Docente")
	private DocenteDTO docenteDTO;

	@NotNull(message = "Debe seleccionar una Carrera")
	private CarreraDTO carreraDTO;

	@NotNull(message = "Debe seleccionar uno o más Alumnos")
	private List<AlumnoDTO> alumnos;

}
