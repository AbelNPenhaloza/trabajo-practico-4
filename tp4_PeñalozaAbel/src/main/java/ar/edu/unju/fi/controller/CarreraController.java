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
import ar.edu.unju.fi.dto.CarreraDTO;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.service.ICarreraService;

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

		return "carrera";

	}

	// Metodo para guardar una carrera nueva usando el boton guardar
	@PostMapping("/guardar")
	public ModelAndView guardarCarrera(@ModelAttribute("carrera") CarreraDTO carreraDTO, Model model) {

		ModelAndView modelView = new ModelAndView("carreras");
		String mensaje;

		boolean exito = carreraService.save(carreraDTO);
		if (exito) {
			mensaje = "Carrera guardada con exito";
		} else {
			mensaje = "Carrera no se pudo guardar";
		}
		model.addAttribute("exito", exito);
		model.addAttribute("mensaje", mensaje);
		modelView.addObject("carreras", carreraService.findAll());

		return modelView;
	}

	// Metodo que presenta el formulario para modificar
	@GetMapping("/modificar/{codigo}")
	public String getModificarCarreraPage(Model model, @PathVariable(value = "codigo") Integer codigo) {
		Carrera carreraEncontrada = new Carrera();
		// toma valor verdadero para editar
		boolean edicion = true;
		// hacer validadcion si o si
		CarreraDTO carreraEncontradaDTO = carreraService.findById(codigo);
		model.addAttribute("edicion", edicion);
		model.addAttribute("carrera", carreraEncontradaDTO);
		model.addAttribute("titulo", "Modificar Carrera");

		return "carrera";

	}

	// Metodo para modificar y guardar la modificacion en la Collection
	@PostMapping("/modificar")
	public String modificarCarrera(@ModelAttribute("carrera") CarreraDTO carreraDTO, Model model) {

		boolean exito = false;
		String mensaje = "";
		try {
			carreraService.edit(carreraDTO);
			mensaje = "La carrera con codigo " + carreraDTO.getCodigo() + " fue modificada con exito!";
			exito = true;
		} catch (Exception e) {
			mensaje = e.getMessage();
			e.printStackTrace();
		}

		model.addAttribute("carreras", carreraService.findAll());
		model.addAttribute("exito", exito);
		model.addAttribute("mensaje", mensaje);
		model.addAttribute("titulo", "Carreras");
		return "carreras";

	}

	// Metodo para eliminar una carrera
	@GetMapping("/eliminar/{codigo}")
	public String eliminarCarrera(@PathVariable(value = "codigo") Integer codigo) {
		carreraService.deleteById(codigo);
		return "redirect:/carrera/listado";
	}
}
