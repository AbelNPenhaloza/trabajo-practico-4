package ar.edu.unju.fi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class DocenteDTO {
	private Long idDocente;
	
	@NotNull(message="Debe ingresar el legajo")
	private Integer legajo;
	
	@NotBlank(message="Debe ingresar nombre del docente")
	@Pattern(regexp = "^[a-zA-Z\\s]{3,40}$", message = "El nombre debe contener solo letras y espacios, y tener entre 3 y 40 caracteres")
	private String nombre;
	
	@NotBlank(message="Debe ingresar apellido del docente")
	@Pattern(regexp = "^[a-zA-Z\\s]{3,40}$", message = "El nombre debe contener solo letras y espacios, y tener entre 3 y 40 caracteres")
	private String apellido;
	
	@NotBlank(message="Debe ingresar correo del docente")
	@Email
	private String correoElectronico;
	
	@NotNull(message = "Debe seleccionar un estado")
	private Boolean estado;
	
	@NotBlank(message="Debe ingresar el telefono")
	@Pattern(regexp = "\\d+", message = "El teléfono debe contener solo dígitos")
    @Size(min = 7, max = 15, message = "El teléfono debe tener entre 7 y 15 dígitos")
	private String telefono;
}
