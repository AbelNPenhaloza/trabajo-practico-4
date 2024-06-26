package ar.edu.unju.fi.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class DocenteDTO {
	private Long idDocente;
	
	@Column(name = "docente_legajo")
	@NotBlank(message="Debe ingresar el legajo")
	@Size(min=3,max=12, message="El legajo debe contener como minimo 3 digitos y como maximo 12 digitos")
	private Integer legajo;
	
	@Column(name="docente_nombre")
	@NotBlank(message="Debe ingresar nombre del docente")
	@Pattern(regexp= "[a-z A-Z]*", message="Debe ingresar unicamente letras")
	private String nombre;
	
	@Column(name="docente_apellido")
	@NotBlank(message="Debe ingresar apellido del docente")
	@Pattern(regexp= "[a-z A-Z]*", message="Debe ingresar unicamente letras")
	private String apellido;
	
	@Column(name="docente_email")
	@NotBlank(message="Debe ingresar correo del docente")
	@Email
	private String correoElectronico;
	
	@NotNull(message = "Debe seleccionar un estado")
	private Boolean estado;
	
	@Column(name = "docente_telefono")
	@NotBlank(message="Debe ingresar el telefono")
	@Pattern(regexp= "[0-9]*", message="Debe ingresar unicamente números")
	@Size(min=7,max=15, message="El legajo debe contener como minimo 7 digitos y como maximo 15 digitos")
	private String telefono;

	public DocenteDTO(
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
