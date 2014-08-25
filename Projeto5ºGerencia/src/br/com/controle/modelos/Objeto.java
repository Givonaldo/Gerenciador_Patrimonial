package br.com.controle.modelos;

import java.io.Serializable;

/**
 * 
 * @author gilvonaldo
 * @version 1.0
 */
public class Objeto implements ModelosIF, Serializable {

	private String nome, descricao;
	private String numTombamento;
	
	public Objeto(){
		
	}
	
	/**
	 * 
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * 
	 * @return nome
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * 
	 * @param descricao
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	/**
	 * 
	 * @return descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	
	/**
	 * 
	 * @param numTombamento
	 */
	public void setNumTombamento(String numTombamento) {
		this.numTombamento = numTombamento;
	}
	
	/**
	 * 
	 * @return numTombamento
	 */
	public String getNumTombamento() {
		return numTombamento;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Nome: "+ getNome()+" Descrição: "+getDescricao()
				+" Número do Tombamento: "+getNumTombamento();
	}
	
}