package com.luca.tinder.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.luca.tinder.model.Perfil;
import com.luca.tinder.service.PerfilService;
import com.luca.tinder.service.PerfilServiceImpl;

@Controller
public class PerfilesController {
	@Autowired
	private PerfilService servicio;
	private static final Logger logger=LoggerFactory.getLogger(PerfilesController.class);
	
	@GetMapping("/new")
	public String createNewPerfil(ModelMap model) {
		logger.info("-- en NEW");
		model.addAttribute("perfil", new Perfil());
		return "PerfilForm";
		
	}
	
	
	
	@PostMapping("/save")
	public ModelAndView savePerfil(@ModelAttribute Perfil perfil) {
		logger.info("-- en SAVE");
		
		servicio.createNewPerfil(perfil);
		return new ModelAndView("redirect:/");
		
		
	}
}
