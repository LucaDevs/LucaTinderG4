package com.luca.tinder.model;

import java.util.HashMap;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.javafaker.Faker;


@Entity
@Table(name = "perfiles")
public class Perfil {
	
	static Logger logger= LoggerFactory.getLogger(Perfil.class);
	
	@Id
	@GeneratedValue
	@Column(name = "cod_perfil")
	private int cod_perfil;
	@NotEmpty
	private String nick_perfil;
	@NotEmpty
	private String nombre_perfil;
	//@NotEmpty
	private char genero_perfil;
	//@Size(min=18)
	private int edad_perfil;
	
	private String descripcion_perfil;
	
	private String password;
	
	private boolean enabled;
	
	private String poblacion_perfil;
	@ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name="gustos",
	joinColumns=@JoinColumn(name="cod_perfil"),
	inverseJoinColumns=@JoinColumn(name="id_categoria"))
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

	public int getCod_perfil() {
		return cod_perfil;
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
	
	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public boolean isEnabled() {
		return enabled;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	public void setCod_perfil(int cod_perfil) {
		this.cod_perfil = cod_perfil;
	}


	//Crear perfiles falsos
	public static HashMap<String, Perfil> CrearFalsosPerfiles() {
		HashMap<String, Perfil> cuentas = new HashMap<String, Perfil>();
		for(int i=0;i<10;i++) {
			Perfil p=new Perfil();
			Faker faker = new Faker();
			
			
			int azar=(int)(Math.random()*2);
			
			p.setNombre_perfil(faker.animal().name());
			p.setNick_perfil(faker.animal().name());
			p.setEdad_perfil((int)((Math.random()*90)+18));
			p.setPoblacion_perfil(faker.address().cityName());
			p.setPassword("123");
			if(azar==0) {
				p.setGenero_perfil('F');
			} else {
				p.setGenero_perfil('M');
			}
			String desc = faker.company().catchPhrase();
			if(desc.length() <= 100) {
				p.setDescripcion_perfil(desc);
			}else
				p.setDescripcion_perfil(desc.substring(0, 99));
			p.setEnabled(true);
			cuentas.put(p.getNick_perfil(), p);
			logger.info(p.toString());
		
		}
		
		return cuentas;
	}


	@Override
	public String toString() {
		return "Perfil [cod_perfil=" + cod_perfil + ", nick_perfil=" + nick_perfil + ", nombre_perfil=" + nombre_perfil
				+ ", genero_perfil=" + genero_perfil + ", edad_perfil=" + edad_perfil + ", descripcion_perfil="
				+ descripcion_perfil + ", password=" + password + ", enabled=" + enabled + ", poblacion_perfil="
				+ poblacion_perfil + ", categoria=" + categoria + "]";
	}


	

}
