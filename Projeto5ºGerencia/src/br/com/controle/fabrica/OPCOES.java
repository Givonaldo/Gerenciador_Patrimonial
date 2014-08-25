package br.com.controle.fabrica;

/**
 * 
 * @author gilvonaldo
 * @version 1.0
 */
public enum OPCOES {

	FABRICA_DE_GERENCIADORES("Fabrica de Gerenciadores"), FABRICA_DE_MODELOS("Fabrica de Modelos"), 
	ESCOLA("Escola"), OBJETO("Objeto"), GERENCIA_CADASTRO("Gerencia Cadastro")
	, GERENCIA_DADOS("Gerencia Dados"), AUTENTICACAO("Autenticação"), FACHADA("Fachada"),
	ENVIO_DE_OBJETOS("Envio de Objetos");
	
	private String titulo;
	
	OPCOES(String titulo){
		this.titulo = titulo;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
}