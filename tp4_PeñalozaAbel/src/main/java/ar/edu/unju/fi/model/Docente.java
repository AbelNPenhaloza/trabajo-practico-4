package ar.edu.unju.fi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="docentes")
@Getter
@Setter
@NoArgsConstructor

public class Docente {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "docente_id", nullable = false)
	private Long idDocente;
	
	@NotNull(message = "Debe ingresar el legajo")
	@Column(name = "docente_legajo", nullable = false, unique = true)
	private Integer legajo;
	
	@NotBlank(message="Debe ingresar nombre del docente")
	@Pattern(regexp = "^[a-zA-Z\\s]{3,40}$", message = "El nombre debe contener solo letras y espacios, y tener entre 3 y 40 caracteres")
	@Column(name="docente_nombre", nullable = false)
	private String nombre;
	
	@NotBlank(message="Debe ingresar apellido del docente")
	@Pattern(regexp = "^[a-zA-Z\\s]{3,40}$", message = "El nombre debe contener solo letras y espacios, y tener entre 3 y 40 caracteres")
	@Column(name="docente_apellido", nullable = false)
	private String apellido;
	
	@NotBlank(message="Debe ingresar correo del docente")
	@Email
	@Column(name="docente_email", nullable = false, unique = true)
	private String correoElectronico;
	
	@NotNull(message = "Debe seleccionar un estado")
	@Column(name = "docente_estado", nullable = false, columnDefinition = "boolean default true")
	private Boolean estado;
	
	@NotBlank(message="Debe ingresar el telefono")
	@Pattern(regexp = "\\d+", message = "El teléfono debe contener solo dígitos")
    @Size(min = 7, max = 15, message = "El teléfono debe tener entre 7 y 15 dígitos")
	@Column(name = "docente_telefono", nullable=false)
	private String telefono;

	public Docente(@NotNull(message = "Debe ingresar el legajo") Integer legajo,
			@NotBlank(message = "Debe ingresar nombre del docente") @Pattern(regexp = "^[a-zA-Z\\s]{3,40}$", message = "El nombre debe contener solo letras y espacios, y tener entre 3 y 40 caracteres") String nombre,
			@NotBlank(message = "Debe ingresar apellido del docente") @Pattern(regexp = "^[a-zA-Z\\s]{3,40}$", message = "El nombre debe contener solo letras y espacios, y tener entre 3 y 40 caracteres") String apellido,
			@NotBlank(message = "Debe ingresar correo del docente") @Email String correoElectronico,
			@NotNull(message = "Debe seleccionar un estado") Boolean estado,
			@NotBlank(message = "Debe ingresar el telefono") @Pattern(regexp = "\\d+", message = "El teléfono debe contener solo dígitos") @Size(min = 7, max = 15, message = "El teléfono debe tener entre 7 y 15 dígitos") String telefono) {
		this.legajo = legajo;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correoElectronico = correoElectronico;
		this.estado = estado;
		this.telefono = telefono;
	}
	
}
