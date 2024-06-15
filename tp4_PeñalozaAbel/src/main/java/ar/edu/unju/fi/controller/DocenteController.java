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

import ar.edu.unju.fi.dto.DocenteDTO;
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.service.IDocenteService;

@Controller
@RequestMapping("/docente")
public class DocenteController {

	@Autowired
	private DocenteDTO docenteDTO;
	// private Docente docente;
	
	@Autowired
	private IDocenteService docenteService;

	@GetMapping("/listado")
	public String getDocentePage(Model model) {
		
		model.addAttribute("exito", false);
		model.addAttribute("mensaje", "");
		model.addAttribute("titulo", "Docentes");
		model.addAttribute("docentes", docenteService.findAll());
		return "docentes";
	}

	// Metodo para agregar un nuevo docente
	@GetMapping("/nuevo")
	public String getNuevoDocentePage(Model model) {

		boolean edicion = false;

		model.addAttribute("docente", docenteDTO);
		model.addAttribute("edicion", edicion);
		model.addAttribute("titulo", "Nueva Docente");

		return "docente";

	}

	// Metodo para guardar un docente nuevo usando el boton guardar
	@PostMapping("/guardar")
	public ModelAndView guardarDocente(@ModelAttribute("docente") Docente docente, Model model) {

		ModelAndView modelView = new ModelAndView("docentes");
		String mensaje;

		boolean exito = docenteService.save(docenteDTO);
		if (exito) {
			mensaje = "Docente guardado con exito";
		} else {
			mensaje = "El Docente no se pudo guardar";
		}
		model.addAttribute("exito", exito);
		model.addAttribute("mensaje", mensaje);
		modelView.addObject("docentes", docenteService.findAll());

		return modelView;
	}

	// Metodo que presenta el formulario para modificar
	@GetMapping("/modificar/{legajo}")
	public String getModificarDocentePage(Model model, @PathVariable(value = "legajo") Integer legajo) {

		Docente docenteEncontrado = new Docente();
		// toma valor verdadero para editar
		boolean edicion = true;
		// hacer validadcion si o si
		DocenteDTO docenteEncontradoDTO = docenteService.findById(legajo);
		
		model.addAttribute("edicion", edicion);
		model.addAttribute("docente", docenteEncontradoDTO);
		model.addAttribute("titulo", "Modificar Docente");

		return "docente";
	}

	// Metodo para modificar y guardar la modificacion en la Collection
	@PostMapping("/modificar")
	public String modificarDocente(@ModelAttribute("docente") Docente docente, Model model) {

		boolean exito = false;
		String mensaje = "";
		try {
			docenteService.edit(docenteDTO);
			mensaje = "El docente con codigo " + docente.getLegajo() + " fue modificado con exito!";
			exito = true;
		} catch (Exception e) {
			mensaje = e.getMessage();
			e.printStackTrace();
		}

		model.addAttribute("docentes", docenteService.findAll());
		model.addAttribute("exito", exito);
		model.addAttribute("mensaje", mensaje);
		model.addAttribute("titulo", "Docentes");
		return "docentes";
	}

	// Metodo para eliminar un docente
	@GetMapping("/eliminar/{legajo}")
	public String eliminarDocente(@PathVariable(value = "legajo") Integer legajo) {
		docenteService.deleteById(legajo);
		return "redirect:/docente/listado";
	}
}
