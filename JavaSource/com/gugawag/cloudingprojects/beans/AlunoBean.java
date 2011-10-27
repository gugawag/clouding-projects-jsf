package com.gugawag.cloudingprojects.beans;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.gugawag.cloudingprojects.bd.AlunoManager;
import com.gugawag.cloudingprojects.modelo.Aluno;
import com.gugawag.cloudingprojects.modelo.AlunoInexistenteException;
import com.gugawag.cloudingprojects.modelo.AlunoJahMatriculadoException;

@ManagedBean
@SessionScoped
public class AlunoBean {

	@EJB
	private AlunoManager alunoManager;
	
	private Aluno aluno, alunoPesquisado;
	private List<String> matriculasAlunosRemover;
	private Map<String, Aluno> alunosMap;	

	public AlunoBean() {
		aluno = new Aluno();
		alunoPesquisado = new Aluno();
		alunosMap = new HashMap<String, Aluno>();
	}

	@PostConstruct
	public void test(){
		List<Aluno> alunos = alunoManager.getAlunos();
		for(Aluno aluno: alunos){
			alunosMap.put(aluno.getMatricula(), aluno);
		}

	}
	
	public Map<String, Aluno> getAlunosMap() {
		return alunosMap;
	}



	public void setAlunosMap(Map<String, Aluno> alunosMap) {
		this.alunosMap = alunosMap;
	}



	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public List<Aluno> getAlunos() {
		return alunoManager.getAlunos();
	}

	public void setMatriculasAlunosRemover(List<String> matriculasAlunosRemover) {
		this.matriculasAlunosRemover = matriculasAlunosRemover;
	}
	
	public List<String> getMatriculasAlunosRemover() {
		return matriculasAlunosRemover;
	}

	public Aluno getAlunoPesquisado() {
		return alunoPesquisado;
	}

	public void setAlunoPesquisado(Aluno alunoPesquisado) {
		this.alunoPesquisado = alunoPesquisado;
	}

	public String cadastrar() {

		try {
			alunoManager.acrescentaAtualizaUsuario(aluno);
			//TODO trocar abaixo por mensagem do MessageFactory
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Aluno cadastrado"));
		} catch (AlunoJahMatriculadoException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage()));
		}
		return null;
	}
	
	public void removerAlunos() {
		try {
			alunoManager.removeAlunoPorMatricula(this.aluno.getMatricula());
		} catch (AlunoInexistenteException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage()));

		}
	}
	
	public String pesquisarPorNome(){
		this.alunoPesquisado = alunoManager.pesquisarAlunoPorNome(this.alunoPesquisado.getNome());
		if (alunoPesquisado == null){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Nenhum aluno foi encontrado!"));
		}
		return null;
	}

}
