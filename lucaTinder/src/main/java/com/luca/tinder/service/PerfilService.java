package com.luca.tinder.service;

import com.luca.tinder.model.Perfil;

public interface PerfilService {
	
	public void createNewPerfil(Perfil perfil);
	public void createPerfilFalso();
	public long numeroPerfiles();
}
