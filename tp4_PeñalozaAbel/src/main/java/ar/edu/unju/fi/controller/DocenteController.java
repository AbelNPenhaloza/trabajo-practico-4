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

import ar.edu.unju.fi.dto.DocenteDTO;
import ar.edu.unju.fi.service.IDocenteService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/docente")
public class DocenteController {
	
	@Autowired
	private IDocenteService docenteService;
	
	// Listado para visualizar en la tabla 
	@GetMapping("/listado")
	public String getDocentePage(Model model) {
		
		model.addAttribute("titulo", "Docentes");
		model.addAttribute("exito", false);
		model.addAttribute("mensaje", "");
		model.addAttribute("docentes", docenteService.findAllActive());
		return "docentes";
	}

	// Metodo para agregar un nuevo docente
	@GetMapping("/nuevo")
	public String getNuevoDocentePage(Model model) {
		
		boolean edicion = false;

		model.addAttribute("docente", new DocenteDTO());
		model.addAttribute("edicion", edicion);
		model.addAttribute("titulo", "Nueva Docente");

		return "docente";
	}

	// Metodo para guardar un docente nuevo usando el boton guardar
	@PostMapping("/guardar")
	public ModelAndView guardarDocente(@Valid @ModelAttribute("docente") DocenteDTO docenteDTO, BindingResult result,
			Model model) {
		
		ModelAndView modelView = new ModelAndView("docentes");
		String mensaje;
		
		if (result.hasErrors()) {
			model.addAttribute("edicion", false);
			model.addAttribute("titulo", "Nuevo Docente");
			modelView.setViewName("docente");
			return modelView;
		}
		
		try {
			docenteService.save(docenteDTO);
			mensaje= "Docente guardado con éxito!";
			model.addAttribute("exito", true);
		} catch (Exception e) {
			mensaje= "Docente no se pudo guardar: " + e.getMessage();
			model.addAttribute("exito", false);
		}
		
		model.addAttribute("mensaje", mensaje);
		modelView.addObject("docentes", docenteService.findAllActive());

		return modelView;
	}

	// Metodo que presenta el formulario para modificar
	@GetMapping("/modificar/{idDocente}")
	public String getModificarDocentePage(Model model, @PathVariable(value = "idDocente") Long idDocente) {
		
		DocenteDTO docenteDTO = docenteService.findById(idDocente);
		// toma valor verdadero para editar
		boolean edicion = true;
		
		model.addAttribute("edicion", edicion);
		model.addAttribute("docente", docenteDTO);
		model.addAttribute("titulo", "Modificar Docente");

		return "docente";
	}

	// Metodo para modificar y guardar la modificacion en la Collection
	@PostMapping("/modificar")
	public String modificarDocente(@Valid @ModelAttribute("docente") DocenteDTO docenteDTO, BindingResult result,
			Model model) {

		if (result.hasErrors()) {
			model.addAttribute("edicion", true);
			model.addAttribute("titulo", "Modificar Docente");
			return "materia";
		}
		
		try {
			docenteService.editarDocente(docenteDTO);
			model.addAttribute("exito", true);
			model.addAttribute("mensaje", "Docente fue modificado con éxito!");
		} catch (Exception e) {
			model.addAttribute("exito", false);
			model.addAttribute("mensaje", "Error al modificar el Docente: " + e.getMessage());
		}

		model.addAttribute("docentes", docenteService.findAllActive());
		model.addAttribute("titulo", "Docentes");
		return "docentes";
	}

	@GetMapping("/eliminar/{idDocente}")
	public String eliminarDocente(Model model, @PathVariable(value = "idDocente") Long idDocente) {
		docenteService.deleteById(idDocente);
		model.addAttribute("docentes", docenteService.findAllActive());
		model.addAttribute("titulo", "Docentes");
		return "docentes";
	}
}
