package com.luca.tinder.service;

import java.util.ArrayList;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luca.tinder.dao.PerfilDao;
import com.luca.tinder.model.Perfil;


@Service
@Transactional
public class PerfilServiceImpl implements PerfilService {
	
	@Autowired
	private PerfilDao perfDao;
	
	private static Logger logger;
	
	static {
		try {
			logger = LogManager.getLogger(PerfilServiceImpl.class);
		} catch (Throwable e) {
			System.out.println("Error en el logger");
		}
	}

	@Override
	public void createNewPerfil(Perfil perfil) {
		perfDao.save(perfil);
	}
	
	public void createPerfilFalso() {
		perfDao.insertPerfilFalso();
	}
	
	public long numeroPerfiles() {
		return perfDao.count();
	}
	
	public Perfil buscarPorNick(String nick) {
		Perfil p = null;
		try {
			p = perfDao.buscarPorNick(nick);
		} catch (NoResultException e) {
			logger.warn("No hay usuario con ese nick");
		}
		return p;
	}
	
	public ArrayList<Perfil> getPerfiles(Perfil p){
		ArrayList<Perfil> perfiles = null;
		perfiles = perfDao.getPerfiles(p);
		return perfiles;
	}

	@Override
	public void likeDislike(Perfil p, int cod_perfil, int tipo_lista) {
		perfDao.likeDislike(p, cod_perfil, tipo_lista);	
	}

	@Override
	public ArrayList<Perfil> getContactos(Perfil p) {
		ArrayList<Perfil> contactos = null;
		contactos = perfDao.getContactos(p);
		return contactos;
	}

	@Override
	public ArrayList<Perfil> getDescartes(Perfil p) {
		ArrayList<Perfil> contactos=null;
		contactos=perfDao.getDescartes(p);

		return contactos;
	}

	@Override
	public Perfil cargarMiperfil(String nick) {
		Perfil p = null;
		try {
			p = perfDao.buscarPorNick(nick);
		} catch (NoResultException e) {
			logger.warn("No hay usuario con ese nick");
		}
		return p;
		
	}


	
}
