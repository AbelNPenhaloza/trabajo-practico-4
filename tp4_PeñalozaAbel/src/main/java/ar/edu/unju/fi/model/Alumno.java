package ar.edu.unju.fi.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="alumnos")

public class Alumno {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="alumno_id", nullable = false)
	private Long idAlumno;
			
	@Column(name="alumno_lu", nullable = false)
	@NotNull(message="Debe ingresar la libreta universitaria")
	private Integer lu;
		
	@Column(name="alumno_dni", nullable = false)
	@NotNull(message="Debe ingresar el dni")	
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
	
	@NotNull(message = "Debe seleccionar un estado")
	@Column(name="alumno_estado", nullable = false, columnDefinition = "boolean default true")
	private Boolean estado;
	
	@Column(name="alumno_fecha_de_nacimiento", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message="Debe ingresar la fecha de nacimiento")
	@Past(message="La fecha de nacimiento debe ser anterior a la fecha actual")
	private LocalDate fechaNacimiento;
	
	@Column(name="alumno_domicilio", nullable = false)
	@NotBlank(message="Debe ingresar el domicilio")
	private String domicilio;

	@ManyToOne
	@JoinColumn(name="carrera_id")
	@NotNull(message = "Debe seleccionar una carrera.")
	private Carrera carrera;
	
	@ManyToMany(mappedBy = "alumnos")
	private List<Materia> materias = new ArrayList<Materia>();

	public Alumno(@NotNull(message = "Debe ingresar la libreta universitaria") Integer lu,
			@NotNull(message = "Debe ingresar el dni") Integer dni,
			@NotBlank(message = "Debe ingresar el nombre") @Size(min = 3, max = 20, message = "El nombre debe contener como mínimo 3 caracteres y como máximo 20 caracteres") @Pattern(regexp = "[a-z A-Z]*", message = "Debe ingresar únicamente letras") String nombre,
			@NotBlank(message = "Debe ingresar el apellido") @Size(min = 3, max = 20, message = "El apellido debe contener como mínimo 3 caracteres y como máximo 20 caracteres") @Pattern(regexp = "[a-z A-Z]*", message = "Debe ingresar únicamente letras") String apellido,
			@NotBlank(message = "Debe ingresar el correo electronico") @Email String correoElectronico,
			@NotBlank(message = "Debe ingresar el telefono") String telefono,
			@NotNull(message = "Debe seleccionar un estado") Boolean estado,
			@NotNull(message = "Debe ingresar la fecha de nacimiento") @Past(message = "La fecha de nacimiento debe ser anterior a la fecha actual") LocalDate fechaNacimiento,
			@NotBlank(message = "Debe ingresar el domicilio") String domicilio,
			@NotNull(message = "Debe seleccionar una carrera.") Carrera carrera, List<Materia> materias) {
		this.lu = lu;
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correoElectronico = correoElectronico;
		this.telefono = telefono;
		this.estado = estado;
		this.fechaNacimiento = fechaNacimiento;
		this.domicilio = domicilio;
		this.carrera = carrera;
		this.materias = materias;
	}
}