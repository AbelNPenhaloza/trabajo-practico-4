package ar.edu.unju.fi.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AlumnoDTO {
	private Long idAlumno;

	@NotNull(message="Debe ingresar la libreta universitaria")
	private Integer lu;

	@NotNull(message="Debe ingresar el dni")	
	private Integer dni;

	@NotBlank(message="Debe ingresar el nombre")
	@Size(min=3, max=20, message="El nombre debe contener como mínimo 3 caracteres y como máximo 20 caracteres")
	@Pattern(regexp= "[a-z A-Z]*", message="Debe ingresar únicamente letras")
	private String nombre;

	@NotBlank(message="Debe ingresar el apellido")
	@Size(min=3, max=20, message="El apellido debe contener como mínimo 3 caracteres y como máximo 20 caracteres")
	@Pattern(regexp= "[a-z A-Z]*", message="Debe ingresar únicamente letras")
	private String apellido;

	@NotBlank(message="Debe ingresar el correo electronico")
	@Email
	private String correoElectronico;

	@NotBlank(message="Debe ingresar el telefono")
	private String telefono;

	@NotNull(message = "Debe seleccionar un estado")
	private Boolean estado;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message="Debe ingresar la fecha de nacimiento")
	@Past(message="La fecha de nacimiento debe ser anterior a la fecha actual")
	private LocalDate fechaNacimiento;

	@NotBlank(message="Debe ingresar el domicilio")
	private String domicilio;

	@NotNull(message = "Debe seleccionar una Carrera")
	private CarreraDTO carreraDTO;


	private List<MateriaDTO> materias = new ArrayList<MateriaDTO>();
}
