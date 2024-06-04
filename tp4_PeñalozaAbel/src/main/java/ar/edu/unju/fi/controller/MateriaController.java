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

import ar.edu.unju.fi.collections.CollectionCarrera;
import ar.edu.unju.fi.collections.CollectionDocente;
import ar.edu.unju.fi.collections.CollectionMateria;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.model.Curso;
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.model.Modalidad;

@Controller
@RequestMapping("/materia")
public class MateriaController {

	@Autowired
	private Materia materia;
	// private Modalidad modalidad;
	@Autowired
	private Carrera carrera;
	@Autowired
	private Docente docente;

	// Metodo para listar todas materias
	@GetMapping("/listado")
	public ModelAndView getMateriasPage() {
		ModelAndView mav = new ModelAndView("materias");

		mav.addObject("materias", CollectionMateria.getMaterias());

		return mav;

	}

	// Metodo para agregar una nueva materia
	@GetMapping("/nuevo")
	public ModelAndView getNuevaMateriaPage() {

		materia = new Materia();
		ModelAndView mav = new ModelAndView("materia");
		mav.addObject("materia", materia);
		mav.addObject("materias", CollectionMateria.getMaterias());
		return mav;
	}

	// Metodo para guardar una materia nueva usando el boton guardar
	@PostMapping("/guardar")
	public ModelAndView guardarMateria(@ModelAttribute("materia") Materia materia, Model model) {
		ModelAndView modelView = new ModelAndView("materias");
		String mensaje;

		docente= CollectionDocente.buscarDocente(materia.getDocente().getLegajo());
		carrera= CollectionCarrera.buscarCarrera(materia.getCarrera().getCodigo());
		materia.setDocente(docente);
		materia.setCarrera(carrera);

		boolean exito = CollectionMateria.agregarMateria(materia);

		if (exito) {
			mensaje = "Materia guardada con exito!";
		} else {
			mensaje = "Materia no se pudo guardar";
		}

		model.addAttribute("exito", exito);
		model.addAttribute("mensaje", mensaje);
		modelView.addObject("materias", CollectionMateria.getMaterias());

		return modelView;

	}



	// Metodo que presenta el formulario para modificar
	@GetMapping("/modificar/{codigo}")
	public String getModificarMateriaPage(Model model, @PathVariable(value = "codigo") Integer codigo) {
		Materia materiaEncontrada = new Materia();
		// toma valor verdadero para editar
		boolean edicion = true;
		// hacer validadcion si o si
		materiaEncontrada = CollectionMateria.buscarMateria(codigo);
		model.addAttribute("edicion", edicion);
		model.addAttribute("materia", materiaEncontrada);
		model.addAttribute("cursos", Curso.values());
		model.addAttribute("modalidades", Modalidad.values());
		model.addAttribute("docentes", CollectionDocente.getDocentes());
		model.addAttribute("carreras", CollectionCarrera.getCarreras());
		model.addAttribute("titulo", "Modificar Materia");

		return "materia";

	}

	// Metodo para modificar y guardar la modificacion en la Collection
	@PostMapping("/modificar")
	public String modificarMateria(@ModelAttribute("materia") Materia materia) {
		CollectionMateria.modificarMateria(materia);
		return "redirect:/materia/listado";
	}

	// Metodo para eliminar una materia
	@GetMapping("/eliminar/{codigo}")
	public String eliminarMateria(@PathVariable(value = "codigo") Integer codigo) {
		CollectionMateria.eliminarMateria(codigo);
		return "redirect:/materia/listado";
	}
}
