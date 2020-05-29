package com.examen.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sintoma")
public class Sintoma implements Serializable {
	private static final long serialVersionUID = -5167520845419981780L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String nombre;
	
	
	
	public Sintoma() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Sintoma(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	public int getid() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
}
