package com.luca.tinder.dao;

import java.util.ArrayList;

import javax.persistence.NoResultException;

import com.luca.tinder.model.Perfil;

public interface PerfilDaoCustom {
	public boolean insertPerfilFalso();
	public Perfil buscarPorNick(String nick) throws NoResultException;
	public ArrayList<Perfil> getPerfiles(Perfil p);
	public ArrayList<Perfil> getContactos(Perfil p);
} 
