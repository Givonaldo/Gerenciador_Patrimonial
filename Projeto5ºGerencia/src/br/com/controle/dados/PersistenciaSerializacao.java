package br.com.controle.dados;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import br.com.controle.modelos.EnvioDeObjetos;

/**
 * 
 * @author gilvonaldo
 * @version 1.0
 */
public class PersistenciaSerializacao {

	private ArrayList<EnvioDeObjetos> listaDeObjetos;
	private static PersistenciaSerializacao persistencia = null;
	
	private PersistenciaSerializacao(){
		listaDeObjetos = new ArrayList<>();
	}
	
	/**
	 * 
	 * @return PersistenciaSerializacao
	 */
	public static PersistenciaSerializacao getInstance(){
		if (persistencia == null){
			return new PersistenciaSerializacao();
		}else {
			return persistencia;
		}
	}
	
	/**
	 * 
	 * @param arquivo
	 * @param obj
	 */
	public void writeObject(String arquivo, ArrayList<EnvioDeObjetos> obj){
		
		try{
			FileOutputStream fos = new FileOutputStream(arquivo);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(obj);
			fos.close();
			oos.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param arquivo
	 */
	public ArrayList<EnvioDeObjetos> readObject(String arquivo){
		
		try{
			
			FileInputStream fis = new FileInputStream(arquivo);
			ObjectInputStream ois = new ObjectInputStream(fis);
			listaDeObjetos = (ArrayList<EnvioDeObjetos>) ois.readObject();
			
		}catch (Exception e){
			
		}
		return this.listaDeObjetos;
		
	}
	
}