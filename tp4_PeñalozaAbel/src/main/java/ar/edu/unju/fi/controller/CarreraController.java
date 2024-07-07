package ar.edu.unju.fi.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.edu.unju.fi.dto.AlumnoDTO;
import ar.edu.unju.fi.dto.CarreraDTO;
import ar.edu.unju.fi.service.IAlumnoService;
import ar.edu.unju.fi.service.ICarreraService;
import ar.edu.unju.fi.service.IMateriaService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/carrera")
public class CarreraController {
	
	private final ICarreraService carreraService;
	
	private final IAlumnoService alumnoService;
	
	private final IMateriaService materiaService;
	
	public CarreraController(ICarreraService carreraService, IAlumnoService alumnoService,
			IMateriaService materiaService) {
		this.carreraService = carreraService;
		this.alumnoService = alumnoService;
		this.materiaService = materiaService;
	}

	@GetMapping("/listado")
	public String getCarrerasPage(Model model) {

		model.addAttribute("titulo", "Carreras");
		model.addAttribute("exito", false);
		model.addAttribute("mensaje", "");
		model.addAttribute("carreras", carreraService.findAllActive());
		return "carreras";

	}

	// Metodo para agregar una nueva carrera
	@GetMapping("/nuevo")
	public String getNuevaCarreraPage(Model model) {
		boolean edicion = false;

		model.addAttribute("carrera", new CarreraDTO());
		model.addAttribute("edicion", edicion);
		model.addAttribute("titulo", "Nueva Carrera");
		model.addAttribute("alumnos", alumnoService.findAll());
		model.addAttribute("materias", materiaService.findAll());

		return "carrera";

	}

	// Metodo para guardar una carrera nueva usando el boton guardar
	@PostMapping("/guardar")
	public ModelAndView guardarCarrera(@Valid @ModelAttribute("carrera") CarreraDTO carreraDTO, BindingResult result, Model model) {

		ModelAndView modelView = new ModelAndView("carreras");
		String mensaje;
		
		if (result.hasErrors()) {
			model.addAttribute("edicion", false);
			model.addAttribute("titulo", "Nueva Carrera");
			model.addAttribute("alumnos", alumnoService.findAll());
			model.addAttribute("materias", materiaService.findAll());
			modelView.setViewName("carrera");
			return modelView;
		}

		try {
			carreraService.save(carreraDTO);
			mensaje= "Carrera guardada con éxito!";
			model.addAttribute("exito", true);
		} catch (Exception e) {
			mensaje= "Carrera no se pudo guardar: " + e.getMessage();
			model.addAttribute("exito", false);
		}
		
		model.addAttribute("mensaje", mensaje);
		modelView.addObject("carreras", carreraService.findAllActive());

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
		model.addAttribute("alumnos", alumnoService.findAll());
		model.addAttribute("materias", materiaService.findAll());
		model.addAttribute("titulo", "Modificar Carrera");


		return "carrera";
	}

	// Metodo para modificar y guardar la modificacion en la Collection
	@PostMapping("/modificar")
	public String modificarCarrera(@Valid @ModelAttribute("carrera") CarreraDTO carreraDTO, BindingResult result,
			Model model) {

		if (result.hasErrors()) {
			model.addAttribute("edicion", true);
			model.addAttribute("titulo", "Modificar Carrera");
			model.addAttribute("alumnos", alumnoService.findAll());
			model.addAttribute("materias", materiaService.findAll());
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
		model.addAttribute("carreras", carreraService.findAllActive());
		model.addAttribute("alumnos", alumnoService.findAll());
		model.addAttribute("materias", materiaService.findAll());
		model.addAttribute("titulo", "Carreras");
		return "carreras";

	}

	// Metodo para eliminar una carrera
	@GetMapping("/eliminar/{idCarrera}")
	public String eliminarCarrera(Model model, @PathVariable(value = "idCarrera") Integer idCarrera, 
			RedirectAttributes redirectAttributes) {
		try {
			carreraService.deleteById(idCarrera);
			redirectAttributes.addFlashAttribute("exito", true);
			redirectAttributes.addFlashAttribute("mensaje", "Carrera eliminada exitosamnete. ");
		} catch(EntityNotFoundException e) {
			redirectAttributes.addFlashAttribute("exito", false);
			redirectAttributes.addFlashAttribute("mensaje", e.getMessage());
		}catch (Exception e) {
			redirectAttributes.addFlashAttribute("exito", false);
			redirectAttributes.addFlashAttribute("mensaje", "Error al eliminar la carrera. Por favor, intentelo de nuevo.");
		}
		return "redirect:/carrera/listado";
	}
	
	@GetMapping("/alumnos")
	public String filtroAlumnosPorCarrera(Model model) {
		model.addAttribute("carreras", carreraService.findAllActive());
		return "consultar-alumnos-carrera";
	}

	@PostMapping("/alumnos")
	public String filtrarAlumnosPorCarrera(@RequestParam Integer idCarrera, Model model) {
		CarreraDTO carrera = carreraService.findById(idCarrera);
		List<AlumnoDTO> alumnos = carreraService.findAlumnosByCarrera(idCarrera);
		model.addAttribute("alumnos", alumnos);
		model.addAttribute("carrera", carrera);
		model.addAttribute("carreras", carreraService.findAllActive());
		return "consultar-alumnos-carrera";
	}
}