package ar.edu.unju.fi.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class AlumnoDTO {
	private Integer lu;
	private Integer dni;
	private String nombre;
	private String apellido;
	private String correoElectronico;
	private String telefono;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaNacimiento;
	private String domicilio;
}
