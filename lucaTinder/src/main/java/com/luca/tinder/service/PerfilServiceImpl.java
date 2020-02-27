package com.luca.tinder.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.luca.tinder.dao.PerfilDao;
import com.luca.tinder.model.Perfil;

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
}
