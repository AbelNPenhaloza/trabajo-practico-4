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
	
	@Column(name="alumno_id", nullable = false)
	private Integer lu;
	
	@Column(name="dni_alumno", nullable = false)
	@NotBlank(message="Debe ingresar el dni")	
	private Integer dni;
	
	@Column(name="nombre_alumno", nullable = false)
	@NotBlank(message="Debe ingresar el nombre")
	@Size(min=3, max=20, message="El nombre debe contener como mínimo 3 caracteres y como máximo 20 caracteres")
	@Pattern(regexp= "[a-z A-Z]*", message="Debe ingresar únicamente letras")
	private String nombre;
	
	@Column(name="apellido_alumno", nullable = false)
	@NotBlank(message="Debe ingresar el apellido")
	@Size(min=3, max=20, message="El apellido debe contener como mínimo 3 caracteres y como máximo 20 caracteres")
	@Pattern(regexp= "[a-z A-Z]*", message="Debe ingresar únicamente letras")
	private String apellido;
	
	@Column(name="correoElectronico_alumno", nullable = false)
	@NotBlank(message="Debe ingresar el correo electronico")
	@Email
	private String correoElectronico;
	
	@Column(name="telefono_alumno", nullable = false)
	@NotBlank(message="Debe ingresar el telefono")
	private String telefono;
	
	@NotNull(message = "Debe seleccionar un estado")
	private Boolean estado;
	
	@Column(name="fechaNacimiento_alumno", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotBlank(message="Debe ingresar la fecha de nacimiento")
	@Past(message="La fecha de nacimiento debe ser anterior a la fecha actual")
	private LocalDate fechaNacimiento;
	
	@Column(name="domicilio_alumno", nullable = false)
	@NotBlank(message="Debe ingresar el domicilio")
	private String domicilio;

}
