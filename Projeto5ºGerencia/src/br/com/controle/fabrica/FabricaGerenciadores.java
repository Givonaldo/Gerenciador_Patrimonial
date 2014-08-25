package br.com.controle.fabrica;

import br.com.controle.gerencia.GerenciaCadastro;
import br.com.controle.gerencia.GerenciaDados;
import br.com.controle.gerencia.GerenciadorAbstrato;

/**
 * 
 * @author gilvonaldo
 * @version 1.0
 */
public abstract class FabricaGerenciadores {

	public FabricaGerenciadores(){
		
	}
	
	private static  GerenciadorAbstrato gerenciadores;
	
	public static Object getObject(String objeto) {
		
		if (objeto.equals(OPCOES.GERENCIA_CADASTRO.getTitulo())){
			System.out.println("Entrou aqui ");
			gerenciadores = new GerenciaCadastro();	
		}else if (objeto.equals(OPCOES.GERENCIA_DADOS.getTitulo())){
			gerenciadores =  new GerenciaDados();
		}
		return gerenciadores;
	}
	
}