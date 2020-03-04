package com.luca.tinder.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luca.tinder.dao.CategoriaDao;
import com.luca.tinder.model.Categoria;
@Service
@Transactional
public class CategoriaServiceImpl implements CategoriaService {
	@Autowired
	private CategoriaDao catDao;
	
	@Override
	public List<Categoria> listarCategoria(){
		
		return catDao.findAll();
	}
}
