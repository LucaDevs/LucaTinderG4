package com.luca.tinder.model;

import java.util.Set;

public class Perfil {

	private String nick_perfil;
	private String nombre;
	private char genero;
	private int edad;
	private String descripcion;
	private String poblacion;
	private Set<Categoria> gustos;

	public Perfil() {
	}

	public Perfil(String nick_perfil, String nombre, char genero, int edad, String descripcion, String poblacion,
			Set<Categoria> gustos) {
		super();
		this.nick_perfil = nick_perfil;
		this.nombre = nombre;
		this.genero = genero;
		this.edad = edad;
		this.descripcion = descripcion;
		this.poblacion = poblacion;
		this.gustos = gustos;
	}

	public String getNick_perfil() {
		return nick_perfil;
	}

	public void setNick_perfil(String nick_perfil) {
		this.nick_perfil = nick_perfil;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public char getGenero() {
		return genero;
	}

	public void setGenero(char genero) {
		this.genero = genero;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
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

	public Set<Categoria> getGustos() {
		return gustos;
	}//

	public void setGustos(Set<Categoria> gustos) {
		this.gustos = gustos;
	}

	@Override
	public String toString() {
		return "Perfil [nick_perfil=" + nick_perfil + ", nombre=" + nombre + ", genero=" + genero + ", edad=" + edad
				+ ", descripcion=" + descripcion + ", poblacion=" + poblacion + ", gustos=" + gustos + "]";
	}

}
