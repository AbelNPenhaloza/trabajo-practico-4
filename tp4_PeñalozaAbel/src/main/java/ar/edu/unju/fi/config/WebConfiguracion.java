package ar.edu.unju.fi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import ar.edu.unju.fi.converter.CadenaACarreraConverter;
import ar.edu.unju.fi.converter.CadenaADocenteConverter;

@Configuration
public class WebConfiguracion implements WebMvcConfigurer {

	private final CadenaACarreraConverter cadenaACarreraConverter;
	private final CadenaADocenteConverter cadenaADocenteConverter;

	/**
	 * Constructor parametrizado
	 * 
	 * @param cadenaACarreraConverter
	 * @param cadenaADocenteConverter
	 */
	public WebConfiguracion(CadenaACarreraConverter cadenaACarreraConverter,
			CadenaADocenteConverter cadenaADocenteConverter) {
		this.cadenaACarreraConverter = cadenaACarreraConverter;
		this.cadenaADocenteConverter = cadenaADocenteConverter;
	}

	@Override
	public void addFormatters(FormatterRegistry registro) {

		registro.addConverter(cadenaACarreraConverter);
		registro.addConverter(cadenaADocenteConverter);

	}

}
