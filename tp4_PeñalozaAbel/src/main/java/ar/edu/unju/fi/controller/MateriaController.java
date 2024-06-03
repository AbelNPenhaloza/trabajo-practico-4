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
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.model.Modalidad;

@Controller
@RequestMapping("/materia")
public class MateriaController {

	@Autowired
	private Materia materia;

	// Metodo para listar todas materias
	@GetMapping("/listado")
	public String getMateriasPage(Model model) {
		model.addAttribute("materias", CollectionMateria.getMaterias());
		model.addAttribute("docentes", CollectionDocente.getDocentes());
		model.addAttribute("carreras", CollectionCarrera.getCarreras());
		model.addAttribute("titulo", "Materias");
		return "materias";

	}

	// Metodo para agregar una nueva materia
	@GetMapping("/nuevo")
	public String getNuevaMateriaPage(Model model) {

		boolean edicion = false;

		model.addAttribute("materia", materia);
		model.addAttribute("edicion", edicion);
		model.addAttribute("modalidades", Modalidad.values());
		model.addAttribute("docentes", CollectionDocente.getDocentes());
		model.addAttribute("carreras", CollectionCarrera.getCarreras());
		model.addAttribute("titulo", "Nueva Materia");

		return "materia";
	}

	// Metodo para guardar una materia nueva usando el boton guardar
	@PostMapping("/guardar")
	public ModelAndView guardarMateria(@ModelAttribute("materia") Materia materia) {

		ModelAndView modelView = new ModelAndView("materias");
		// Atento a esto cuando hagas el formulario
		CollectionMateria.agregarMateria(materia);
		modelView.addObject("materias", CollectionMateria.getMaterias());
		modelView.addObject("docentes", CollectionDocente.getDocentes());
		modelView.addObject("carreras", CollectionCarrera.getCarreras());

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
