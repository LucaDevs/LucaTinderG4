package com.luca.tinder.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "perfiles")
public class Perfil {


	private String nick_perfil;
	private String nombre_perfil;
	private char genero_perfil;
	private int edad_perfil;
	private String descripcion_perfil;
	private String poblacion_perfil;
	private Set<Categoria> categoria;
	
	
	public Perfil() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Perfil(String nick_perfil, String nombre_perfil, char genero_perfil, int edad_perfil, String descripcion_perfil,
			String poblacion_perfil, Set<Categoria> categoria) {
		super();
		this.nick_perfil = nick_perfil;
		this.nombre_perfil = nombre_perfil;
		this.genero_perfil = genero_perfil;
		this.edad_perfil = edad_perfil;
		this.descripcion_perfil = descripcion_perfil;
		this.poblacion_perfil = poblacion_perfil;
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


	public String getDescripcion_perfil() {
		return descripcion_perfil;
	}


	public void setDescripcion_perfil(String descripcion_perfil) {
		this.descripcion_perfil = descripcion_perfil;
	}


	public String getPoblacion_perfil() {
		return poblacion_perfil;
	}


	public void setPoblacion_perfil(String poblacion_perfil) {
		this.poblacion_perfil = poblacion_perfil;
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
				+ genero_perfil + ", edad_perfil=" + edad_perfil + ", descripcion=" + descripcion_perfil + ", poblacion="
				+ poblacion_perfil + ", categoria=" + categoria + "]";
	}

}
