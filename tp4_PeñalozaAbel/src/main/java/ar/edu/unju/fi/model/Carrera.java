package ar.edu.unju.fi.model;

import org.springframework.stereotype.Component;

@Component
public class Carrera {
	private Integer codigo;
	private String nombre;
	private Byte cantidadAnio;
	private boolean estado;

	/**
	 * Constructor por defecto
	 */
	public Carrera() {
	}

	/**
	 * Constructor parametrizado
	 * 
	 * @param codigo
	 * @param nombre
	 * @param cantidadAnio
	 * @param estado
	 */
	public Carrera(Integer codigo, String nombre, Byte cantidadAnio, boolean estado) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.cantidadAnio = cantidadAnio;
		this.estado = estado;
	}

	// Metodos accesores de la clase Carrera
	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Byte getCantidadAnio() {
		return cantidadAnio;
	}

	public void setCantidadAnio(Byte cantidadAnio) {
		this.cantidadAnio = cantidadAnio;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Carrera codigo= " + codigo + ", nombre= " + nombre + ", cantidadAnio= " + cantidadAnio + ", estado= "
				+ estado;
	}

}
