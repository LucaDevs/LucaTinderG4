package com.luca.tinder.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luca.tinder.model.Perfil;
/**
 * 
 * @author Alejandro Martin
 * @since 1.0
 *
 */
@Repository
public interface PerfilDao extends JpaRepository<Perfil, Integer>, PerfilDaoCustom{

	

}
