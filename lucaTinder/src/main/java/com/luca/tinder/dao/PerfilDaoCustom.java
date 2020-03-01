package com.luca.tinder.dao;

import java.util.ArrayList;
import java.util.HashMap;

import javax.persistence.NoResultException;

import com.luca.tinder.model.Perfil;

public interface PerfilDaoCustom {
	public HashMap<String, Perfil> CrearFalsosPerfiles();
	public boolean insertPerfilFalso();
	public Perfil buscarPorNick(String nick) throws NoResultException;
	public ArrayList<Perfil> getPerfiles();
}
