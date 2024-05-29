package ar.edu.unju.fi.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Carrera;

@Component
public class CollectionCarrera {

	private static List<Carrera> carreras = new ArrayList<Carrera>();

	/**
	 * Metodo estatico que devuelve un ArrayList de Objetos de la clase Carrera.
	 **/
	public static List<Carrera> getCarreras() {

		if (carreras.isEmpty()) {

			carreras.add(new Carrera(1, "APU", (byte) 3, true));
			carreras.add(new Carrera(2, "Ingenieria Quimica", (byte) 5, true));
			carreras.add(new Carrera(3, "Licenciatura en Sistemas", (byte) 5, true));
			carreras.add(new Carrera(4, "TUDIVJ", (byte) 3, true));
			carreras.add(new Carrera(5, "Ingenieria Electronica", (byte) 5, false));

		}
		return carreras;
	}

	/**
	 * Metodo estatico que agrega un Objetos Carrera al ArrayList de Carreras.
	 * 
	 * @param carrera a agregar
	 **/
	public static void agregarCarrera(Carrera carrera) {
		carreras.add(carrera);
	}

	/**
	 * Metodo estatico que elimina un Objetos carrera del ArrayList de carreras
	 * @param codigoCarrera.
	 **/
	public static void eliminarCarrera(Integer codigo) {
		Iterator<Carrera> iterator = carreras.iterator();
		while (iterator.hasNext()) {
			if (iterator.next().getCodigo() == codigo) {
				iterator.remove();
			}
		}
	}

	/**
	 * Metodo estatico que modifica un Objetos carrera con los nuevos valores
	 * enviados en
	 * 
	 * @param carrera objeto con los valores de atributos modificados.
	 **/
	public static void modificarCarrera(Carrera carrera) {
		for (Carrera c : carreras) {

			if (c.getCodigo() == carrera.getCodigo()) {
				c.setNombre(carrera.getNombre());
				c.setCantidadAnio(carrera.getCantidadAnio());
				c.setEstado(carrera.isEstado());
			} else {
				System.out.println("No se encuentra el codigo de la Carrera");
			}
		}
	}

	/**
	 * Metodo estatico que busca un Objetos carrera dentro del ArrayList, el
	 * criterio es por
	 * @param codigo el codigo a buscar en el ArryList carreras.
	 * @param return
	 **/
	public static Carrera buscarCarrera(Integer codigo) {

		Predicate<Carrera> filterCodigo = c -> c.getCodigo()== codigo;
		Optional<Carrera> carrera = carreras.stream().filter(filterCodigo).findFirst();
		if (carrera.isPresent()) {
			return carrera.get();
		} else {
			return null;
		}

	}
}