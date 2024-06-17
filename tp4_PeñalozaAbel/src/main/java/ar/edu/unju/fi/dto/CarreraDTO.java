package ar.edu.unju.fi.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component

public class CarreraDTO {

	private Integer codigo;
	private String nombre;
	private Byte cantidadAnios;
	private boolean estado;

}
