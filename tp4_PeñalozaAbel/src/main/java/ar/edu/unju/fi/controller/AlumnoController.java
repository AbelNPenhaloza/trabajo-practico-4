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

import ar.edu.unju.fi.collections.CollectionAlumno;
import ar.edu.unju.fi.model.Alumno;

@Controller
@RequestMapping("/alumno")
public class AlumnoController {

	@Autowired
	private Alumno alumno;

	@GetMapping("/listado")
	public String getAlumnosPage(Model model) {

		model.addAttribute("titulo", "Alumnos");
		model.addAttribute("exito", false);
		model.addAttribute("mensaje", "");
		model.addAttribute("alumnos", CollectionAlumno.getAlumnos());
		return "alumnos";
	}

	// Metodo para agregar una nuevo Alumno
	@GetMapping("/nuevo")
	public String getNuevoAlumnoPage(Model model) {

		boolean edicion = false;

		model.addAttribute("alumno", alumno);
		model.addAttribute("edicion", edicion);
		model.addAttribute("titulo", "Nuevo Alumno");

		return "alumno";
	}

	// Metodo para guardar un alumno nuevo usando el boton guardar
	@PostMapping("/guardar")
	public ModelAndView guardarAlumno(@ModelAttribute("alumno") Alumno alumno, Model model) {

		ModelAndView modelView = new ModelAndView("alumnos");
		String mensaje;
		boolean exito = CollectionAlumno.agregarAlumno(alumno);
		if (exito) {
			mensaje = "Alumno guardado con exito";
		} else {
			mensaje = "El Alumno no se pudo guardar";
		}
		model.addAttribute("exito", exito);
		model.addAttribute("mensaje", mensaje);
		modelView.addObject("alumnos", CollectionAlumno.getAlumnos());

		return modelView;
	}

	// Metodo que presenta el formulario para modificar
	@GetMapping("/modificar/{lu}")
	public String getModificarAlumnoPage(Model model, @PathVariable(value = "lu") Integer lu) {

		Alumno alumnoEncontrado = new Alumno();
		// toma valor verdadero para editar
		boolean edicion = true;
		// hacer validadcion si o si
		alumnoEncontrado = CollectionAlumno.buscarAlumno(lu);

		model.addAttribute("edicion", edicion);
		model.addAttribute("alumno", alumnoEncontrado);
		model.addAttribute("titulo", "Modificar Alumno");

		return "alumno";
	}

	// Metodo para modificar y guardar la modificacion en la Collection
	@PostMapping("/modificar")
	public String modificarAlumno(@ModelAttribute("alumno") Alumno alumno, Model model) {

		boolean exito = false;
		String mensaje = "";
		try {
			CollectionAlumno.modificarAlumno(alumno);
			mensaje = "El alumno con codigo " + alumno.getLu() + " fue modificado con exito!";
			exito = true;
		} catch (Exception e) {
			mensaje = e.getMessage();
			e.printStackTrace();
		}

		model.addAttribute("alumnos", CollectionAlumno.getAlumnos());
		model.addAttribute("exito", exito);
		model.addAttribute("mensaje", mensaje);
		model.addAttribute("titulo", "Alumnos");
		return "alumnos";
	}

	// Metodo para eliminar una alumno
	@GetMapping("/eliminar/{lu}")
	public String eliminarAlumno(@PathVariable(value = "lu") Integer lu) {
		CollectionAlumno.eliminarAlumno(lu);
		return "redirect:/alumno/listado";
	}
}
