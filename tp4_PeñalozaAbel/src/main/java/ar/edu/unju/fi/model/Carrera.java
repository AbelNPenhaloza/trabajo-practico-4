package ar.edu.unju.fi.model;

import org.springframework.stereotype.Component;

@Component
public class Carrera {
	private int codigo;
	private String nombre;
	private byte cantidadAnio;
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
	public Carrera(int codigo, String nombre, byte cantidadAnio, boolean estado) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.cantidadAnio = cantidadAnio;
		this.estado = estado;
	}

	// Metodos accesores de la clase Carrera
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public byte getCantidadAnio() {
		return cantidadAnio;
	}

	public void setCantidadAnio(byte cantidadAnio) {
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
