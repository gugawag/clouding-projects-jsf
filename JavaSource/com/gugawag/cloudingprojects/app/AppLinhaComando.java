package com.gugawag.cloudingprojects.app;

import java.util.Scanner;

import javax.naming.InitialContext;

import com.gugawag.cloudingprojects.bd.AlunoManager;
import com.gugawag.cloudingprojects.modelo.Aluno;
import com.gugawag.cloudingprojects.modelo.AlunoInexistenteException;
import com.gugawag.cloudingprojects.modelo.AlunoJahMatriculadoException;

/**
 * Cliente do EJB de gerência. Não funciona no JBoss 7 pois este não dá suporte a remote client (JNDI)
 * @author gugawag
 *
 */
public class AppLinhaComando {

	private static AlunoManager alunoManager;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		InitialContext context = new InitialContext();
		alunoManager = (AlunoManager) context.lookup("java:module/AlunoManager");
		menu();
	}
	
	/**
	 * Mostra o menu e solicita ao usuário uma opção a ser escolhida.
	 */
	public static void menu(){
		int opcao = -1;
		Scanner entrada = new Scanner(System.in);
		do{
			System.out.println("----------");
			System.out.println("1 - Inserir usuário;");
			System.out.println("2 - Listar usuários;");
			System.out.println("3 - Remover usuário por matrícula;");
			System.out.println("0 - Sair;");
			System.out.println("----------");
			opcao = entrada.nextInt();
			switch(opcao){
				case 1:{
					inserirUsuario();
					break;
				}
				case 2:{
					listarUsuarios();
					break;
				}
				case 3:{
					removerUsuario();
					break;
				}
				case 0:{
					System.out.println("Fechando...");
					System.exit(0);
					break;
				}
				default:{
					System.out.println("Opção inválida!");
				}
			}
		} while(opcao != 0);
		
		
	}

	private static void removerUsuario() {		
		Scanner entrada = new Scanner(System.in);
		System.out.println("Digite a matrícula do usuário:");
		String matricula = entrada.nextLine();
		try {
			alunoManager.removeAlunoPorMatricula(matricula);
		} catch (AlunoInexistenteException e) {
			System.out.println(e.getMessage());
		}
		
	}

	private static void listarUsuarios() {
		for (Aluno aluno: alunoManager.getAlunos()){
			System.out.println(aluno);
		}
		
	}

	private static void inserirUsuario() {
		Scanner entrada = new Scanner(System.in);
		System.out.println("Digite as informações do usuário:");
		System.out.print("       Matrícula:");
		String matricula = entrada.nextLine();
		System.out.print("       Nome:");
		String nome = entrada.nextLine();
		System.out.print("       Login:");
		String login = entrada.nextLine();
		System.out.print("       Senha:");
		String senha = entrada.nextLine();
		Aluno aluno = new Aluno(matricula, nome, login, senha);
		try {
			alunoManager.acrescentaAtualizaUsuario(aluno);
		} catch (AlunoJahMatriculadoException e) {
			System.out.println(e.getMessage());
		}
	}

}
