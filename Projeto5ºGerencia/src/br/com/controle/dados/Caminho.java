package br.com.controle.dados;

/**
 * Enumeração que possui o caminho relativo para
 * a realização da persistencia dos dados.
 * 
 * @author gilvonaldo
 * @version 1.0
 * @see PersistenciaXML
 */
public enum Caminho {

	CAMINHO((System.getProperty("user.dir") + System.getProperty("file.separator")
			+ "/Img" + System.getProperty("file.separator"))),

	ARQUIVO_UNICO("Arquivo_Unico.xml"), ARQUIVO_ENVIO("Arquivo_Envio.ser");
	
	private String titulo;
	
	Caminho(String titulo){
		this.titulo = titulo;
	}

	public String getTitulo(){
		return this.titulo;
	}

}