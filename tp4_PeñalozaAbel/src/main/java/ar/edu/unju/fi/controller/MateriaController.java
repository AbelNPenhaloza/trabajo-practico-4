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

import ar.edu.unju.fi.dto.MateriaDTO;
import ar.edu.unju.fi.mapper.AlumnoMapper;
import ar.edu.unju.fi.mapper.CarreraMapper;
import ar.edu.unju.fi.mapper.DocenteMapper;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.model.Curso;
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.model.Modalidad;
import ar.edu.unju.fi.service.IAlumnoService;
import ar.edu.unju.fi.service.ICarreraService;
import ar.edu.unju.fi.service.IDocenteService;
import ar.edu.unju.fi.service.IMateriaService;
import jakarta.validation.Valid;


@Controller
@RequestMapping("/materia")
public class MateriaController {

	@Autowired
	private MateriaDTO materiaDTO;

	@Autowired
	private IMateriaService materiaService;
	@Autowired
	private IAlumnoService alumnoService;
	@Autowired
	private IDocenteService docenteService;
	@Autowired
	private ICarreraService carreraService;

	// @Autowired
	// private MateriaMapper materiaMapper;
	@Autowired
	private DocenteMapper docenteMapper;
	@Autowired
	private CarreraMapper carreraMapper;
	@Autowired
	private AlumnoMapper alumnoMapper;
	// @Autowired
	// private CarreraDTO carreraDTO;
	// @Autowired
	// private DocenteDTO docenteDTO;

	// Metodo para listar todas materias
	@GetMapping("/listado")
	public String getMateriasPage(Model model) {

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
	public ModelAndView guardarMateria(@Valid @ModelAttribute("materia") MateriaDTO materiaDTO, BindingResult result,
			Model model) {

		ModelAndView modelView = new ModelAndView("materias");
		String mensaje;

		if (result.hasErrors()) {

			model.addAttribute("edicion", false);
			model.addAttribute("titulo", "Nueva Materia");
			model.addAttribute("modalidades", Modalidad.values());
			model.addAttribute("cursos", Curso.values());
			model.addAttribute("docentes", docenteService.findAll());
			model.addAttribute("carreras", carreraService.findAll());
			modelView.setViewName("materia");
			return modelView;
		}

		Docente docente = docenteMapper.toDocente(docenteService.findById(materiaDTO.getDocente().getLegajo()));
		Carrera carrera = carreraMapper.toCarrera(carreraService.findById(materiaDTO.getCarrera().getCodigo()));

		materiaDTO.setDocente(docente);
		materiaDTO.setCarrera(carrera);

		Materia materia = materiaService.save(materiaDTO);

		if (materia != null) {
			mensaje = "Materia guardada con exito!";
			model.addAttribute("exito", true);
		} else {
			mensaje = "La Materia no se pudo guardar";
			model.addAttribute("exito", false);
		}

		// model.addAttribute("exito", exito);
		model.addAttribute("mensaje", mensaje);
		model.addAttribute("cursos", Curso.values());// tener en cuenta borrar
		model.addAttribute("modalidades", Modalidad.values());// tener en cuenta borrar
		modelView.addObject("materias", materiaService.findAll());

		return modelView;

	}



	// Metodo que presenta el formulario para modificar
	@GetMapping("/modificar/{idMateria}")
	public String getModificarMateriaPage(Model model, @PathVariable(value = "idMateria") Long idMateria) {

		MateriaDTO materiaEncontradaDTO = new MateriaDTO();
		// toma valor verdadero para editar
		boolean edicion = true;
		// hacer validadcion si o si

		materiaEncontradaDTO = materiaService.findById(idMateria);
		model.addAttribute("edicion", edicion);
		model.addAttribute("materia", materiaEncontradaDTO);
		model.addAttribute("cursos", Curso.values());
		model.addAttribute("modalidades", Modalidad.values());
		model.addAttribute("docentes", docenteService.findAll());
		model.addAttribute("carreras", carreraService.findAll());
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
			model.addAttribute("docentes", docenteService.findAll());
			model.addAttribute("carreras", carreraService.findAll());
			return "materia";
		}

		Docente docente = docenteMapper.toDocente(docenteService.findById(materiaDTO.getDocente().getLegajo()));
		Carrera carrera = carreraMapper.toCarrera(carreraService.findById(materiaDTO.getCarrera().getCodigo()));

		materiaDTO.setDocente(docente);
		materiaDTO.setCarrera(carrera);

		boolean exito = false;
		String mensaje = "";
		try {
			materiaService.editarMateria(materiaDTO);

			mensaje = "La Materia con codigo " + materiaDTO.getIdMateria() + " fue modificada con exito!";
			exito = true;
		} catch (Exception e) {
			mensaje = e.getMessage();
			e.printStackTrace();
		}

		model.addAttribute("materias", materiaService.findAll());
		model.addAttribute("exito", exito);
		model.addAttribute("mensaje", mensaje);
		model.addAttribute("docentes", docenteService.findAll());// tener en cuenta borrar
		model.addAttribute("carreras", carreraService.findAll());// tener en cuenta borrar
		model.addAttribute("titulo", "Materias");
		return "materias";

	}

	// Metodo para eliminar una materia
	// @GetMapping("/eliminar/{codigo}")
	// public String eliminarMateria(@PathVariable(value = "codigo")Integer codigo)
	// {
	// materiaService.deleteById(codigo);

	// return "redirect:/materia/listado";
	// }

	// Metodo para eliminar una materia
	@GetMapping("/eliminar/{idMateria}")
	public String eliminarMateria(Model model, @PathVariable(value = "idMateria") Long idMateria) {
		materiaService.deleteById(idMateria);
		model.addAttribute("materias", materiaService.findAll());
		model.addAttribute("titulo", "Materias");
		return "materias";
	}

	// Metodo para listar alumnos de una materia
	@GetMapping("/alumnos/{idMateria}")
	public String getAlumnosByMateria(Model model, @PathVariable Long idMateria) {
		MateriaDTO materia = materiaService.findById(idMateria);
		model.addAttribute("alumnos", materia.getAlumnos());
		model.addAttribute("materia", materia);
		return "materia-alumnos";
	}
}
