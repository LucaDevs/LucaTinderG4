package com.luca.tinder.dao;

import java.util.ArrayList;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.luca.tinder.model.Perfil;

@Repository
public class PerfilDaoImpl implements PerfilDaoCustom {
	@PersistenceContext
	EntityManager entityManager;

	Logger logger = LoggerFactory.getLogger(PerfilDaoImpl.class);

	public boolean insertPerfilFalso() {
		Map<String, Perfil> cuentas = Perfil.CrearFalsosPerfiles();
		for (Map.Entry<String, Perfil> entries : cuentas.entrySet()) {
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

	public Perfil buscarPorNick(String nick) throws NoResultException {
		Perfil p = null;
		Query query = entityManager.createNativeQuery("SELECT * FROM lucatinder.perfiles where nick_perfil = ?",
				Perfil.class);
		query.setParameter(1, nick);
		Object o = query.getSingleResult();
		if (o instanceof Perfil)
			p = (Perfil) o;
		return p;
	}

	public ArrayList<Perfil> getPerfiles(Perfil p) {
		ArrayList<Perfil> perfiles = null;
		Query query = entityManager
				.createNativeQuery(
						"Select * from lucatinder.perfiles where nick_perfil != ? and cod_perfil not in "
								+ "(select distinct usu2 from lucatinder.listas where perfil = ?) limit 20",
						Perfil.class);
		query.setParameter(1, p.getNick_perfil());
		query.setParameter(2, p.getCod_perfil());
		perfiles = (ArrayList<Perfil>) query.getResultList();

		return perfiles;
	}

	@Override
	public void likeDislike(Perfil p, int cod_perfil, int tipo_lista) {
		Query query = entityManager
				.createNativeQuery("Insert into lucatinder.listas(perfil, usu2, tipo_lista) values (?,?,?)");
		query.setParameter(1, p.getCod_perfil());
		query.setParameter(2, cod_perfil);
		query.setParameter(3, tipo_lista);
		query.executeUpdate();
	}

	@Override
	public ArrayList<Perfil> getContactos(Perfil p) {
		ArrayList<Perfil> contactos = null;
		Query query = entityManager.createNativeQuery(
				"select distinct * from lucatinder.perfiles a, lucatinder.listas b where b.tipo_lista = 1 and b.usu2 = a.cod_perfil and b.perfil = ? and a.nick_perfil != ?",
				Perfil.class);
		query.setParameter(1, p.getCod_perfil());
		query.setParameter(2, p.getNick_perfil());
		contactos = (ArrayList<Perfil>) query.getResultList();
		return contactos;
	}

	@Override
	public ArrayList<Perfil> getDescartes(Perfil p) {
		ArrayList<Perfil> contactos = null;
		Query query = entityManager.createNativeQuery(
				"select distinct * from lucatinder.perfiles a, lucatinder.listas b where b.tipo_lista = 2 and b.usu2 = a.cod_perfil and b.perfil = ? and a.nick_perfil != ?",
				Perfil.class);
		query.setParameter(1, p.getCod_perfil());
		query.setParameter(2, p.getNick_perfil());
		contactos = (ArrayList<Perfil>) query.getResultList();
		return contactos;
	}

	@Override
	public ArrayList<Perfil> getMatch(Perfil p) {
		ArrayList<Perfil> contactos = null;

		return contactos;
	}
<<<<<<< HEAD
	@Override
	public ArrayList<Perfil> cargarMiperfil(Perfil p) {
		ArrayList<Perfil> miperfil = null;
=======

	public Perfil cargarMiperfil(Perfil p) {
		Perfil miperfil = null;
>>>>>>> refs/heads/tarea_EliminarUsuario_alejandro_leoVersion
		Query query = entityManager.createNativeQuery("select * from lucatinder.perfiles where nick_perfil = ?",
				Perfil.class);
		query.setParameter(1, p.getNick_perfil());
		miperfil = (Perfil) query.getSingleResult();
		return miperfil;

	}
<<<<<<< HEAD
	
	public boolean editarPerfil(Perfil p) {
		Query query = entityManager.createNativeQuery("Update lucatinder.perfiles set nombre_perfil = ?, genero_perfil=?"
				+ "edad_perfil=?, descripcion_perfil=?, poblacion_perfil=? where nick_perfil=?");
		query.setParameter(1, p.getNombre_perfil());
		query.setParameter(2, p.getGenero_perfil());
		query.setParameter(3, p.getEdad_perfil());
		query.setParameter(4, p.getDescripcion_perfil());
		query.setParameter(5, p.getPoblacion_perfil());
		query.setParameter(6, p.getNick_perfil());
		query.executeUpdate();
		
		return true;
=======

	@Override
	public void eliminarPerfil(Perfil p) {
		Query query = entityManager
				.createNativeQuery("delete from lucatinder.perfiles where cod_perfil = ?");
		query.setParameter(1, p.getCod_perfil());
		query.executeUpdate();
		
>>>>>>> refs/heads/tarea_EliminarUsuario_alejandro_leoVersion
	}
}
