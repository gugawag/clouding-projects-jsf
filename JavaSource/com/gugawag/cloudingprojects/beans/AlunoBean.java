package com.gugawag.cloudingprojects.beans;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.gugawag.cloudingprojects.bd.AlunoManager;
import com.gugawag.cloudingprojects.modelo.Aluno;
import com.gugawag.cloudingprojects.modelo.AlunoJahMatriculadoException;

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
			alunoManager.acrescentaAtualizaUsuario(new Aluno(aluno.getMatricula(), 
					aluno.getNome(), aluno.getLogin(), aluno.getSenha()));
			Aluno alunoRecuperado = alunoManager.getAlunoPorMatricula(aluno.getMatricula());
			System.out.println(alunoRecuperado);
			alunoRecuperado.setNome("ALTERADO");
			alunoRecuperado.setMatricula("MAT ALTERADa");
			alunoManager.acrescentaAtualizaUsuario(alunoRecuperado);
			
			alunoRecuperado = alunoManager.getAlunoPorMatricula(aluno.getMatricula());
			System.out.println(alunoRecuperado);
		} catch (AlunoJahMatriculadoException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage()));
		}
		return null;
	}

}
