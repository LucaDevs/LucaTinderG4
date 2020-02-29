package com.luca.tinder.dao;

import java.util.HashMap;

import com.luca.tinder.model.Perfil;

public interface PerfilDaoCustom {
	public HashMap<String, Perfil> CrearFalsosPerfiles();
	public boolean insertPerfilFalso();
	public Perfil buscarPorNick(String nick);
}
