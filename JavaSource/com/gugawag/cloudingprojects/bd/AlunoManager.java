package com.gugawag.cloudingprojects.bd;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.gugawag.cloudingprojects.modelo.Aluno;
import com.gugawag.cloudingprojects.modelo.AlunoInexistenteException;
import com.gugawag.cloudingprojects.modelo.AlunoJahMatriculadoException;

@Stateless
public class AlunoManager implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager em;
	
	public void acrescentaAtualizaUsuario(Aluno aluno) throws AlunoJahMatriculadoException{
		Aluno alunoAInserir = getAlunoPorMatricula(aluno.getMatricula());
		if (alunoAInserir != null){
			throw new AlunoJahMatriculadoException("O aluno de matr’cula [" + alunoAInserir.getMatricula() +"]" +
					"j‡ est‡ cadastrado!");
		}
		if (aluno.getCodigo() != null){
			em.merge(aluno);
		} else{
			em.persist(aluno);
		}
	}
	
	public List<Aluno> getAlunos(){
		return (List<Aluno>)em.createQuery("from Aluno").getResultList();
	}
	
	public Aluno getAlunoPorMatricula(String matricula){
		List<Aluno> alunos = (List<Aluno>)em.createQuery("from Aluno a where a.matricula=:matricula").setParameter("matricula", matricula).getResultList();
		if ((alunos != null) && (alunos.size()>0)){
			return alunos.get(0);
		}
		return null;
	}
	
	public void removeAlunoPorMatricula(String matricula) throws AlunoInexistenteException{
		Aluno alunoARemover = getAlunoPorMatricula(matricula);
		if (alunoARemover == null){
			throw new AlunoInexistenteException("O usu‡rio com matr’cula [" + matricula+ "] n‹o existe!");
		}
		em.remove(alunoARemover);
	}

	public Aluno pesquisarAlunoPorNome(String nome) {
		List<Aluno> alunos = (List<Aluno>)em.createQuery("from Aluno a where a.nome=:nome").setParameter("nome", nome).getResultList();
		if ((alunos != null) && (alunos.size()>0)){
			return alunos.get(0);
		}
		return null;

	}
}
