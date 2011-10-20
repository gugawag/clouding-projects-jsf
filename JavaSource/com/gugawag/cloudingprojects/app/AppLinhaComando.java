package com.gugawag.cloudingprojects.app;

import java.util.Scanner;

import javax.naming.InitialContext;

import com.gugawag.cloudingprojects.bd.AlunoManager;
import com.gugawag.cloudingprojects.modelo.Aluno;
import com.gugawag.cloudingprojects.modelo.AlunoInexistenteException;
import com.gugawag.cloudingprojects.modelo.AlunoJahMatriculadoException;

/**
 * Cliente do EJB de ger�ncia. N�o funciona no JBoss 7 pois este n�o d� suporte a remote client (JNDI)
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
	 * Mostra o menu e solicita ao usu�rio uma op��o a ser escolhida.
	 */
	public static void menu(){
		int opcao = -1;
		Scanner entrada = new Scanner(System.in);
		do{
			System.out.println("----------");
			System.out.println("1 - Inserir usu�rio;");
			System.out.println("2 - Listar usu�rios;");
			System.out.println("3 - Remover usu�rio por matr�cula;");
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
					System.out.println("Op��o inv�lida!");
				}
			}
		} while(opcao != 0);
		
		
	}

	private static void removerUsuario() {		
		Scanner entrada = new Scanner(System.in);
		System.out.println("Digite a matr�cula do usu�rio:");
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
		System.out.println("Digite as informa��es do usu�rio:");
		System.out.print("       Matr�cula:");
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
