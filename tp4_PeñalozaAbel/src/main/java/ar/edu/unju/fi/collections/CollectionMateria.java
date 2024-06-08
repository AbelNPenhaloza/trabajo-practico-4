package ar.edu.unju.fi.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.model.Curso;
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.model.Modalidad;

@Component
public class CollectionMateria {
	private static List<Materia> materias = new ArrayList<Materia>();

	/**
	 * Metodo estatico que devuelve un ArrayList de Objetos de la clase Materia.
	 **/
	public static List<Materia> getMaterias() {
		if (materias.isEmpty()) {
			// Obtener la lista de docentes del CollectionDocente
			List<Docente> docentes = CollectionDocente.getDocentes();
			// Obtener la lista de carreras del CollectionCarrera
			List<Carrera> carreras = CollectionCarrera.getCarreras();

			materias.add(new Materia(1, "Prog Visual", Curso.PRIMERO, (byte) 40, Modalidad.VIRTUAL, docentes.get(0),
					carreras.get(0)));

			materias.add(new Materia(2, "Analisis Matematico", Curso.SEGUNDO, (byte) 30, Modalidad.PRESENCIAL,
					docentes.get(1), carreras.get(2)));

			materias.add(new Materia(3, "Quimica", Curso.CUARTO, (byte) 40, Modalidad.VIRTUAL, docentes.get(2),
					carreras.get(1)));

			materias.add(new Materia(4, "Diseño", Curso.QUINTO, (byte) 40, Modalidad.VIRTUAL, docentes.get(3),
					carreras.get(3)));

		}
		return materias;
	}

	/**
	 * Metodo estatico que agrega un Objeto Materia al ArrayList de materias.
	 * 
	 * @param materia a agregar
	 **/
	public static boolean agregarMateria(Materia materia) {
		return materias.add(materia) ? true : false;
	}

	/**
	 * Metodo estatico que elimina un Objetos materia del ArrayList de materias
	 * 
	 * @param codigoMateria.
	 **/
	public static void eliminarMateria(Integer codigo) {

		Iterator<Materia> iterator = materias.iterator();
		while (iterator.hasNext()) {
			if (iterator.next().getCodigo() == codigo) {
				iterator.remove();
			}
		}
	}

	/**
	 * Metodo estatico que modifica un Objetos mateia con los nuevos valores
	 * enviados en
	 * 
	 * @param materia objeto con los valores de atributos modificados.
	 **/
	public static void modificarMateria(Materia materia) throws Exception {
		boolean encontrado = false;

		try {
			for (Materia m : materias) {

				if (m.getCodigo() == materia.getCodigo()) {
					m.setNombre(materia.getNombre());
					m.setCurso(materia.getCurso());
					m.setCantidadDeHoras(materia.getCantidadDeHoras());
					m.setModalidad(materia.getModalidad());
					m.setDocente(materia.getDocente());
					m.setCarrera(materia.getCarrera());
					encontrado = true;

				}

			}
			if (!encontrado) {
				throw new Exception("La materia con codigo " + materia.getCodigo() + "no existe");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Metodo estatico que busca un Objetos materia dentro del ArrayList, el
	 * criterio es por
	 * 
	 * @param codigo el codigo a buscar en el ArryList materias.
	 * @param return
	 **/
	public static Materia buscarMateria(Integer codigo) {

		Predicate<Materia> filterCodigo = c -> c.getCodigo() == codigo;
		Optional<Materia> materia = materias.stream().filter(filterCodigo).findFirst();
		if (materia.isPresent()) {
			return materia.get();
		} else {
			return null;
		}

	}

}
