package com.luca.tinder.service;

import java.util.ArrayList;

import com.luca.tinder.model.Perfil;

public interface PerfilService {
	
	public void createNewPerfil(Perfil perfil);
	public void createPerfilFalso();
	public long numeroPerfiles();
	public Perfil buscarPorNick(String nick);
	public ArrayList<Perfil> getPerfiles(Perfil p);
}
