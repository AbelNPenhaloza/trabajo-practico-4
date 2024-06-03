package ar.edu.unju.fi.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.edu.unju.fi.collections.CollectionCarrera;
import ar.edu.unju.fi.model.Carrera;

@Component
public class CadenaACarreraConverter implements Converter<String, Carrera> {


	@Override
	public Carrera convert(String source) {

		if (source == null || source.isEmpty()) {
			return null;
		}
		try {
			Integer codigo = Integer.valueOf(source);
			return CollectionCarrera.buscarCarrera(codigo);
		} catch (NumberFormatException e) {
			return null; // Manejo de errores si el código no es un número válido
		}
	}
}

