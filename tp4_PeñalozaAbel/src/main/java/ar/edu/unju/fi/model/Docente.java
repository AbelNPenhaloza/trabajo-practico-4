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
	
	@NotBlank(message = "Debe ingresar el legajo")
	@Size(min=3,max=12, message="El legajo debe contener como minimo 3 digitos y como maximo 12 digitos")
	@Column(name = "docente_legajo", nullable = false)
	private Integer legajo;
	
	@NotBlank(message="Debe ingresar nombre del docente")
	@Pattern(regexp= "[a-z A-Z]*", message="Debe ingresar unicamente letras")
	@Column(name="docente_nombre", nullable = false)
	private String nombre;
	
	@NotBlank(message="Debe ingresar apellido del docente")
	@Pattern(regexp= "[a-z A-Z]*", message="Debe ingresar unicamente letras")
	@Column(name="docente_apellido", nullable = false)
	private String apellido;
	
	@NotBlank(message="Debe ingresar correo del docente")
	@Email
	@Column(name="docente_email", nullable = false)
	private String correoElectronico;
	
	@NotNull(message = "Debe seleccionar un estado")
	@Column(name = "docente_estado", nullable = false, columnDefinition = "boolean default true")
	private Boolean estado;
	
	@NotBlank(message="Debe ingresar el telefono")
	@Pattern(regexp= "[0-9]*", message="Debe ingresar unicamente números")
	@Size(min=7,max=15, message="El legajo debe contener como minimo 7 digitos y como maximo 15 digitos")
	@Column(name = "docente_telefono", nullable=false)
	private String telefono;

	public Docente(
			@NotBlank(message = "Debe ingresar el legajo") @Size(min = 3, max = 12, message = "El legajo debe contener como minimo 3 digitos y como maximo 12 digitos") Integer legajo,
			@NotBlank(message = "Debe ingresar nombre del docente") @Pattern(regexp = "[a-z A-Z]*", message = "Debe ingresar unicamente letras") String nombre,
			@NotBlank(message = "Debe ingresar apellido del docente") @Pattern(regexp = "[a-z A-Z]*", message = "Debe ingresar unicamente letras") String apellido,
			@NotBlank(message = "Debe ingresar correo del docente") @Email String correoElectronico,
			@NotNull(message = "Debe seleccionar un estado") Boolean estado,
			@NotBlank(message = "Debe ingresar el telefono") @Pattern(regexp = "[0-9]*", message = "Debe ingresar unicamente números") @Size(min = 7, max = 15, message = "El legajo debe contener como minimo 7 digitos y como maximo 15 digitos") String telefono) {
		this.legajo = legajo;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correoElectronico = correoElectronico;
		this.estado = estado;
		this.telefono = telefono;
	}
}
