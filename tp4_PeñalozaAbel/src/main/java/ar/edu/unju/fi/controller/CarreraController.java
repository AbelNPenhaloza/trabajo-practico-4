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

import ar.edu.unju.fi.collections.CollectionCarrera;
import ar.edu.unju.fi.dto.CarreraDTO;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.service.ICarreraService;
import ar.edu.unju.fi.service.imp.AlumnoServiceImpl;
import ar.edu.unju.fi.service.imp.MateriaServiceImpl;

@Controller
@RequestMapping("/carrera")
public class CarreraController {

	
	
	@Autowired
	private CarreraDTO carreraDTO;
	//private Carrera carrera;
	
	@Autowired
	private ICarreraService carreraService;

	@GetMapping("/listado")
	public String getCarrerasPage(Model model) {

		model.addAttribute("titulo", "Carreras");
		model.addAttribute("exito", false);
		model.addAttribute("mensaje", "");
		model.addAttribute("carreras", carreraService.findAll());
		return "carreras";

	}

	// Metodo para agregar una nueva carrera
	@GetMapping("/nuevo")
	public String getNuevaCarreraPage(Model model) {
		boolean edicion = false;

		model.addAttribute("carrera", carreraDTO);
		model.addAttribute("edicion", edicion);
		model.addAttribute("titulo", "Nueva Carrera");
		//model.addAttribute("alumnos", AlumnoServiceImpl.findAll());
		//model.addAttribute("materias", MateriaServiceImpl.findAll());

		return "carrera";

	}

	// Metodo para guardar una carrera nueva usando el boton guardar
	@PostMapping("/guardar")
	public ModelAndView guardarCarrera(@ModelAttribute("carrera") CarreraDTO carreraDTO, BindingResult result, Model model) {

		ModelAndView modelView = new ModelAndView("carreras");
		String mensaje;
		
		if (result.hasErrors()) {
			model.addAttribute("edicion", false);
			model.addAttribute("titulo", "Nueva Carrera");
			//model.addAttribute("alumnos", AlumnoServiceImpl.findAll());
			//model.addAttribute("materias", MateriaServiceImpl.findAll());
			modelView.setViewName("carrera");
			return modelView;
		}

		Carrera carrera = carreraService.save(carreraDTO);
		
		if (carrera != null) {
			mensaje = "carrera guardada con exito";
			model.addAttribute("exito", true);
		} else {
			mensaje = "La Carrera no se pudo guardar";
			model.addAttribute("exito", false);
		}
		
		// model.addAttribute("exito", exito);
		model.addAttribute("mensaje", mensaje);
		modelView.addObject("carreras", carreraService.findAll());

		return modelView;
		
	}

	// Metodo que presenta el formulario para modificar
	@GetMapping("/modificar/{idCarrera}")
	public String getModificarCarreraPage(Model model, @PathVariable(value = "idCarrera") Integer idCarrera) {
		CarreraDTO carreraDTO = carreraService.findById(idCarrera);
		// toma valor verdadero para editar
		boolean edicion = true;

		model.addAttribute("edicion", edicion);
		model.addAttribute("carrera", carreraDTO);
		//model.addAttribute("alumnos", alumnoService.findAll());
		//model.addAttribute("materias", materiaService.findAll());
		model.addAttribute("titulo", "Modificar Carrera");


		return "carrera";
	}

	// Metodo para modificar y guardar la modificacion en la Collection
	@PostMapping("/modificar")
	public String modificarCarrera(@ModelAttribute("carrera") CarreraDTO carreraDTO, BindingResult result, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("edicion", true);
			model.addAttribute("titulo", "Modificar Carrera");
			//model.addAttribute("docentes", alumnoService.findAll());
			//model.addAttribute("carreras", materiaService.findAll());
			return "carrera";
		}

		try {
			carreraService.editarCarrera(carreraDTO);
			model.addAttribute("exito", true);
			model.addAttribute("mensaje", "La Carrera fue modificada con éxito!");
		} catch (Exception e) {
			model.addAttribute("exito", false);
			model.addAttribute("mensaje", "Error al modificar la Carrera: " + e.getMessage());
		}
		model.addAttribute("carreras", carreraService.findAll());
		//model.addAttribute("alumnoss", alumnoService.findAll());
		//model.addAttribute("materias", materiaService.findAll());
		model.addAttribute("titulo", "Carreras");
		return "carreras";

	}

	// Metodo para eliminar una carrera
	@GetMapping("/eliminar/{idCarrera}")
	public String eliminarCarrera(Model model, @PathVariable(value = "idCarrera") Integer idCarrera) {
		carreraService.deleteById(idCarrera);
		model.addAttribute("carrerass", carreraService.findAll());
		model.addAttribute("titulo", "Carreras");
		return "carrera";
	}
}