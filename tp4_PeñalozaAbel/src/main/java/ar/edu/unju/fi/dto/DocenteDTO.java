package ar.edu.unju.fi.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class DocenteDTO {
	private Integer legajo;
	private String nombre;
	private String apellido;
	private String correoElectronico;
	private String telefono;
}
