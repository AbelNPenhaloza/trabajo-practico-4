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

import ar.edu.unju.fi.dto.AlumnoDTO;
import ar.edu.unju.fi.service.IAlumnoService;
import ar.edu.unju.fi.service.ICarreraService;
import ar.edu.unju.fi.service.IMateriaService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/alumno")
public class AlumnoController {

	@Autowired
	private IAlumnoService alumnoService;
	
	private ICarreraService carreraService;	
	
    public AlumnoController(IAlumnoService alumnoService, ICarreraService carreraService, IMateriaService materiaService){
    	this.alumnoService = alumnoService;
    	this.carreraService = carreraService;
    } 
    	
	
	@GetMapping("/listado")
	public String getAlumnosPage(Model model) {

		model.addAttribute("titulo", "Alumnos");
		model.addAttribute("exito", false);
		model.addAttribute("exitoeliminar", false);
		model.addAttribute("erroreliminar", false);
		model.addAttribute("mensaje", "");
		model.addAttribute("alumnos", alumnoService.findAllactive());
		return "alumnos";
	}

	// Metodo para agregar una nuevo Alumno
	@GetMapping("/nuevo")
	public String getNuevoAlumnoPage(Model model) {

		boolean edicion = false;

		model.addAttribute("alumno", new AlumnoDTO());
		model.addAttribute("edicion", edicion);
		model.addAttribute("titulo", "Nuevo Alumno");
		model.addAttribute("carreras", carreraService.findAllActive());
		
		return "alumno";
	}

	// Metodo para guardar un alumno nuevo usando el boton guardar
	@PostMapping("/guardar")
	public ModelAndView guardarAlumno(@Valid @ModelAttribute("alumno") AlumnoDTO alumnoDTO, BindingResult result, Model model) {
		
		ModelAndView modelView = new ModelAndView("alumnos");
		String mensaje;
		
		 if (alumnoService.existeAlumnoLu(alumnoDTO.getLu())) {
			
			 model.addAttribute("edicion", false);
			 model.addAttribute("titulo", "Nuevo Alumno");
			 model.addAttribute("carreras", carreraService.findAllActive());
			 modelView.setViewName("alumno");
			 model.addAttribute("repetido",true);
			 model.addAttribute("mensajelu", "La libreta universitada ya está registrada para otro alumno");
			 return modelView;	
		   }
		
		if(result.hasErrors()) {
			model.addAttribute("edicion", false);
			model.addAttribute("titulo", "Nuevo Alumno");
			model.addAttribute("carreras", carreraService.findAllActive());
			modelView.setViewName("alumno");
			return modelView;		
		}
				
		try {
			alumnoService.save(alumnoDTO);
			mensaje = "Alumno guardado con exito!";
			model.addAttribute("exito", true);
		
		} catch (Exception e) {
			mensaje = "El Alumno no se pudo guardar" + e.getMessage();
			
			model.addAttribute("exito", false);	
		}
		
		
		model.addAttribute("mensaje", mensaje);
		modelView.addObject("alumnos", alumnoService.findAllactive());
		
		return modelView;
	}

	// Metodo que presenta el formulario para modificar
	@GetMapping("/modificar/{idAlumno}")
	public String getModificarAlumnoPage(Model model, @PathVariable(value = "idAlumno") Long idAlumno) {
        
		
		AlumnoDTO alumnoDTO = alumnoService.findById(idAlumno);

		// toma valor verdadero para editar
		boolean edicion = true;

		model.addAttribute("edicion", edicion);
		model.addAttribute("alumno", alumnoDTO);
		model.addAttribute("carreras", carreraService.findAllActive());
		model.addAttribute("titulo", "Modificar Alumno");

		return "alumno";
	}

	// Metodo para modificar y guardar la modificacion en la Collection
	@PostMapping("/modificar")
	public String modificarAlumno(@Valid @ModelAttribute("alumno") AlumnoDTO alumnoDTO, BindingResult result, Model model) {

		
		 if (alumnoService.existeAlumnoLu(alumnoDTO.getLu())) {
				
			 model.addAttribute("edicion", true);
			 model.addAttribute("titulo", "Modificar Alumno");
			 model.addAttribute("carreras", carreraService.findAllActive());
			 model.addAttribute("repetido",true);
			 model.addAttribute("mensajelu", "La libreta universitada ya está registrada para otro alumno");
			 return "alumno";	
		   }
		 
		if(result.hasErrors()) {
			model.addAttribute("edicion",true);
			model.addAttribute("titulo","Modificar Alumno");
			model.addAttribute("carreras", carreraService.findAllActive());
			return "alumno";
		}
				
		try {
			alumnoService.editarAlumno(alumnoDTO);
			model.addAttribute("exito",true);
			model.addAttribute("mensaje", "El alumno fue modificado con exito!");
		} catch (Exception e) {
			model.addAttribute("exito",false);
			model.addAttribute("mensaje", "Error al modificar el Alumno"+ e.getMessage());
		}

		model.addAttribute("alumnos", alumnoService.findAllactive());
		model.addAttribute("carreras", carreraService.findAllActive());
		model.addAttribute("titulo", "Alumnos");
		
		return "alumnos";
	}

	// Metodo para eliminar una alumno
	@GetMapping("/eliminar/{idAlumno}")
	public String eliminarAlumno(Model model, @PathVariable(value = "idAlumno") Long idAlumno) {
		String mensaje;
	
		try {
			alumnoService.deleteById(idAlumno);
			mensaje="El Alumno con la id: "+ idAlumno +" ha sido eliminado";	
			model.addAttribute("mensaje", mensaje);
			model.addAttribute("exitoeliminar",true);
		} catch (Exception e) {
			 mensaje = e.getMessage(); 
		     model.addAttribute("mensaje", mensaje);
		     model.addAttribute("erroreliminar", true); 
		}
				
		model.addAttribute("alumnos", alumnoService.findAllactive());
		model.addAttribute("titulo", "Alumnos");
		
		return "alumnos";
	}
}
