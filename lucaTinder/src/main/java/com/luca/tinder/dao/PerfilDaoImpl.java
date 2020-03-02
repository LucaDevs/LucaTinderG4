package com.luca.tinder.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
	//Mover a paquetes utilidades
	public HashMap<String, Perfil> CrearFalsosPerfiles() {
		HashMap<String, Perfil> cuentas = new HashMap<String, Perfil>();
		for(int i=0;i<10;i++) {
			Perfil p=new Perfil();
			Faker faker = new Faker();
			
			
			int azar=(int)(Math.random()*2);
			
			p.setNombre_perfil(faker.animal().name());
			p.setNick_perfil(faker.animal().name());
			p.setEdad_perfil((int)((Math.random()*90)+18));
			p.setPoblacion_perfil(faker.address().cityName());
			if(azar==0) {
				p.setGenero_perfil('M');
			} else {
				p.setGenero_perfil('H');
			}
			String desc = faker.company().catchPhrase();
			if(desc.length() <= 100) {
				p.setDescripcion_perfil(desc);
			}else
				p.setDescripcion_perfil(desc.substring(0, 99));
			
			cuentas.put(p.getNick_perfil(), p);
			logger.info(p.toString());
		
		}
		
		return cuentas;
	}
	
	public boolean insertPerfilFalso() {
		Map<String, Perfil> cuentas = this.CrearFalsosPerfiles();
		for(Map.Entry<String, Perfil> entries : cuentas.entrySet()) {
//			Query query = entityManager.createNativeQuery("INSERT INTO lucatinder.perfiles(nick_perfil, nombre_perfil, genero_perfil"
//					+ "edad_perfil, descripcion_perfil) values (?,?,?,?,?)", Perfil.class);
//			Perfil p = entries.getValue();
//			query.setParameter(1, p.getNick_perfil());
//			query.setParameter(2, p.getNombre_perfil());
//			query.setParameter(3, p.getGenero_perfil());
//			query.setParameter(4, p.getEdad_perfil());
//			query.setParameter(5, p.getDescripcion_perfil());
//			query.executeUpdate();
			entityManager.persist(entries.getValue());
		}
		return true;
	}
	
	public Perfil buscarPorNick(String nick) throws NoResultException{
		Perfil p = null;
		Query query = entityManager.createNativeQuery("SELECT * FROM lucatinder.perfiles where nick_perfil = ?", Perfil.class);
		query.setParameter(1, nick);
		Object o = query.getSingleResult();
		if (o instanceof Perfil)
			p = (Perfil) o;
		return p;
	}
	
	public ArrayList<Perfil> getPerfiles(){
		ArrayList<Perfil> perfiles = null;
		//QUERY 
		return perfiles;
	}

	@Override
	public ArrayList<Perfil> getPerfiles(Perfil p) {
		ArrayList<Perfil> perfiles = null;
		Query query = entityManager.createNativeQuery("Select * from perfiles where nick_perfil != ? and cod_perfil not in (select distinct usu2 from listas where perfil = ?)", Perfil.class);
		query.setParameter(1, p.getNick_perfil());
		query.setParameter(2, p.getCod_perfil());
		perfiles = (ArrayList<Perfil>) query.getResultList();
		
		return perfiles;
	}
}
