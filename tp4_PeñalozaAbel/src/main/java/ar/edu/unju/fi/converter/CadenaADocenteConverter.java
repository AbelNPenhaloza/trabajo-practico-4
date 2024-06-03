package ar.edu.unju.fi.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.edu.unju.fi.collections.CollectionDocente;
import ar.edu.unju.fi.model.Docente;

@Component
public class CadenaADocenteConverter implements Converter<String, Docente> {

	@Override
	public Docente convert(String source) {
		if (source == null || source.isEmpty()) {
			return null;
		}
		try {
			Integer legajo = Integer.valueOf(source);
			return CollectionDocente.buscarDocente(legajo);
		} catch (NumberFormatException e) {
			return null; // Manejo de errores si el código no es un número válido
		}
	}

}
