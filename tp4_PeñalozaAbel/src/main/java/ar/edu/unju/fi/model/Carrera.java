package ar.edu.unju.fi.model;

import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "carreras")
public class Carrera {
	
	@Id
	@Column(name = "carrera_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCarrera;
	
	@Column(name = "carrera_codigo")
	@NotNull(message = "Debe ingresar el codigo de la carrera. ")
	private Integer codigo;
	
	@Column(name = "carrera_nombre")
	@NotBlank(message = " Debe ingresar el nombre de la carrera.")
	@Pattern(regexp = "^[\\p{L}0-9\\s]{3,}$", message = "Debe ingresar unicamente letras y numeros")
	private String nombre;
	
	@Column(name = "carrera_cantidad_años")
	@NotNull(message = "Debe ingresar la Cantidad de Anios")
	private Byte cantidadAnios;
	
	@NotNull(message = "Debe seleccionar un estado")
	@Column(nullable = false, columnDefinition = "boolean default true")
	private Boolean estado;
	
	@OneToMany(mappedBy = "carrera", cascade = CascadeType.ALL, orphanRemoval = true)
	@NotNull(message = "Debe seleccionar uno o mas alumnos. ")
	private List<Alumno> alumnos = new ArrayList<>();
	
	@OneToMany(mappedBy = "carrera", cascade = CascadeType.ALL, orphanRemoval = true)
	@NotNull(message = "Debe seleccionar una o mas materias. ")
	private List<Materia> materias = new ArrayList<>();

	public Carrera(
			@NotNull(message = "Debe ingresar el codigo de la carrera. ") Integer codigo,
			@NotBlank(message = " Debe ingresar el nombre de la carrera.") @Pattern(regexp = "^[\\p{L}0-9\\s]{3,}$",
			message = "Debe ingresar unicamente letras y numeros") String nombre,
			@NotNull(message = "Debe ingresar la Cantidad de Anios") Byte cantidadAnios,
			@NotNull(message = "Debe seleccionar un estado") Boolean estado,
			@NotNull(message = "Debe seleccionar uno o mas alumnos. ") List<Alumno> alumnos,
			@NotNull(message = "Debe seleccionar una o mas materias. ") List<Materia> materias) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.cantidadAnios = cantidadAnios;
		this.estado = estado;
		this.alumnos = alumnos;
		this.materias = materias;
	}

	

	


	

}