package com.gugawag.cloudingprojects.beans;

import java.util.List;

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
	private Aluno aluno;
	private List<Aluno> alunosARemover;

	public AlunoBean() {
		aluno = new Aluno();
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public String cadastrar() {
		try {
			alunoManager.acrescentaAtualizaUsuario(aluno);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Aluno matriculado!"));
		} catch (AlunoJahMatriculadoException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage()));
		}
		return null;
	}

	public List<Aluno> getAlunos() {
		return alunoManager.getAlunos();
	}

	public void setAlunosARemover(List<Aluno> alunosARemover) {
		this.alunosARemover = alunosARemover;
	}

	public void removerAlunos() {
		try {
			alunoManager.removeAlunoPorMatricula(this.aluno.getMatricula());
		} catch (AlunoInexistenteException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage()));

		}
	}

}
