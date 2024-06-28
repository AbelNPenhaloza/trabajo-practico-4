package ar.edu.unju.fi.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
	@Column(name="id_alumno", nullable = false)
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

	@Autowired
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="carrera_id")
	private Carrera carrera;
	
	@ManyToMany(mappedBy = "alumnos")
	private List<Alumno> alumnos = new ArrayList<Alumno>();
}