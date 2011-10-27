package com.gugawag.cloudingprojects.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * Classe que representa um projeto no sistema Clouding Projects.
 * @author gugawag, gugawag@gmail.com
 *
 */
public class Projeto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer codigo;
	private String nome;
	private String url;
	
	@Transient
	private List<Entrega> entregas;
	@Transient
	private List<Cliente> clientes;
	
	public Projeto() {
		this(null, null);
	}
	
	public Projeto(String nome, String url) {
		super();
		this.nome = nome;
		this.url = url;
		entregas = new ArrayList<Entrega>();
		clientes = new ArrayList<Cliente>();
	}


	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Entrega> getEntregas() {
		return entregas;
	}
	
	
	public List<Cliente> getClientes() {
		return clientes;
	}

	public void addEntrega(Entrega entrega){
		this.entregas.add(entrega);
	}
	
	public void addCliente(Cliente cliente){
		this.clientes.add(cliente);
	}
}
