package com.luca.tinder.model;

import java.util.Set;

public class Perfil {
	
	private String nick_perfil;
	private String nombre_perfil;
	private char genero_perfil;
	private int edad_perfil;
	private String descripcion;
	private String poblacion;
	private Set<Categoria> categoria;
	
	
	public Perfil() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Perfil(String nick_perfil, String nombre_perfil, char genero_perfil, int edad_perfil, String descripcion,
			String poblacion, Set<Categoria> categoria) {
		super();
		this.nick_perfil = nick_perfil;
		this.nombre_perfil = nombre_perfil;
		this.genero_perfil = genero_perfil;
		this.edad_perfil = edad_perfil;
		this.descripcion = descripcion;
		this.poblacion = poblacion;
		this.categoria = categoria;
	}


	public String getNick_perfil() {
		return nick_perfil;
	}


	public void setNick_perfil(String nick_perfil) {
		this.nick_perfil = nick_perfil;
	}


	public String getNombre_perfil() {
		return nombre_perfil;
	}


	public void setNombre_perfil(String nombre_perfil) {
		this.nombre_perfil = nombre_perfil;
	}


	public char getGenero_perfil() {
		return genero_perfil;
	}


	public void setGenero_perfil(char genero_perfil) {
		this.genero_perfil = genero_perfil;
	}


	public int getEdad_perfil() {
		return edad_perfil;
	}


	public void setEdad_perfil(int edad_perfil) {
		this.edad_perfil = edad_perfil;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String getPoblacion() {
		return poblacion;
	}


	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}


	public Set<Categoria> getCategoria() {
		return categoria;
	}


	public void setCategoria(Set<Categoria> categoria) {
		this.categoria = categoria;
	}


	@Override
	public String toString() {
		return "Perfil [nick_perfil=" + nick_perfil + ", nombre_perfil=" + nombre_perfil + ", genero_perfil="
				+ genero_perfil + ", edad_perfil=" + edad_perfil + ", descripcion=" + descripcion + ", poblacion="
				+ poblacion + ", categoria=" + categoria + "]";
	}

}
