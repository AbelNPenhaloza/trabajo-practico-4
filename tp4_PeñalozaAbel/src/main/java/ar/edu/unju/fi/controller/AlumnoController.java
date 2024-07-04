package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.dto.AlumnoDTO;
import ar.edu.unju.fi.service.IAlumnoService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/alumno")
public class AlumnoController {

	@Autowired
	private AlumnoDTO alumnoDTO;

	@Autowired
	private IAlumnoService alumnoService;

	@GetMapping("/listado")
	public String getAlumnosPage(Model model) {

		model.addAttribute("titulo", "Alumnos");
		model.addAttribute("exito", false);
		model.addAttribute("mensaje", "");
		model.addAttribute("alumnos", alumnoService.findAllactive());
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
	public ModelAndView guardarAlumno(@Valid @ModelAttribute("alumno") AlumnoDTO alumnoDTO, Model model , BindingResult result) {
		ModelAndView modelView = new ModelAndView("alumnos");
		String mensaje;
		
		if(result.hasErrors()) {
			modelView = new ModelAndView("alumnos");
			model.addAttribute("edicion", false);
			model.addAttribute("titulo", "Nuevo Alumno");
			modelView.setViewName("alumno");
			return modelView;		
		}
		
		try {
			alumnoService.save(alumnoDTO);
			mensaje = "Alumno guardado con exito";
			model.addAttribute("exito", true);
		} catch (Exception e) {
			mensaje = "El Alumno no se pudo guardar";
			model.addAttribute("exito", false);	
		}
		
		
		model.addAttribute("mensaje", mensaje);
		modelView.addObject("alumnos", alumnoService.findAllactive());
		
		return modelView;
	}

	// Metodo que presenta el formulario para modificar
	@GetMapping("/modificar/{idAlumno}")
	public String getModificarAlumnoPage(Model model, @PathVariable(value = "idAlumno") Long idAlumno) {
        AlumnoDTO alumnoEncontradoDTO= new AlumnoDTO();

		// toma valor verdadero para editar
		boolean edicion = true;

		alumnoEncontradoDTO = alumnoService.findById(idAlumno);

		model.addAttribute("edicion", edicion);
		model.addAttribute("alumno", alumnoEncontradoDTO);
		model.addAttribute("titulo", "Modificar Alumno");

		return "alumno";
	}

	// Metodo para modificar y guardar la modificacion en la Collection
	@PostMapping("/modificar")
	public String modificarAlumno(@Valid @ModelAttribute("alumno") AlumnoDTO alumnoDTO, Model model, BindingResult result) {

		if(result.hasErrors()) {
			model.addAttribute("edicion",true);
			model.addAttribute("titulo","Modificar Alumno");
			return "alumno";
		}
				
		try {
			alumnoService.editarAlumno(alumnoDTO);
			model.addAttribute("exito",true);
			model.addAttribute("mensaje", "El alumno fue modificado con exito");
		} catch (Exception e) {
			model.addAttribute("exito",false);
			model.addAttribute("mensaje", "Error al modificar el alumno");
		}

		model.addAttribute("alumnos", alumnoService.findAllactive());
		model.addAttribute("titulo", "Alumnos");
		
		return "alumnos";
	}

	// Metodo para eliminar una alumno
	@GetMapping("/eliminar/{idAlumno}")
	public String eliminarAlumno(Model model, @PathVariable(value = "idAlumno") Long idAlumno) {
		alumnoService.deleteById(idAlumno);
		model.addAttribute("alumnos", alumnoService.findAllactive());
		model.addAttribute("titulo", "Alumnos");
		
		return "alumnos";
	}
}
