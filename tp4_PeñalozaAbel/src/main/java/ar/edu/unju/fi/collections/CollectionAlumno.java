package ar.edu.unju.fi.collections;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Alumno;

@Component
public class CollectionAlumno {

	private static List<Alumno> alumnos = new ArrayList<Alumno>();

	/**
	 * Metodo estatico que devuelve un ArrayList de Objetos de la clase Alumno.
	 **/
	public static List<Alumno> getAlumnos(){
		if(alumnos.isEmpty()) {
			alumnos.add(new Alumno(3512, 35120345, "Abel", "Perez", "abel@gmail.com", "3887987980",
					LocalDate.of(1980, 11, 23), "Las Heras 120"));

			alumnos.add(new Alumno(3412, 34120345, "Lucas", "Rodriguez", "lucas@gmail.com", "3886087324",
					LocalDate.of(1990, 3, 21), "Las Camelias 435"));

			alumnos.add(new Alumno(2845, 28456657, "Monica", "Cejas", "monica@gmail.com", "3884567589",
					LocalDate.of(1999, 12, 28), "San Cayetano 678"));

			alumnos.add(new Alumno(3845, 38456089, "Ana", "Quiroga", "ana@gmail.com", "3886778697",
					LocalDate.of(1986, 5, 2), "Cbo Quispe 326"));
		}
		return alumnos;
	}

	/**
	 * Metodo estatico que agrega un Objeto Alumno al ArrayList de alumnos.
	 * 
	 * @param alumno a agregar
	 **/
	public static boolean agregarAlumno(Alumno alumno) {
		return alumnos.add(alumno) ? true : false;
	}

	/**
	 * Metodo estatico que elimina un Objetos alumno del ArrayList de alumnos
	 * 
	 * @param lu Alumno.
	 **/
	public static void eliminarAlumno(Integer lu) {
		Iterator<Alumno> iterator = alumnos.iterator();
		while (iterator.hasNext()) {
			Alumno alumno = iterator.next();
			if (alumno.getLu().equals(lu)) {
				iterator.remove();
			}
		}
	}

	/**
	 * Metodo estatico que modifica un Objetos alumno con los nuevos valores
	 * enviados en
	 * 
	 * @param alumno objeto con los valores de atributos modificados.
	 **/
	public static void modificarAlumno(Alumno alumno) throws Exception {
		boolean encontrado = false;
		try {
			for (Alumno a : alumnos) {

				if (a.getLu().equals(alumno.getLu())) {
					a.setDni(alumno.getDni());
					a.setNombre(alumno.getNombre());
					a.setApellido(alumno.getApellido());
					a.setCorreoElectronico(alumno.getCorreoElectronico());
					a.setTelefono(alumno.getTelefono());
					a.setFechaNacimiento(alumno.getFechaNacimiento());
					a.setDomicilio(alumno.getDomicilio());
					encontrado = true;

				}

			}
			if (!encontrado) {
				throw new Exception("El Alumno con codigo " + alumno.getLu() + "no existe");
			}
		} catch (Exception e) {

			System.out.println(e.getMessage());
			throw e;
		}
	}

	/**
	 * Metodo estatico que busca un Objetos alumno dentro del ArrayList, el criterio
	 * es por
	 * 
	 * @param LU     la lu a buscar en el ArryList alumnos.
	 * @param return
	 **/
	public static Alumno buscarAlumno(Integer lu) {

		Predicate<Alumno> filterLu = l -> l.getLu().equals(lu);
		Optional<Alumno> alumno = alumnos.stream().filter(filterLu).findFirst();
		if (alumno.isPresent()) {
			return alumno.get();
		} else {
			return null;
		}

	}

}
