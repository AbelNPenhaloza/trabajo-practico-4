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

import ar.edu.unju.fi.dto.MateriaDTO;
import ar.edu.unju.fi.model.Curso;
import ar.edu.unju.fi.model.Modalidad;
import ar.edu.unju.fi.service.ICarreraService;
import ar.edu.unju.fi.service.IDocenteService;
import ar.edu.unju.fi.service.IMateriaService;
import jakarta.validation.Valid;


@Controller
@RequestMapping("/materia")
public class MateriaController {

	private final IMateriaService materiaService;
	private final IDocenteService docenteService;
	private final ICarreraService carreraService;

	/**
	 * @param materiaService
	 * @param docenteService
	 * @param carreraService
	 */

	public MateriaController(IMateriaService materiaService, IDocenteService docenteService,
			ICarreraService carreraService) {
		this.materiaService = materiaService;
		this.docenteService = docenteService;
		this.carreraService = carreraService;
	}

	// Metodo para listar todas materias
	@GetMapping("/listado")
	public String getMateriasPage(Model model) {
		List<MateriaDTO> materias = materiaService.findAllActive();

		model.addAttribute("titulo", "Materias");
		model.addAttribute("exito", false);
		model.addAttribute("mensaje", "");
		model.addAttribute("materias", materias);
		return "materias";
	}

	// Metodo para agregar una nueva materia
	@GetMapping("/nuevo")
	public String getNuevaMateriaPage(Model model) {

		boolean edicion = false;

		model.addAttribute("materia", new MateriaDTO());
		model.addAttribute("edicion", edicion);
		model.addAttribute("titulo", "Nueva Materia");
		model.addAttribute("modalidades", Modalidad.values());
		model.addAttribute("cursos", Curso.values());
		model.addAttribute("docentes", docenteService.findAllActive());
		model.addAttribute("carreras", carreraService.findAllActive());

		return "materia";
	}

	// Metodo para guardar una materia nueva usando el boton guardar
	@PostMapping("/guardar")
	public ModelAndView guardarMateria(@Valid @ModelAttribute("materia") MateriaDTO materiaDTO, BindingResult result,
			Model model) {

		ModelAndView modelView = new ModelAndView("materias");
		String mensaje;

		if (result.hasErrors()) {

			model.addAttribute("edicion", false);
			model.addAttribute("titulo", "Nueva Materia");
			model.addAttribute("modalidades", Modalidad.values());
			model.addAttribute("cursos", Curso.values());
			model.addAttribute("docentes", docenteService.findAllActive());
			model.addAttribute("carreras", carreraService.findAllActive());
			modelView.setViewName("materia");
			return modelView;
		}

		try {
			materiaService.save(materiaDTO);
			mensaje = "Materia guardada con éxito!";
			model.addAttribute("exito", true);
		} catch (Exception e) {
			mensaje = "La Materia no se pudo guardar: " + e.getMessage();
			model.addAttribute("exito", false);
		}

		model.addAttribute("mensaje", mensaje);
		model.addAttribute("cursos", Curso.values());
		model.addAttribute("modalidades", Modalidad.values());
		modelView.addObject("materias", materiaService.findAllActive());

		return modelView;

	}



	// Metodo que presenta el formulario para modificar
	@GetMapping("/modificar/{idMateria}")
	public String getModificarMateriaPage(Model model, @PathVariable(value = "idMateria") Long idMateria) {

		MateriaDTO materiaDTO = materiaService.findById(idMateria);
		// toma valor verdadero para editar
		boolean edicion = true;

		model.addAttribute("edicion", edicion);
		model.addAttribute("materia", materiaDTO);
		model.addAttribute("cursos", Curso.values());
		model.addAttribute("modalidades", Modalidad.values());
		model.addAttribute("docentes", docenteService.findAllActive());
		model.addAttribute("carreras", carreraService.findAllActive());
		model.addAttribute("titulo", "Modificar Materia");

		return "materia";

	}

	// Metodo para modificar y guardar la modificacion.
	@PostMapping("/modificar")
	public String modificarMateria(@Valid @ModelAttribute("materia") MateriaDTO materiaDTO, BindingResult result,
			Model model) {

		if (result.hasErrors()) {
			model.addAttribute("edicion", true);
			model.addAttribute("titulo", "Modificar Materia");
			model.addAttribute("modalidades", Modalidad.values());
			model.addAttribute("cursos", Curso.values());
			model.addAttribute("docentes", docenteService.findAllActive());
			model.addAttribute("carreras", carreraService.findAllActive());
			return "materia";
		}

		try {
			materiaService.editarMateria(materiaDTO);
			model.addAttribute("exito", true);
			model.addAttribute("mensaje", "La Materia fue modificada con éxito!");
		} catch (Exception e) {
			model.addAttribute("exito", false);
			model.addAttribute("mensaje", "Error al modificar la Materia: " + e.getMessage());
		}

		model.addAttribute("materias", materiaService.findAllActive());
		model.addAttribute("docentes", docenteService.findAllActive());
		model.addAttribute("carreras", carreraService.findAllActive());
		model.addAttribute("titulo", "Materias");
		return "materias";

	}

	// Metodo para eliminar una materia
	@GetMapping("/eliminar/{idMateria}")
	public String eliminarMateria(Model model, @PathVariable(value = "idMateria") Long idMateria) {
		materiaService.deleteById(idMateria);
		model.addAttribute("materias", materiaService.findAllActive());
		model.addAttribute("titulo", "Materias");
		return "materias";
	}

	@GetMapping("/alumnos/filtro")
	public String filtroAlumnosPorMateria(Model model) {
		model.addAttribute("materias", materiaService.findAllActive());
		return "filtro-alumnos-materia";
	}

	@PostMapping("/alumnos/filtro")
	public String filtrarAlumnosPorMateria(@RequestParam Long idMateria, Model model) {
		MateriaDTO materia = materiaService.findById(idMateria);
		model.addAttribute("alumnos", materia.getAlumnos());
		model.addAttribute("materia", materia);
		model.addAttribute("materias", materiaService.findAllActive());
		return "filtro-alumnos-materia";
	}


}
