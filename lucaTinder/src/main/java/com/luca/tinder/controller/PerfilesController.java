package com.luca.tinder.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luca.tinder.model.Perfil;
import com.luca.tinder.service.PerfilService;

@Controller
@RequestMapping("/")
public class PerfilesController {
	@Autowired
	private PerfilService servicio;
	private static final Logger logger = LoggerFactory.getLogger(PerfilesController.class);

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
			//return "redirect:/new";
		} 
		
		servicio.createNewPerfil(perfil);
		servicio.createPerfilFalso();
		model.addAttribute("success",
				"Estimado " + perfil.getNombre_perfil() + " , su registro se ha completado de forma correcta");
		return "seleccionPerfiles";
		
	}

}
