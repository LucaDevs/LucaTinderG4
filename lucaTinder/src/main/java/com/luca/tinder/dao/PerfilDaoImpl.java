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
/**
 * 
 * @author David Heras
 * @since 26-02-2020
 */
@Repository
public class PerfilDaoImpl implements PerfilDaoCustom {
	@PersistenceContext
	EntityManager entityManager;
	
	Logger logger= LoggerFactory.getLogger(PerfilDaoImpl.class);
	/**
	 * Da de alta una serie de perfiles con datos falsos en la base de datos.
	 * @return <ul><li><b>true</b>: los perfiles se han insertado correctamente.</li></ul>
	 */
	@Override
	public boolean insertPerfilFalso() {
		Map<String, Perfil> cuentas = Perfil.CrearFalsosPerfiles();
		for (Map.Entry<String, Perfil> entries : cuentas.entrySet()) {
			entityManager.persist(entries.getValue());
		}
		return true;
	}
	/**
	 * Dado un nick de perfil único devuelve un objeto de tipo Perfil cuyos datos corresponden con los obtenidos de la base de datos.
	 * @return un perfil que se encuentre en la base de datos.
	 * @throws NoResultException si no hay ningún con ese nick en la base de datos.
	 */
	@Override
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
	/**
	 * Obtiene un lista de perfiles cualesquiera, omitiendo al perfil que se le pasa por parámetro.
	 * @param p el perfil que se omitirá a la hora de obtener la lista de perfiles.
	 * @return una lista de perfiles cualesquiera de la base de datos.
	 */
	@Override
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
	/**
	 * Realiza la acción de dar like (o dislike) hacia un usuario de la base de datos.<br>
	 * Cuando un perfil da <em>like</em> (o <em>dislike</em>) a otro usuario, este último no aparecerá <b>en</b> futuras pantallas de selección del primero.
	 * @param p perfil que realiza la acción de dar like o dislike.
	 * @param cod_perfil el codigo de perfil en la base de datos del perfil que recibe la acción.
	 * @param tipo_lista el tipo de acción que se realizará: <ul><li><b>1</b>: acción de dar a <b>like</b>.</li>
	 * <li><b>2</b>: acción de dar a <b>dislike</b>.</li></ul>
	 */
	@Override
	public void likeDislike(Perfil p, int cod_perfil, int tipo_lista) {
		Query query = entityManager
				.createNativeQuery("Insert into lucatinder.listas(perfil, usu2, tipo_lista) values (?,?,?)");
		query.setParameter(1, p.getCod_perfil());
		query.setParameter(2, cod_perfil);
		query.setParameter(3, tipo_lista);
		query.executeUpdate();
	}	
	/**
	 * Devuelve la lista de contactos del que lo solicite. Entendemos por <em>contacto</em> a cualquier otro perfil al que se haya dado <b>like</b>
	 * pero no haya dado <b>like</b> de vuelta.
	 * @param p perfil que realiza la solicitud.
	 * @return lista de perfiles al que dicho perfil haya dado like y no haya recibido like de vuelta.
	 */
	@Override
	public ArrayList<Perfil> getContactos(Perfil p) {
		ArrayList<Perfil> contactos = null;
		Query query = entityManager.createNativeQuery(
				"select distinct * from lucatinder.perfiles a, lucatinder.listas b where b.tipo_lista = 1 and b.usu2 = a.cod_perfil and b.perfil = ? and a.nick_perfil != ? and a.cod_perfil not in (SELECT p.cod_perfil FROM lucatinder.perfiles p where nick_perfil != ? and cod_perfil in (select usu2 from lucatinder.listas where perfil = ? and usu2 in(select perfil from lucatinder.listas where usu2 = ?) and tipo_lista = 1))", Perfil.class);
		query.setParameter(1, p.getCod_perfil());
		query.setParameter(2, p.getNick_perfil());
		query.setParameter(3, p.getNick_perfil());
		query.setParameter(4, p.getCod_perfil());
		query.setParameter(5, p.getCod_perfil());
		contactos = (ArrayList<Perfil>) query.getResultList();
		return contactos;
	}
	/**
	 * Devuelve la lista de perfiles descartados por el perfil que lo solicita. Entendemos por <em>descarte</em> a cualquier otro perfil al que se haya dado <b>dislike</b>.
	 * @param p perfil que realiza la solicitud.
	 * @return lista de perfiles al que dicho haya dislike.
	 */
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
		Query query= entityManager.createNativeQuery("SELECT p.* FROM lucatinder.perfiles p where nick_perfil != ? and cod_perfil in " + 
				"(select usu2 from lucatinder.listas where perfil = ? and usu2 in" + 
				"(select perfil from lucatinder.listas where usu2 = ?) and tipo_lista = 1)",Perfil.class  );
		query.setParameter(1, p.getNick_perfil());
		query.setParameter(2, p.getCod_perfil());
		query.setParameter(3, p.getCod_perfil());
		contactos = (ArrayList<Perfil>) query.getResultList();
		return contactos;
	}

	public Perfil cargarMiperfil(Perfil p) {
		Perfil miperfil = null;
		Query query = entityManager.createNativeQuery("select * from lucatinder.perfiles where nick_perfil = ?",
				Perfil.class);
		query.setParameter(1, p.getNick_perfil());
		miperfil = (Perfil) query.getSingleResult();
		return miperfil;

	}
	
	public boolean editarPerfil(Perfil p) {
		Query query = entityManager.createNativeQuery("Update lucatinder.perfiles set nombre_perfil = ?, genero_perfil=?,"
				+ "edad_perfil=?, descripcion_perfil=?, poblacion_perfil=? where nick_perfil=?");
		query.setParameter(1, p.getNombre_perfil());
		query.setParameter(2, p.getGenero_perfil());
		query.setParameter(3, p.getEdad_perfil());
		query.setParameter(4, p.getDescripcion_perfil());
		query.setParameter(5, p.getPoblacion_perfil());
		query.setParameter(6, p.getNick_perfil());
		query.executeUpdate();
		
		return true;
	}
	
	@Override
	public void eliminarPerfil(Perfil p) {
		Query query = entityManager
				.createNativeQuery("delete from lucatinder.perfiles where cod_perfil = ?");
		query.setParameter(1, p.getCod_perfil());
		query.executeUpdate();
	}
}
