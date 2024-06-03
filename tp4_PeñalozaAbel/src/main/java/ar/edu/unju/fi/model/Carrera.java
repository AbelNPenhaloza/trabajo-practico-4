package ar.edu.unju.fi.model;

import org.springframework.stereotype.Component;

@Component
public class Carrera {
	private Integer codigo;
	private String nombre;
	private Byte cantidadAnios;
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
	 * @param cantidadAnios
	 * @param estado
	 */
	public Carrera(Integer codigo, String nombre, Byte cantidadAnios, boolean estado) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.cantidadAnios = cantidadAnios;
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

	public Byte getCantidadAnios() {
		return cantidadAnios;
	}

	public void setCantidadAnios(Byte cantidadAnios) {
		this.cantidadAnios = cantidadAnios;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Carrera codigo= " + codigo + ", nombre= " + nombre + ", cantidadAnio= " + cantidadAnios + ", estado= "
				+ estado;
	}

}
