package ar.edu.unju.fi.model;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "materias")
@Getter
@Setter
@NoArgsConstructor

public class Materia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_materia", nullable = false)
	private Long idMateria;

	@NotBlank(message = "Debe ingresar el nombre de la Materia")
	@Size(min = 3, max = 100, message = "El nombre de la materia debe tener entre 3 y 100 caracteres")
	@Pattern(regexp = "[a-z A-Z]*", message = "Debe ingresar unicamente letras")
	private String nombre;

	@NotNull(message = "Debe seleccionar un Curso")
	@Enumerated(EnumType.STRING)
	private Curso curso;

	@NotNull(message = "Debe ingresar la cantidad de horas de la Materia")
	@Min(value = 1, message = "La cantidad de horas debe ser al menos 1")
	@Max(value = 200, message = "La cantidad de horas no puede exceder 200")
	@Column(name = "cantidad_de_horas")
	private Integer cantidadDeHoras;

	@NotNull(message = "Debe seleccionar una Modalidad")
	@Enumerated(EnumType.STRING)
	private Modalidad modalidad;

	@NotNull(message = "Debe seleccionar un estado")
	private Boolean estado;

	@OneToOne
	@JoinColumn(name = "docente_id")
	@NotNull(message = "Debe seleccionar un Docente")
	private Docente docente;

	@ManyToOne
	@JoinColumn(name = "carrera_id")
	@NotNull(message = "Debe seleccionar una Carrera")
	private Carrera carrera;

	@ManyToMany
	@JoinTable(name = "materias_alumnos", joinColumns = @JoinColumn(name = "alumno_id"), inverseJoinColumns = @JoinColumn(name = "materia_id"))
	@NotNull(message = "Debe seleccionar uno o mas Alumnos")
	private List<Alumno> alumnos = new ArrayList<Alumno>();

	/**
	 * @param nombre
	 * @param curso
	 * @param cantidadDeHoras
	 * @param modalidad
	 * @param estado
	 * @param docente
	 * @param carrera
	 * @param alumnos
	 */
	public Materia(
			@NotBlank(message = "Debe ingresar el nombre de la Materia") @Size(min = 3, max = 100, message = "El nombre de la materia debe tener entre 3 y 100 caracteres") @Pattern(regexp = "[a-z A-Z]*", message = "Debe ingresar unicamente letras") String nombre,
			@NotNull(message = "Debe seleccionar un Curso") Curso curso,
			@NotNull(message = "Debe ingresar la cantidad de horas de la Materia") @Min(value = 1, message = "La cantidad de horas debe ser al menos 1") @Max(value = 200, message = "La cantidad de horas no puede exceder 200") Integer cantidadDeHoras,
			@NotNull(message = "Debe seleccionar una Modalidad") Modalidad modalidad,
			@NotNull(message = "Debe seleccionar un estado") Boolean estado,
			@NotNull(message = "Debe seleccionar un Docente") Docente docente,
			@NotNull(message = "Debe seleccionar una Carrera") Carrera carrera,
			@NotNull(message = "Debe seleccionar uno o mas Alumnos") List<Alumno> alumnos) {
		this.nombre = nombre;
		this.curso = curso;
		this.cantidadDeHoras = cantidadDeHoras;
		this.modalidad = modalidad;
		this.estado = estado;
		this.docente = docente;
		this.carrera = carrera;
		this.alumnos = alumnos;
	}

}
