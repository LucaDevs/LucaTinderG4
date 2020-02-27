package com.luca.tinder.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "categorias")
public class Categoria {
	@Id
	@GeneratedValue
	@Column(name = "id_categoria")
	private int id_categoria;
	private String nombre_categoria;
	@ManyToMany
	private Set<Perfil> perfil;

	public Categoria() {
	}

	public Categoria(int id_categoria, String nombre_categoria, Set<Perfil> perfil) {
		super();
		this.id_categoria = id_categoria;
		this.nombre_categoria = nombre_categoria;
		this.perfil = perfil;
	}

	public int getId_categoria() {
		return id_categoria;
	}

	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}

	public String getNombre_categoria() {
		return nombre_categoria;
	}

	public void setNombre_categoria(String nombre_categoria) {
		this.nombre_categoria = nombre_categoria;
	}

	public Set<Perfil> getPerfil() {
		return perfil;
	}

	public void setPerfil(Set<Perfil> perfil) {
		this.perfil = perfil;
	}

	@Override
	public String toString() {
		return "Categoria [id_categoria=" + id_categoria + ", nombre_categoria=" + nombre_categoria + ", perfil= " + perfil + "]";
	}

	
	
}
