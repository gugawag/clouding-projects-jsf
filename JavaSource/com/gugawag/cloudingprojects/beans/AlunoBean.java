package com.gugawag.cloudingprojects.beans;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.gugawag.cloudingprojects.bd.AlunoManager;
import com.gugawag.cloudingprojects.modelo.Aluno;
import com.gugawag.cloudingprojects.modelo.AlunoJahMatriculadoException;
import com.gugawag.cloudingprojects.modelo.Projeto;

@ManagedBean
@SessionScoped
public class AlunoBean {

	@EJB
	private AlunoManager alunoManager;
	private Aluno aluno;
	
	public AlunoBean(){
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
			aluno.acrescentaProjeto(new Projeto("Proj1", "http://proj1"));
			alunoManager.acrescentaAtualizaUsuario(aluno);
			Aluno alunoRecuperado = alunoManager.getAlunoPorMatricula(aluno.getMatricula());
			System.out.println("Quant projetos:" + alunoRecuperado.getProjetos().size());
		} catch (AlunoJahMatriculadoException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage()));
		}
		return null;
	}

}
