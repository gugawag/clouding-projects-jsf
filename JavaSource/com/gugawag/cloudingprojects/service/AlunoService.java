package com.gugawag.cloudingprojects.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.gugawag.cloudingprojects.bd.AlunoManager;
import com.gugawag.cloudingprojects.modelo.Aluno;
import com.gugawag.cloudingprojects.modelo.AlunoInexistenteException;
import com.gugawag.cloudingprojects.modelo.AlunoJahMatriculadoException;

@Stateless
public class AlunoService {
	
	@EJB
	private AlunoManager alunoManager;

	public void acrescentaAtualizaUsuario(Aluno aluno) throws AlunoJahMatriculadoException{
		Aluno alunoAInserir = getAlunoPorMatricula(aluno.getMatricula());
		if (alunoAInserir != null){
			throw new AlunoJahMatriculadoException("O aluno de matr’cula [" + alunoAInserir.getMatricula() +"]" +
					"j‡ est‡ cadastrado!");
		}
		alunoManager.acrescentaAtualizaUsuario(aluno);
	}
	
	public List<Aluno> getAlunos(){
		return alunoManager.getAlunos();
	}
	
	public Aluno getAlunoPorMatricula(String matricula){
		return alunoManager.getAlunoPorMatricula(matricula);
	}
	
	public void removeAlunoPorMatricula(String matricula) throws AlunoInexistenteException{
		Aluno alunoARemover = alunoManager.getAlunoPorMatricula(matricula);
		if (alunoARemover == null){
			throw new AlunoInexistenteException("O usu‡rio com matr’cula [" + matricula+ "] n‹o existe!");
		}
		alunoManager.removeAlunoPorMatricula(alunoARemover);
	}

	public Aluno getAlunoPorNome(String nome) {
		return alunoManager.getAlunoPorNome(nome);
	}


}
