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

import ar.edu.unju.fi.dto.CarreraDTO;
import ar.edu.unju.fi.dto.DocenteDTO;
import ar.edu.unju.fi.dto.MateriaDTO;
import ar.edu.unju.fi.mapper.CarreraMapper;
import ar.edu.unju.fi.mapper.DocenteMapper;
import ar.edu.unju.fi.mapper.MateriaMapper;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.model.Curso;
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.model.Modalidad;
import ar.edu.unju.fi.service.ICarreraService;
import ar.edu.unju.fi.service.IDocenteService;
import ar.edu.unju.fi.service.IMateriaService;


@Controller
@RequestMapping("/materia")
public class MateriaController {

	@Autowired
	private MateriaDTO materiaDTO;

	@Autowired
	private IMateriaService materiaService;
	@Autowired
	private IDocenteService docenteService;
	@Autowired
	private ICarreraService carreraService;

	@Autowired
	private MateriaMapper materiaMapper;
	@Autowired
	private DocenteMapper docenteMapper;
	@Autowired
	private CarreraMapper carreraMapper;
	@Autowired
	private CarreraDTO carreraDTO;
	@Autowired
	private DocenteDTO docenteDTO;

	// Metodo para listar todas materias
	@GetMapping("/listado")
	public String getMateriasPage(Model model) {
		System.out.println("Hola ");
		model.addAttribute("titulo", "Materias");
		model.addAttribute("exito", false);
		model.addAttribute("mensaje", "");
		model.addAttribute("materias", materiaService.findAll());
		return "materias";
	}

	// Metodo para agregar una nueva materia
	@GetMapping("/nuevo")
	public String getNuevaMateriaPage(Model model) {

		boolean edicion = false;

		model.addAttribute("materia", materiaDTO);
		model.addAttribute("edicion", edicion);
		model.addAttribute("titulo", "Nueva Materia");
		model.addAttribute("modalidades", Modalidad.values());
		model.addAttribute("cursos", Curso.values());
		model.addAttribute("docentes", docenteService.findAll());
		model.addAttribute("carreras", carreraService.findAll());

		return "materia";
	}

	// Metodo para guardar una materia nueva usando el boton guardar
	@PostMapping("/guardar")
	public ModelAndView guardarMateria(@ModelAttribute("materia") MateriaDTO materiaDTO, Model model) {

		ModelAndView modelView = new ModelAndView("materias");
		String mensaje;

		Docente docente = docenteMapper.toDocente(docenteService.findById(materiaDTO.getDocente().getLegajo()));
		Carrera carrera = carreraMapper.toCarrera(carreraService.findById(materiaDTO.getCarrera().getCodigo()));

		materiaDTO.setDocente(docente);
		materiaDTO.setCarrera(carrera);

		boolean exito = materiaService.save(materiaDTO);

		if (exito) {
			mensaje = "Materia guardada con exito!";
		} else {
			mensaje = "La Materia no se pudo guardar";
		}

		model.addAttribute("exito", exito);
		model.addAttribute("mensaje", mensaje);
		model.addAttribute("cursos", Curso.values());
		model.addAttribute("modalidades", Modalidad.values());
		modelView.addObject("materias", materiaService.findAll());

		return modelView;

	}



	// Metodo que presenta el formulario para modificar
	@GetMapping("/modificar/{codigo}")
	public String getModificarMateriaPage(Model model, @PathVariable(value = "codigo") Integer codigo) {
		MateriaDTO materiaEncontradaDTO = new MateriaDTO();
		// toma valor verdadero para editar
		boolean edicion = true;
		// hacer validadcion si o si
		materiaEncontradaDTO = materiaService.findById(codigo);
		model.addAttribute("edicion", edicion);
		model.addAttribute("materia", materiaEncontradaDTO);
		model.addAttribute("cursos", Curso.values());
		model.addAttribute("modalidades", Modalidad.values());
		model.addAttribute("docentes", docenteService.findAll());
		model.addAttribute("carreras", carreraService.findAll());
		model.addAttribute("titulo", "Modificar Materia");

		return "materia";

	}

	// Metodo para modificar y guardar la modificacion en la Collection
	@PostMapping("/modificar")
	public String modificarMateria(@ModelAttribute("materia") MateriaDTO materiaDTO, Model model) {

		Docente docente = docenteMapper.toDocente(docenteService.findById(materiaDTO.getDocente().getLegajo()));
		Carrera carrera = carreraMapper.toCarrera(carreraService.findById(materiaDTO.getCarrera().getCodigo()));

		materiaDTO.setDocente(docente);
		materiaDTO.setCarrera(carrera);
		boolean exito = false;
		String mensaje = "";
		try {
			materiaService.edit(materiaDTO);

			mensaje = "La Materia con codigo " + materiaDTO.getCodigo() + " fue modificada con exito!";
			exito = true;
		} catch (Exception e) {
			mensaje = e.getMessage();
			e.printStackTrace();
		}

		model.addAttribute("materias", materiaService.findAll());
		model.addAttribute("exito", exito);
		model.addAttribute("mensaje", mensaje);
		model.addAttribute("docentes", docenteService.findAll());
		model.addAttribute("carreras", carreraService.findAll());
		model.addAttribute("titulo", "Materias");
		return "materias";

	}

	// Metodo para eliminar una materia
	@GetMapping("/eliminar/{codigo}")
	public String eliminarMateria(@PathVariable(value = "codigo") Integer codigo) {
		materiaService.deleteById(codigo);
		return "redirect:/materia/listado";
	}
}
