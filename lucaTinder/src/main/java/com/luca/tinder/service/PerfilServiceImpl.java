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


/**
 * 
 * @author Alejandro Martin
 *@since 1.0
 */

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
	/**
	 * Crea un nuevo registro en la base de datos.
	 * @param perfil perfil que se va a dar de alta.
	 */
	@Override
	public void createNewPerfil(Perfil perfil) {
		perfDao.save(perfil);
	}
	
	/**
	 * Crea varios en registros en la base de datos con datos falsos.
	 * @see Perfil
	 */
	@Override
	public void createPerfilFalso() {
		perfDao.insertPerfilFalso();
	}
	
	/**
	 * Permite saber cuántos usuarios hay en la base de datos.
	 * @return numero de registros en la base de datos.
	 */
	@Override
	public long numeroPerfiles() {
		return perfDao.count();
	}
	
	/**
	 * Dado un nick, obtiene el objeto Perfil en la base de datos asociado a ese nick.
	 * @param nick nick de usuario único con el que se identifica a un usuario en la base de datos.
	 * @return objeto Perfil asociado a ese nick de usuario, si no existe devuelve <b>null</b>.
	 */
	@Override
	public Perfil buscarPorNick(String nick) {
		Perfil p = null;
		try {
			p = perfDao.buscarPorNick(nick);
		} catch (NoResultException e) {
			logger.warn("No hay usuario con ese nick");
		}
		return p;
	}
	
	/**
	 * Obtiene un listado de usuarios cualesquiera de la base de datos, obviando de la lista al usuario que llama a este método.
	 * @param p perfil que realiza la consulta.
	 * @return lista de perfiles cualesquiera, no devolverá el perfil que introducido por parámetro en la lista.  
	 */
	 @Override
	public ArrayList<Perfil> getPerfiles(Perfil p){
		ArrayList<Perfil> perfiles = null;
		perfiles = perfDao.getPerfiles(p);
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
		perfDao.likeDislike(p, cod_perfil, tipo_lista);	
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
		contactos = perfDao.getContactos(p);
		return contactos;
	}
	/**
	 * Devuelve la lista de perfiles descartados por el perfil que lo solicita. Entendemos por <em>descarte</em> a cualquier otro perfil al que se haya dado <b>dislike</b>.
	 * @param p perfil que realiza la solicitud.
	 * @return lista de perfiles al que dicho haya dislike.
	 */
	@Override
	public ArrayList<Perfil> getDescartes(Perfil p) {
		ArrayList<Perfil> contactos=null;
		contactos=perfDao.getDescartes(p);

		return contactos;
	}

	@Override
	public ArrayList<Perfil> getMatch(Perfil p) {
		ArrayList<Perfil> contactos=null;
		contactos=perfDao.getMatch(p);
		return contactos;
	}

	@Override
	public Perfil cargarMiperfil(Perfil p) {
		Perfil miperfil = null;
		miperfil = perfDao.cargarMiperfil(p);
		return miperfil;
		
	}
	
	@Override
	public boolean editarPerfil(Perfil p, Perfil old) {
		p.setCod_perfil(old.getCod_perfil());
		p.setNick_perfil(old.getNick_perfil());
		return perfDao.editarPerfil(p);
	}

	@Override
	public void eliminarPerfil(Perfil p) {
		perfDao.eliminarPerfil(p);
		
	}
	
}
