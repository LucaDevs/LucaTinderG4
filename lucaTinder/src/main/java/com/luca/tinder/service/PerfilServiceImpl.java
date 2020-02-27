package com.luca.tinder.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PerfilServiceImpl implements PerfilService {
	
	private static Logger logger;
	
	static {
		try {
			logger = LogManager.getLogger(PerfilServiceImpl.class);
		} catch (Throwable e) {
			System.out.println("Error en el logger");
		}
	}
	
	public void createNewPerfil() {
		
		
		
	}

}
