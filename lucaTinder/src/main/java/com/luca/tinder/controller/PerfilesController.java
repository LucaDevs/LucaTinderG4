package com.luca.tinder.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.luca.tinder.model.Perfil;

@Controller
public class PerfilesController {

	private static final Logger logger=LoggerFactory.getLogger(PerfilesController.class);
	@GetMapping("/new")
	public String createNewPerfil(ModelMap model) {
		logger.info("-- en NEW");
		model.addAttribute("perfil", new Perfil());
		return "PerfilForm";
		
	}
}
