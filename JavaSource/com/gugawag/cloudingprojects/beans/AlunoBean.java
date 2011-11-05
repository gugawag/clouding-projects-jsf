package com.gugawag.cloudingprojects.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import com.gugawag.cloudingprojects.modelo.Aluno;
import com.gugawag.cloudingprojects.modelo.AlunoInexistenteException;
import com.gugawag.cloudingprojects.modelo.AlunoJahMatriculadoException;
import com.gugawag.cloudingprojects.service.AlunoService;

@ManagedBean
@SessionScoped
public class AlunoBean {

	@EJB
	private AlunoService alunoService;
	
	private Aluno aluno, alunoPesquisado;
	private List<String> matriculasAlunosRemover;
	private DataModel dmAlunos;
	

	public AlunoBean() {
		aluno = new Aluno();
		alunoPesquisado = new Aluno();
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public DataModel<Aluno> getAlunos() {
		return (dmAlunos = new ListDataModel(alunoService.getAlunos())); 
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
			alunoService.acrescentaAtualizaUsuario(aluno);
			//TODO trocar abaixo por mensagem do MessageFactory
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Aluno cadastrado"));
		} catch (AlunoJahMatriculadoException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage()));
		}
		return null;
	}
	
	public String pesquisarPorNome(){
		this.alunoPesquisado = alunoService.getAlunoPorNome(this.alunoPesquisado.getNome());
		if (alunoPesquisado == null){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Nenhum aluno foi encontrado!"));
		}
		return null;
	}
	
	public String removerAluno(){
		Aluno alunoARemover = (Aluno) dmAlunos.getRowData();
		try {
			alunoService.removeAlunoPorMatricula(alunoARemover.getMatricula());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Aluno removido com sucesso!"));
		} catch (AlunoInexistenteException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("N‹o existe aluno com esta matr’cula!"));
		}
		return null;
	}
	
}
