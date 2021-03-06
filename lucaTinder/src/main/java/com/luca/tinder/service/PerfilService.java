package com.luca.tinder.service;

import java.util.ArrayList;

import com.luca.tinder.model.Perfil;
/**
 * 
 * @author Alejandro Martin
 * @since 1.0
 *
 */
public interface PerfilService {
	
	public void createNewPerfil(Perfil perfil);
	public void createPerfilFalso();
	public long numeroPerfiles();
	public Perfil buscarPorNick(String nick);
	public ArrayList<Perfil> getPerfiles(Perfil p);
	public void likeDislike(Perfil p, int cod_perfil, int tipo_lista);
	public ArrayList<Perfil> getContactos(Perfil p);
	public  ArrayList<Perfil> getDescartes(Perfil p);
	public  ArrayList<Perfil> getMatch(Perfil p);
	public boolean editarPerfil(Perfil p, Perfil perfilViejo);
	public Perfil cargarMiperfil(Perfil p);
	public void eliminarPerfil(Perfil p);
	
}
