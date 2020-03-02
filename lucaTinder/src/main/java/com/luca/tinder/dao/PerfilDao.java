package com.luca.tinder.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luca.tinder.model.Perfil;

@Repository
public interface PerfilDao extends JpaRepository<Perfil, Integer>, PerfilDaoCustom{

	

}
