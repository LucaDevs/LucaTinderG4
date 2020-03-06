package com.luca.tinder.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luca.tinder.dao.CategoriaDao;
import com.luca.tinder.model.Categoria;
/**
 * 
 * @author David Heras
 * @since 1.0
 */
@Service
@Transactional
public class CategoriaServiceImpl implements CategoriaService {
	@Autowired
	private CategoriaDao catDao;
	
	/**
	 * Listado de todas las categorias dadas de alta en la base de datos.
	 */
	@Override
	public List<Categoria> listarCategoria(){
		
		return catDao.findAll();
	}
}
