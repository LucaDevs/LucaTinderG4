package com.luca.tinder.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luca.tinder.model.Categoria;

@Repository
public interface CategoriaDao extends JpaRepository<Categoria, Integer>, CategoriaDaoCustom{

}
