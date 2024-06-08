package ar.edu.unju.fi.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Docente;

@Component
public class CollectionDocente {

	private static List<Docente> docentes = new ArrayList<Docente>();

	/**
	 * Metodo estatico que devuelve un ArrayList de Objetos de la clase Docente
	 **/
	public static List<Docente> getDocentes() {
		if (docentes.isEmpty()) {
			docentes.add(new Docente(1, "Lucas", "Perez", "lucas@edu.ar", "3886078695"));
			docentes.add(new Docente(2, "Mario", "Martinez", "mario@edu.ar", "3886067890"));
			docentes.add(new Docente(3, "Laura", "Acosta", "laura@edu.ar", "3885545678"));
			docentes.add(new Docente(4, "Analia", "Toconas", "analia@edu.ar", "3885478908"));
			docentes.add(new Docente(5, "Jose", "Vega", "jose@edu.ar", "3886087980"));
		}
		return docentes;
	}

	/**
	 * Metodo estatico que agrega un Objeto Docentes al ArrayList de docentes.
	 * 
	 * @param docente a agregar
	 **/
	public static boolean agregarDocente(Docente docente) {
		return docentes.add(docente) ? true : false;
	}

	/**
	 * Metodo estatico que elimina un Objetos docente del ArrayList de docente
	 * 
	 * @param legajo Docente.
	 **/
	public static void eliminarDocente(Integer legajo) {

		Iterator<Docente> iterator = docentes.iterator();

		while (iterator.hasNext()) {

			Docente docente = iterator.next();
			if (docente.getLegajo().equals(legajo)) {
				iterator.remove();
			}
		}
	}

	/**
	 * Metodo estatico que modifica un Objeto docente con los nuevos valores
	 * enviados en
	 * 
	 * @param docente objeto con los valores de atributos modificados.
	 **/
	public static void modificarDocente(Docente docente) throws Exception {

		boolean encontrado = false;
		try {
			for (Docente d : docentes) {

				if (d.getLegajo() == docente.getLegajo()) {
					d.setNombre(docente.getNombre());
					d.setApellido(docente.getApellido());
					d.setCorreoElectronico(docente.getCorreoElectronico());
					d.setTelefono(docente.getTelefono());
					encontrado = true;
				}
			}
			if (!encontrado) {
				throw new Exception("El docente con codigo " + docente.getLegajo() + " no existe");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}

	/**
	 * Metodo estatico que busca un Objeto docente dentro del ArrayList, el criterio
	 * es por
	 * 
	 * @param legajo, el legajo a buscar en el ArryList docentes.
	 * @param return
	 **/
	public static Docente buscarDocente(Integer legajo) {

		Predicate<Docente> filterLegajo = l -> l.getLegajo() == legajo;
		Optional<Docente> docente = docentes.stream().filter(filterLegajo).findFirst();
		if (docente.isPresent()) {
			return docente.get();
		} else {
			return null;
		}

	}

}
