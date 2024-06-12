package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.dto.AlumnoDTO;
import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.service.IAlumnoService;

@Controller
@RequestMapping("/alumno")
public class AlumnoController {

	@Autowired
	private AlumnoDTO alumnoDTO;
	// private Alumno alumno;

	@Autowired
	private IAlumnoService alumnoService;

	@GetMapping("/listado")
	public String getAlumnosPage(Model model) {

		model.addAttribute("titulo", "Alumnos");
		model.addAttribute("exito", false);
		model.addAttribute("mensaje", "");
		model.addAttribute("alumnos", alumnoService.findAll());
		return "alumnos";
	}

	// Metodo para agregar una nuevo Alumno
	@GetMapping("/nuevo")
	public String getNuevoAlumnoPage(Model model) {

		boolean edicion = false;

		model.addAttribute("alumno", alumnoDTO);
		model.addAttribute("edicion", edicion);
		model.addAttribute("titulo", "Nuevo Alumno");

		return "alumno";
	}

	// Metodo para guardar un alumno nuevo usando el boton guardar
	@PostMapping("/guardar")
	public ModelAndView guardarAlumno(@ModelAttribute("alumno") AlumnoDTO alumnoDTO, Model model) {

		ModelAndView modelView = new ModelAndView("alumnos");
		String mensaje;
		boolean exito = alumnoService.save(alumnoDTO);
		if (exito) {
			mensaje = "Alumno guardado con exito";
		} else {
			mensaje = "El Alumno no se pudo guardar";
		}
		model.addAttribute("exito", exito);
		model.addAttribute("mensaje", mensaje);
		modelView.addObject("alumnos", alumnoService.findAll());

		return modelView;
	}

	// Metodo que presenta el formulario para modificar
	@GetMapping("/modificar/{lu}")
	public String getModificarAlumnoPage(Model model, @PathVariable(value = "lu") Integer lu) {

		Alumno alumnoEncontrado = new Alumno();
		// toma valor verdadero para editar
		boolean edicion = true;
		// hacer validadcion si o si
		AlumnoDTO alumnoEncontradoDTO = alumnoService.findById(lu);

		model.addAttribute("edicion", edicion);
		model.addAttribute("alumno", alumnoEncontradoDTO);
		model.addAttribute("titulo", "Modificar Alumno");

		return "alumno";
	}

	// Metodo para modificar y guardar la modificacion en la Collection
	@PostMapping("/modificar")
	public String modificarAlumno(@ModelAttribute("alumno") AlumnoDTO alumnoDTO, Model model) {

		boolean exito = false;
		String mensaje = "";
		try {
			alumnoService.edit(alumnoDTO);
			mensaje = "El alumno con codigo " + alumnoDTO.getLu() + " fue modificado con exito!";
			exito = true;
		} catch (Exception e) {
			mensaje = e.getMessage();
			e.printStackTrace();
		}

		model.addAttribute("alumnos", alumnoService.findAll());
		model.addAttribute("exito", exito);
		model.addAttribute("mensaje", mensaje);
		model.addAttribute("titulo", "Alumnos");
		return "alumnos";
	}

	// Metodo para eliminar una alumno
	@GetMapping("/eliminar/{lu}")
	public String eliminarAlumno(@PathVariable(value = "lu") Integer lu) {
		alumnoService.deleteById(lu);
		return "redirect:/alumno/listado";
	}
}
