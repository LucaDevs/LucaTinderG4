package com.luca.tinder.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.github.javafaker.Faker;
import com.luca.tinder.model.Perfil;

@Repository
public class PerfilDaoImpl implements PerfilDaoCustom {
	@PersistenceContext
	EntityManager entityManager;
	
	
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
		
		}
		return true;
	}
	
}
