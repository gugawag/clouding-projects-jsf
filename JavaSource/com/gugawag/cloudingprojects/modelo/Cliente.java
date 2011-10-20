package com.gugawag.cloudingprojects.modelo;

import java.io.Serializable;

/**
 * 
 * @author gugawag, gugawag@gmail.com
 *
 */
public class Cliente implements Serializable{


	private static final long serialVersionUID = 1L;
	private String nome;
	private String email;
	
		
	public Cliente() {
		super();
	}
	public Cliente(String nome, String email) {
		super();
		this.nome = nome;
		this.email = email;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
