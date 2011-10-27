package com.gugawag.cloudingprojects.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Aluno implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer codigo;
	private String matricula;
	private String nome;
	private String login;
	private String senha;

	// Transiente significa que n�o ser� armazenado no banco de dados. Remover
	// essa anota��o ap�s mapear Projeto com JPA
	@Transient
	private List<Projeto> projetos;

	public Aluno() {
		this(null, null, null, null);
	}

	public Aluno(String matricula, String nome, String login, String senha) {
		super();
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.matricula = matricula;
		projetos = new ArrayList<Projeto>();
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public List<Projeto> getProjetos() {
		return projetos;
	}

	public void setProjetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}
	
	public void acrescentaProjeto(Projeto projeto){
		this.projetos.add(projeto);
	}

	public String toString() {
		return "Matr�cula: [" + matricula + "] Nome: [" + nome + "] Login: ["
				+ login + "]";
	}

}
