package ar.edu.unju.fi.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
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

	@Column(name="alumno_lu", nullable = false)
	@NotBlank(message="Debe ingresar la libreta universitaria")
	private Integer lu;
		
	@Column(name="alumno_dni", nullable = false)
	@NotBlank(message="Debe ingresar el dni")	
	private Integer dni;
	
	@Column(name="alumno_nombre", nullable = false)
	@NotBlank(message="Debe ingresar el nombre")
	@Size(min=3, max=20, message="El nombre debe contener como mínimo 3 caracteres y como máximo 20 caracteres")
	@Pattern(regexp= "[a-z A-Z]*", message="Debe ingresar únicamente letras")
	private String nombre;
	
	@Column(name="alumno_apellido", nullable = false)
	@NotBlank(message="Debe ingresar el apellido")
	@Size(min=3, max=20, message="El apellido debe contener como mínimo 3 caracteres y como máximo 20 caracteres")
	@Pattern(regexp= "[a-z A-Z]*", message="Debe ingresar únicamente letras")
	private String apellido;
	
	@Column(name="alumno_email", nullable = false)
	@NotBlank(message="Debe ingresar el correo electronico")
	@Email
	private String correoElectronico;
	
	@Column(name="alumno_telefono", nullable = false)
	@NotBlank(message="Debe ingresar el telefono")
	private String telefono;
	
	@Column(name="alumno_estado", nullable = false, columnDefinition = "boolean default true")
	@NotNull(message = "Debe seleccionar un estado")
	private Boolean estado;
	
	@Column(name="alumno_fecha_de_nacimiento", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotBlank(message="Debe ingresar la fecha de nacimiento")
	@Past(message="La fecha de nacimiento debe ser anterior a la fecha actual")
	private LocalDate fechaNacimiento;
	
	@Column(name="alumno_domicilio", nullable = false)
	@NotBlank(message="Debe ingresar el domicilio")
	private String domicilio;
}
