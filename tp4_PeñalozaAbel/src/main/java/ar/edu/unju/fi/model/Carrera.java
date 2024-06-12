package ar.edu.unju.fi.model;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Carrera {
	private Integer codigo;
	private String nombre;
	private Byte cantidadAnios;
	private boolean estado;

}
