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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.luca.tinder.model.Perfil;
import com.luca.tinder.service.PerfilService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
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
	
	@PostMapping("/comprobateNick")
	Perfil comprobarNick(@RequestBody String perfil) {
		System.out.println("----------------------------Controllr comprobateNick "+perfil);
		return this.servicio.buscarPorNick(perfil);
	}
	
	@GetMapping("/comprobateNick")
	Perfil comprobarNickget(@RequestParam String perfil) {
		System.out.println("----------------------------Controllr comprobateNick "+perfil);
		return this.servicio.buscarPorNick(perfil);
	}	
	
	
	@PostMapping("/save")
	ResponseEntity<String> crearPerfil(@RequestBody Perfil perfil) {
		servicio.createNewPerfil(perfil);
		return ResponseEntity.ok().header("Header", "realizado con existo")
		        .body("la operacion  se realizo correctamente");
	}
	
	@GetMapping("/selection")
	Collection<Perfil> seleccionarPerfiles(@RequestBody Perfil perfil) {
		return this.servicio.getPerfiles(perfil);
	}
	
	@GetMapping("/contactos")
	Collection<Perfil> mostrarContactos(@RequestBody Perfil perfil) {
		return this.servicio.getContactos(perfil);
	}
	
	@GetMapping("/descartes")
	Collection<Perfil> mostrarDescartes(@RequestBody Perfil perfil) {
		return this.servicio.getDescartes(perfil);
	}
	
	@GetMapping("/miperfil")
	Perfil miPerfil(@RequestBody Perfil perfil) {
		return this.servicio.cargarMiperfil(perfil);
	}
	
	
	
	

}
