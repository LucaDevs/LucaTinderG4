package com.luca.tinder.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.github.javafaker.Faker;
import com.luca.tinder.model.Perfil;

@Repository
public class PerfilDaoImpl implements PerfilDaoCustom {
	@PersistenceContext
	EntityManager entityManager;
	
	Logger logger= LoggerFactory.getLogger(PerfilDaoImpl.class);
	public boolean CrearFalsosPerfiles() {
		for(int i=0;i<10;i++) {
			Perfil p=new Perfil();
			Faker faker = new Faker();
			
			
			int azar=(int)(Math.random()*2);
			
			p.setNombre_perfil(faker.animal().name());
			p.setNick_perfil(faker.animal().name());
			p.setEdad_perfil((int)(Math.random()*90+18));
			p.setPoblacion_perfil(faker.address().cityName());
			if(azar==0) {
			p.setGenero_perfil('M');
			} else {
				p.setGenero_perfil('H');
			}
			p.setDescripcion_perfil(faker.gameOfThrones().quote());
			logger.info(p.toString());
		
		}
		return true;
	}
	
	public int numeroPerfiles() {
		
		int usuarios=0;
		
		Query query= entityManager.createNativeQuery("SELECT * from lucatinder.perfiles", Perfil.class );
		usuarios = query.getResultList().size();
		
		return usuarios;
	}
	
	
}
