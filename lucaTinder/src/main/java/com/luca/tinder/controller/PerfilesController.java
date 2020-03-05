package com.luca.tinder.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.luca.tinder.model.Perfil;
import com.luca.tinder.service.CategoriaService;
import com.luca.tinder.service.CategoriaServiceImpl;
import com.luca.tinder.service.PerfilService;

@Controller
@SessionAttributes({"perfil", "perfilOld"})
public class PerfilesController {
	// Numero a partir del cual se dejarán de añadir perfiles falsos
	private final long PERFILMIN = 20;

	@Autowired
	private PerfilService servicio;
	
	@Autowired
	private CategoriaService catserv;
	
	private static final Logger logger = LoggerFactory.getLogger(PerfilesController.class);

	@GetMapping("/")
	public String index(ModelMap model) {
		logger.info("-- en INDEX");
		model.addAttribute("perfil", new Perfil());
		return "index";
	}

	
	@PostMapping("/comprobateNick")
	public String comprobarNick(ModelMap model, @RequestParam("nick_perfil") String nick_perfil) {
		logger.info("-- en COMPROBACION");

		Perfil p = servicio.buscarPorNick(nick_perfil);

		if (p != null) {
			model.addAttribute("perfil", p);
			return "redirect:/selection";
		} else {
			model.addAttribute("perfil", new Perfil());
			return "index";
		}

	}
	
	@GetMapping("/selection")
	public String selection(ModelMap model, @ModelAttribute("perfil") Perfil perfil) {
		logger.info("------- Seleccion (entrando) " + perfil);
		model.addAttribute("perfiles", servicio.getPerfiles(perfil));
		return "seleccionPerfiles";
	}
	
	@GetMapping("/contactos")
	public String mostrarContactos(ModelMap model, @ModelAttribute("perfil") Perfil perfil) {
		logger.info("------- Mostrando contactos ");
		model.addAttribute("contactos", servicio.getContactos(perfil));
		return "contactos";
	}
	
	@GetMapping("/descartes")
	public String mostrarDescartes(ModelMap model, @ModelAttribute("perfil") Perfil perfil) {
		logger.info("------- Mostrando descartes ");
		model.addAttribute("descartes", servicio.getDescartes(perfil));
		return "descartes";
	}
	@GetMapping("/match")
	public String mostrarMatch(ModelMap model, @ModelAttribute("perfil") Perfil perfil) {
		logger.info("------- Mostrando match ");
		model.addAttribute("match", servicio.getMatch(perfil));
		return "match";
	}
	
	@GetMapping("/checkLike/{id}")
	public String checkLike(ModelMap model, @ModelAttribute("perfil") Perfil perfil, @PathVariable(name = "id") int cod_perfil) {
		Perfil p = (Perfil) model.getAttribute("perfil");
		logger.info(p.toString());
		logger.info("-- en CHECK" + perfil);
		servicio.likeDislike(p,cod_perfil,1);
		return "redirect:/selection";
	}
	
	@GetMapping("/checkDislike/{id}")
	public String checkDislike(ModelMap model, @ModelAttribute("perfil") Perfil perfil, @PathVariable(name = "id") int cod_perfil) {
		Perfil p = (Perfil) model.getAttribute("perfil");
		logger.info(p.toString());
		logger.info("-- en CHECK" + perfil);
		servicio.likeDislike(p,cod_perfil,2);
		return "redirect:/selection";
	}

	@GetMapping("/new")
	public String createNewPerfil(ModelMap model) {
		logger.info("-- en NEW");
		model.addAttribute("perfil", new Perfil());
		return "PerfilForm";

	}

	@PostMapping("/save")
	public String saveRegistration(@Valid Perfil perfil, BindingResult result, ModelMap model) {
		logger.info("------ controller > save");

		if (result.hasErrors()) {
			System.out.println("--- Hay algunos errores");
			return "PerfilForm";
		}

		servicio.createNewPerfil(perfil);
		if (servicio.numeroPerfiles() < PERFILMIN)
			servicio.createPerfilFalso();
		model.addAttribute("success",
				"Estimado " + perfil.getNombre_perfil() + " , su registro se ha completado de forma correcta");
		return "index";

	}
	
	@GetMapping("/miperfil")
	public String cargarMiperfil(ModelMap model, @ModelAttribute("perfil") Perfil perfil) {
		logger.info("------- Mostrando perfil ");
		model.addAttribute("miperfil", servicio.cargarMiperfil(perfil));
		model.addAttribute("perfilOld", perfil);
		return "miperfil";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminarPerfil(ModelMap model, @ModelAttribute("perfil") Perfil perfil) {
		Perfil p = (Perfil) model.getAttribute("perfil");
		logger.info(p.toString());
		logger.info("-- eliminando" + perfil);
		servicio.eliminarPerfil(p);
		model.addAttribute("perfil", new Perfil());
		return "redirect:/";
	}

	@PostMapping("/editarperfil")
	public String editarPerfil(ModelMap model, @ModelAttribute("perfil") Perfil perfil,
			BindingResult result, @ModelAttribute("perfilOld") Perfil perfilViejo) {
		logger.info("------- Editando Perfil ");
		model.addAttribute("perfil", new Perfil());
		servicio.editarPerfil(perfil, perfilViejo);
		model.addAttribute("perfil", servicio.buscarPorNick(perfilViejo.getNick_perfil()));
		return "redirect:/miperfil";
	}
	

	@GetMapping("/modifperfil")
	public String modificarPerfil(ModelMap model, @ModelAttribute("perfil") Perfil perfil) {
		logger.info("------- modificando perfil ");
		
		model.addAttribute("perfilOld", perfil);
		return "editarperfil";
	}
	
}
