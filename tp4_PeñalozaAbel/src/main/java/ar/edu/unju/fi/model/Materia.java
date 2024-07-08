package ar.edu.unju.fi.model;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
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
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "materias")
public class Materia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "materia_id", nullable = false)
	private Long idMateria;

	@NotBlank(message = "Debe ingresar el nombre de la Materia")
	@Pattern(regexp = "^[\\p{L}A-Z ]{3,100}$", message = "El nombre debe contener solo letras y espacios, y tener entre 3 y 100 caracteres")
	@Column(name = "materia_nombre", nullable = false)
	private String nombre;


	@NotNull(message = "Debe seleccionar un Curso")
	@Enumerated(EnumType.STRING)
	@Column(name = "materia_curso")
	private Curso curso;

	@NotNull(message = "Debe ingresar la cantidad de horas de la Materia")
	@Min(value = 1, message = "La cantidad de horas debe ser al menos 1")
	@Max(value = 200, message = "La cantidad de horas no puede exceder 200")
	@Column(name = "materia_horas")
	private Integer cantidadDeHoras;

	@NotNull(message = "Debe seleccionar una Modalidad")
	@Enumerated(EnumType.STRING)
	@Column(name = "materia_modalidad")
	private Modalidad modalidad;

	@NotNull(message = "Debe seleccionar un estado")
	@Column(name = "materia_estado", nullable = false, columnDefinition = "boolean default true")
	private Boolean estado = true;

	@OneToOne
	@JoinColumn(name = "docente_id")
	@NotNull(message = "Debe seleccionar un Docente")
	private Docente docente;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "carrera_id")
	@NotNull(message = "Debe seleccionar una Carrera")
	private Carrera carrera;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "materia_alumno", joinColumns = @JoinColumn(name = "materia_id"), inverseJoinColumns = @JoinColumn(name = "alumno_id"))
	@NotNull(message = "Debe seleccionar uno o más Alumnos")
	private List<Alumno> alumnos;

	/**
	 * @param nombre
	 * @param curso
	 * @param cantidadDeHoras
	 * @param modalidad
	 * @param docente
	 * @param carrera
	 * @param alumnos
	 */
	public Materia(
			@NotBlank(message = "Debe ingresar el nombre de la Materia") @Pattern(regexp = "^[a-zA-Z\\s]{3,100}$", message = "El nombre debe contener solo letras y espacios, y tener entre 3 y 100 caracteres") String nombre,
			@NotNull(message = "Debe seleccionar un Curso") Curso curso,
			@NotNull(message = "Debe ingresar la cantidad de horas de la Materia") @Min(value = 1, message = "La cantidad de horas debe ser al menos 1") @Max(value = 200, message = "La cantidad de horas no puede exceder 200") Integer cantidadDeHoras,
			@NotNull(message = "Debe seleccionar una Modalidad") Modalidad modalidad,
			@NotEmpty(message = "Debe seleccionar un Docente") Docente docente,
			@NotEmpty(message = "Debe seleccionar una Carrera") Carrera carrera,
			@NotEmpty(message = "Debe seleccionar uno o más Alumnos") List<Alumno> alumnos) {
		this.nombre = nombre;
		this.curso = curso;
		this.cantidadDeHoras = cantidadDeHoras;
		this.modalidad = modalidad;
		this.docente = docente;
		this.carrera = carrera;
		this.alumnos = alumnos;
	}

}
