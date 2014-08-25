package br.com.controle.modelos;

import java.io.Serializable;

/**
 * 
 * @author gilvonaldo
 * @version 1.0
 */
public class Escola implements ModelosIF, Serializable{

	private String nome, cidade, data;
	
	public Escola(){
		
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
	 * @param cidade
	 */
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	/**
	 * 
	 * @return cidade
	 */
	public String getCidade() {
		return cidade;
	}
	
	/**
	 * 
	 * @return data
	 */
	public String getData() {
		return data;
	}
	
	/**
	 * 
	 * @param data
	 */
	public void setData(String data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Escola: "+getNome()+" Cidade: "+getCidade()
				+" Data de Criação: "+getData();
	}
	
}