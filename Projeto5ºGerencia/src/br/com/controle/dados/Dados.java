package br.com.controle.dados;

import java.util.ArrayList;

import br.com.controle.modelos.EnvioDeObjetos;
import br.com.controle.modelos.Escola;
import br.com.controle.modelos.Objeto;

/**
 * Classe dados, essa classe realizará o controle e 
 * representará uma classe com todos os dados que 
 * serão salvo.
 * 
 * @author gilvonaldo
 * @version 1.0
 * @see PersistenciaXML
 */
public class Dados {

	private ArrayList<Escola> escola;
	private ArrayList<Objeto> objeto;
	private ArrayList<Object> dados;
	private PersistenciaXML persistencia;
	private PersistenciaSerializacao persistenciaSer;
	private ArrayList<EnvioDeObjetos> envios;
	
	public Dados(){
		envios = new ArrayList<>();
		escola = new ArrayList<>();
		objeto = new ArrayList<>();
		dados = new ArrayList<>();
	}
	
	/**
	 * 
	 * @param objeto
	 */
	public void persistencia(){

		persistencia = PersistenciaXML.getInstance();
		
		if (persistencia.load(Caminho.ARQUIVO_UNICO.getTitulo()) != null){
			
			for (Object o : persistencia.load(Caminho.ARQUIVO_UNICO.getTitulo())){
				dados.add(o);		
			}
		}for (Escola escola : this.escola){
			
			dados.add(escola);
			
		}for (Objeto objeto : this.objeto){
			
			dados.add(objeto);
		}
		persistencia.salvar(dados, Caminho.ARQUIVO_UNICO.getTitulo());
	}
	
	/**
	 * Metodo que chama o metodo que adiciona os dados em um Arquivo
	 * @param obj
	 */
	public void addEnvio(EnvioDeObjetos obj){
		persistenciaEnvio(Caminho.ARQUIVO_ENVIO.getTitulo(), obj);
	}
	
	/**
	 * 
	 * @param nomeArquivo
	 * @param envioDeObjetos
	 */
	public void persistenciaEnvio(String nomeArquivo, EnvioDeObjetos envioDeObjetos){
		
		persistenciaSer = PersistenciaSerializacao.getInstance();
		
		EnvioDeObjetos envioDeObjeto = null;
		
		if (persistenciaSer.readObject(nomeArquivo) != null){
			
			for (Object envio : persistenciaSer.readObject(nomeArquivo)){
				envioDeObjeto = (EnvioDeObjetos) envio;
				setEnvios(envioDeObjeto);
			}
			setEnvios(envioDeObjetos);
			persistenciaSer.writeObject(Caminho.ARQUIVO_ENVIO.getTitulo(), envios);
			
		}else {
			setEnvios(envioDeObjetos);
			persistenciaSer.writeObject(Caminho.ARQUIVO_ENVIO.getTitulo(), envios);
		}
	}
	
	/**
	 * 
	 * @param envios
	 */
	public void setEnvios(EnvioDeObjetos envios) {
		this.envios.add(envios);
	}
	
	/**
	 * 
	 * @return envios
	 */
	public ArrayList<EnvioDeObjetos> getEnvios() {
		return envios;
	}
	
	/**
	 * 
	 * @param escola
	 */
	public void addEscola(Escola escola) {
		this.escola.add(escola);
		persistencia();
	}
	
	/**
	 * 
	 * @return escola
	 */
	public ArrayList<Escola> getEscola() {
		return escola;
	}
	
	/**
	 * 
	 * @param objeto
	 */
	public void addObjeto(Objeto objeto) {
		this.objeto.add(objeto);
		persistencia();
	}
	
	/**
	 * 
	 * @return objeto
	 */
	public ArrayList<Objeto> getObjeto() {
		return objeto;
	}
	
	
}