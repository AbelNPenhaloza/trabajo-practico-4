package ar.edu.unju.fi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.edu.unju.fi.dto.DocenteDTO;
import ar.edu.unju.fi.service.IDocenteService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/docente")
public class DocenteController {
	
	private final IDocenteService docenteService;
	
	public DocenteController(IDocenteService docenteService) {
		this.docenteService = docenteService;
	}
	
	// Listado para visualizar en la tabla 
	@GetMapping("/listado")
	public String getDocentesPage(Model model) {
	
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
	public String guardarDocente(@Valid @ModelAttribute("docente") DocenteDTO docenteDTO, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()) {
			model.addAttribute("edicion", false);
			model.addAttribute("titulo", "Nuevo Docente");
			return "docente";
		}
		
		String mensaje;
		
		try {
			docenteService.save(docenteDTO);
			mensaje= "Docente guardado con éxito!";
			redirectAttributes.addFlashAttribute("exito", true);
			redirectAttributes.addFlashAttribute("mensaje", mensaje);
			return "redirect:/docente/listado";
		} catch (Exception e) {
			model.addAttribute("error", true);
			mensaje= "Docente no se pudo guardar: " + e.getMessage();
			model.addAttribute("exito", false);
			model.addAttribute("mensaje", mensaje);
			model.addAttribute("docente", docenteDTO);
			return "docente";
		}
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
			return "docente";
		}
		
		String mensaje;
		
		try {
			docenteService.editarDocente(docenteDTO);
			mensaje= "Docente fue modificado con éxito!";
			model.addAttribute("exito", true);
		} catch (Exception e) {
			mensaje= "Error al modificar el Docente: " + e.getMessage();
			model.addAttribute("exito", false);
		}

		model.addAttribute("mensaje", mensaje);
		model.addAttribute("docentes", docenteService.findAllActive());
		model.addAttribute("titulo", "Docentes");
		return "docentes";
	}

	@GetMapping("/eliminar/{idDocente}")
	public String eliminarDocente(Model model, @PathVariable(value = "idDocente") Long idDocente, RedirectAttributes redirectAttributes) {
		try {
			docenteService.deleteById(idDocente);
			redirectAttributes.addFlashAttribute("exito", true);
			redirectAttributes.addFlashAttribute("mensaje", "Docente eliminado correctamente.");
		} catch (EntityNotFoundException e) {
			redirectAttributes.addFlashAttribute("exito", false);
			redirectAttributes.addFlashAttribute("mensaje", e.getMessage());
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("exito", false);
			redirectAttributes.addFlashAttribute("mensaje",
					"Error al eliminar al docente. Por favor, inténtelo de nuevo.");
		}
		return "redirect:/docente/listado";
	}
}
