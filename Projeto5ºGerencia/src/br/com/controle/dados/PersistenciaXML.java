package br.com.controle.dados;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Classe que realiza a persistencia em XML, dos dados 
 * registrados no programa.
 * 
 * @author gilvonaldo
 * @version 1.0
 */
public class PersistenciaXML {

	private static PersistenciaXML armazena = null;
	private XMLDecoder xml;
	private ArrayList<Object> readObject;
	
	public PersistenciaXML(){
		
	}

	/**
	* Metodo que verifica se o objeto Armazena jÃ¡ foi instanciado
	* se nÃ£o ele retorna uma instancia dessa classe.
	* @return
	*/
	public static PersistenciaXML getInstance(){
		if (armazena == null){
			return new PersistenciaXML();
		}
		return armazena;
	}
	
	/**
	* Metodo que recebe como parÃ¢metro o nome do Arquivo XML que
	* deseja salvar e o objeto que vai ser salvo.
	* @param arquivo
	* @param objeto
	*/
	public void salvar(ArrayList objeto, String arquivo){
		
		try {
			FileOutputStream fos = new FileOutputStream(Caminho.CAMINHO.getTitulo()+arquivo);
			BufferedOutputStream buff = new BufferedOutputStream(fos);
			XMLEncoder xml = new XMLEncoder(buff);
			xml.writeObject(objeto);
			xml.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	* Metodo que recebe como parÃ¢metro o nome do arquivo, e serve
	* para recumperar o arquivo passado como parametro.
	* @param arquivo
	* @return obj
	*/
	public ArrayList<Object> load(String arquivo){

		try {
			FileInputStream fos = new FileInputStream(Caminho.CAMINHO.getTitulo()+arquivo);
			BufferedInputStream buff = new BufferedInputStream(fos);
			xml = new XMLDecoder(buff);
			readObject = (ArrayList<Object>) xml.readObject();
			ArrayList<Object> obj = readObject;
			return obj;
		} catch (Exception e) {
			
			return null;
			
		}
		
	}	

}