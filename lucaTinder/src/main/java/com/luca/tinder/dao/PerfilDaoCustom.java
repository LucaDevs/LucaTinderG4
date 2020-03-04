package com.luca.tinder.dao;

import java.util.ArrayList;

import javax.persistence.NoResultException;

import com.luca.tinder.model.Perfil;

public interface PerfilDaoCustom {
	public boolean insertPerfilFalso();
	public Perfil buscarPorNick(String nick) throws NoResultException;
	public ArrayList<Perfil> getPerfiles(Perfil p);
	void likeDislike(Perfil p, int cod_perfil, int tipo_lista);
	public ArrayList<Perfil> getContactos(Perfil p);
	public ArrayList<Perfil> getDescartes(Perfil p);
	public Perfil cargarMiperfil(String nick);
} 