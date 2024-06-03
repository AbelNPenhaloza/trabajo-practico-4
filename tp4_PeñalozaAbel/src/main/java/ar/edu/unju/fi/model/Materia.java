package ar.edu.unju.fi.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Materia {
	private Integer codigo;
	private String nombre;
	private String curso;
	private Byte cantidadDeHoras;
	private Modalidad modalidad;
	@Autowired
	private Docente docente;
	@Autowired
	private Carrera carrera;

	/**
	 * Constructor por defecto
	 */
	public Materia() {
	}

	/**
	 * Constructor parametrizado
	 * 
	 * @param codigo
	 * @param nombre
	 * @param curso
	 * @param cantidadDeHoras
	 * @param modalidad
	 * @param docente
	 * @param carrera
	 */
	public Materia(Integer codigo, String nombre, String curso, Byte cantidadDeHoras, Modalidad modalidad,
			Docente docente, Carrera carrera) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.curso = curso;
		this.cantidadDeHoras = cantidadDeHoras;
		this.modalidad = modalidad;
		this.docente = docente;
		this.carrera = carrera;
	}

	// Metodos accesores de la clase Materia

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

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public Byte getCantidadDeHoras() {
		return cantidadDeHoras;
	}

	public void setCantidadDeHoras(Byte cantidadDeHoras) {
		this.cantidadDeHoras = cantidadDeHoras;
	}

	public Modalidad getModalidad() {
		return modalidad;
	}

	public void setModalidad(Modalidad modalidad) {
		this.modalidad = modalidad;
	}

	public Docente getDocente() {
		return docente;
	}

	public void setDocente(Docente docente) {
		this.docente = docente;
	}

	public Carrera getCarrera() {
		return carrera;
	}

	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}

	@Override
	public String toString() {
		return "Materia codigo=" + codigo + ", nombre=" + nombre + ", curso=" + curso + ", cantidadDeHoras="
				+ cantidadDeHoras + ", modalidad=" + modalidad + ", docente=" + docente + ", carrera=" + carrera;
	}

}
