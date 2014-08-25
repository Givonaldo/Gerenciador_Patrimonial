package br.com.controle.modelos;

public enum OPCOES_MODELOS {

	JANELA_CADASTRAR_OBJETOS("Janela cadastrar Objetos"), JANELA_CADASTRAR_ESCOLAS("Janela cadastrar escolas"), 
	OBJETO("Objeto"), ESCOLA("Escola"), ENVIO_OBJETOS("Envio de Objetos");
	
	String titulo;
	OPCOES_MODELOS(String titulo){
		this.titulo = titulo;
	}
	
	public String getTitulo(){
		return this.titulo;
	}
	
}