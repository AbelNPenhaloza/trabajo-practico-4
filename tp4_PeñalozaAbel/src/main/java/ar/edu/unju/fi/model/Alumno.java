package ar.edu.unju.fi.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Component
public class Alumno {
	private Integer lu;
	private Integer dni;
	private String nombre;
	private String apellido;
	private String correoElectronico;
	private String telefono;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaNacimiento;
	private String domicilio;


	/**
	 * Contructor por defecto
	 */
	public Alumno() {
	}

	/**
	 * Contructor parametrizado
	 * 
	 * * @param lu
	 * 
	 * @param dni
	 * @param nombre
	 * @param apellido
	 * @param correoElectronico
	 * @param telefono
	 * @param fechaNacimiento
	 * @param domicilio
	 */
	public Alumno(Integer lu, Integer dni, String nombre, String apellido, String correoElectronico, String telefono,
			LocalDate fechaNacimiento, String domicilio) {
		this.lu = lu;
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correoElectronico = correoElectronico;
		this.telefono = telefono;
		this.fechaNacimiento = fechaNacimiento;
		this.domicilio = domicilio;

	}

	// Metodos accesores de la clase Alumno

	public Integer getLu() {
		return lu;
	}

	public void setLu(Integer lu) {
		this.lu = lu;
	}
	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
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

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	@Override
	public String toString() {
		return "Alumno lu=" + lu + ", dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", correoElectronico=" + correoElectronico + ", telefono=" + telefono + ", fechaNacimiento="
				+ fechaNacimiento + ", domicilio=" + domicilio;
	}

}
