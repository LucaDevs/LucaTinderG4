package com.luca.tinder.service;

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
	
}
