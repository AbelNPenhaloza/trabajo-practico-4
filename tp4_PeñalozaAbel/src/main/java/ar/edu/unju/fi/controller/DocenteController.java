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
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.service.IDocenteService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/docente")
public class DocenteController {
	
	@Autowired
	private DocenteDTO docenteDTO;
	
	@Autowired
	private IDocenteService docenteService;
	
	// Listado para visualizar en la tabla 
	@GetMapping("/listado")
	public String getDocentePage(Model model) {
		
		model.addAttribute("titulo", "Docentes");
		model.addAttribute("exito", false);
		model.addAttribute("mensaje", "");
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

		Docente docente = docenteService.save(docenteDTO);
		
		if (docente != null) {
			mensaje = "Docente guardado con exito";
			model.addAttribute("exito", true);
		} else {
			mensaje = "El Docente no se pudo guardar";
			model.addAttribute("exito", false);
		}
		
		// model.addAttribute("exito", exito);
		model.addAttribute("mensaje", mensaje);
		modelView.addObject("docentes", docenteService.findAll());

		return modelView;
		
	}

	// Metodo que presenta el formulario para modificar
	@GetMapping("/modificar/{idDocente}")
	public String getModificarDocentePage(Model model, @PathVariable(value = "idDocente") Long idDocente) {
		
		DocenteDTO docenteEncontradoDTO = new DocenteDTO();
		// toma valor verdadero para editar
		boolean edicion = true;
		// hacer validadcion si o si
		
		docenteEncontradoDTO = docenteService.findById(idDocente);
		model.addAttribute("edicion", edicion);
		model.addAttribute("docente", docenteEncontradoDTO);
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
		
		boolean exito = false;
		String mensaje = "";
		try {
			docenteService.editarDocente(docenteDTO);
			mensaje = "El docente con codigo " + docenteDTO.getIdDocente() + " fue modificado con exito!";
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
	// @GetMapping("/eliminar/{idDocente}")
	// public String eliminarDocente(@PathVariable(value = "idDocente") Long idDocente) {
	//	docenteService.deleteById(idDocente);
	//	return "redirect:/docente/listado";
	// }
	
	@GetMapping("/eliminar/{idDocente}")
	public String eliminarDocente(Model model, @PathVariable(value = "idDocente") Long idDocente) {
		docenteService.deleteById(idDocente);
		model.addAttribute("docentes", docenteService.findAll());
		model.addAttribute("titulo", "Docentes");
		return "docentes";
	}
}
