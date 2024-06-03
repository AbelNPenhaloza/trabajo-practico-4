package ar.edu.unju.fi.model;

import org.springframework.stereotype.Component;

@Component
public class Docente {
	private Integer legajo;
	private String nombre;
	private String apellido;
	private String correoElectronico;
	private String telefono;

	/**
	 * Constructor por defecto
	 */
	public Docente() {
	}

	/**
	 * Controlador parametrizado
	 * 
	 * @param legajo
	 * @param nombre
	 * @param apellido
	 * @param correoElectronico
	 * @param telefono
	 */
	public Docente(Integer legajo, String nombre, String apellido, String correoElectronico, String telefono) {
		this.legajo = legajo;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correoElectronico = correoElectronico;
		this.telefono = telefono;
	}

	// Metodos accesores de la clase Docente
	public Integer getLegajo() {
		return legajo;
	}

	public void setLegajo(Integer legajo) {
		this.legajo = legajo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "Docente legajo= " + legajo + ", nombre= " + nombre + ", apellido= " + apellido + ", correoElectronico= "
				+ correoElectronico + ", telefono= " + telefono;
	}

}
