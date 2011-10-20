package com.gugawag.cloudingprojects.modelo;

import java.io.Serializable;
import java.util.Date;

/**
 * Representa uma entrega do projeto.
 * @author gugawag, gugawag@gmail.com
 *
 */
public class Entrega implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String observacao;
	private Date data;
		
	public Entrega() {
		super();
	}
	
	public Entrega(String observacao, Date data) {
		super();
		this.observacao = observacao;
		this.data = data;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
	

}
