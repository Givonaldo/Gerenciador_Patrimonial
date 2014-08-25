package br.com.controle.fachada;

import java.util.Collection;
import br.com.controle.modelos.Escola;
import br.com.controle.modelos.Objeto;

/**
 * Interface do programa de gerenciamendo patrimonial da 5º Gerência
 * regional de educação da paraíba, localizada no municipio de Monteiro. 
 *  Essa interface representa um ponto unico de acesso ao sistema, 
 *  facilitando assim uma maior simplicidade no acesso aos dados.
 *  
 * @author gilvonaldo
 * @version 1.0
 */
public interface FachadaIF {

	/**
	 * Metodo que deve receber como parâmetro uma String representando o nome de uma 
	 * escola, outra String descrevendo a cidade onde a escola está localizada e outra 
	 * String que representa a data de cadastro da escola.
	 *  
	 * @param nome
	 * @param cidade
	 */
	public void addEscola(String nome, String cidade, String data);
	
	/**
	 * 
	 * @param nome
	 * @param atributo
	 * @param novoAtributo
	 */
	public void auteraEscola(String nome, String atributo, String novoAtributo);
	
	/**
	 * 
	 * @param nome
	 */
	public void removeEscola(String nome);
	
	/**
	 * 
	 * @param nome
	 * @param descricao
	 * @param numTombamento
	 */
	public void addObjeto(String nome, String descricao, String numTombamento);
	
	/**
	 * 
	 * @param numTombamento
	 * @param novoAtributo
	 */
	public void auteraObjeto(String numTombamento, String novoAtributo);
	
	/**
	 * 
	 * @param numTombamento
	 */
	public void removeObjeto(String numTombamento);
	
	/**
	 * 
	 * @param objeto
	 * @param escola
	 * @param data
	 */
	public void enviarObjetos(Collection<Objeto> objeto, Escola escola);
	
	/**
	 * 
	 * @param booleano
	 */
	public void limparEnvios();
	
}