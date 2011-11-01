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
	
	public void removeAlunoPorMatricula(Aluno aluno) throws AlunoInexistenteException{
		em.remove(aluno);
	}

	public Aluno getAlunoPorNome(String nome) {
		List<Aluno> alunos = (List<Aluno>)em.createQuery("from Aluno a where a.nome=:nome").setParameter("nome", nome).getResultList();
		if ((alunos != null) && (alunos.size()>0)){
			return alunos.get(0);
		}
		return null;

	}
}
