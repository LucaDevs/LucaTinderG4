package com.luca.tinder.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.luca.tinder.model.Perfil;
import com.luca.tinder.service.PerfilService;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("/perfilrest")
public class PerfilesControllerRest {
	
	private final PerfilService servicio;

	@Autowired
	public PerfilesControllerRest(PerfilService servicio) {
		this.servicio = servicio;
	}
	
	@SuppressWarnings("serial")
	@ResponseStatus(HttpStatus.NOT_FOUND)
	class PerfilNotFoundException extends RuntimeException {

		public PerfilNotFoundException() {
			super("Perfil does not exist");
		}
	}
	
	
	@PostMapping("/save")
	ResponseEntity<String> crearPerfil(@RequestBody Perfil perfil) {
		servicio.createNewPerfil(perfil);
		return ResponseEntity.ok().header("Header", "realizado con existo")
		        .body("la operacion  se realizo correctamente");
	}
	
	
	@GetMapping("/contactos")
	Collection<Perfil> mostrarContactos(@RequestBody Perfil perfil) {
		return this.servicio.getContactos(perfil);
	}
	

}
