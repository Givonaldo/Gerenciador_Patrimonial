package br.com.controle.modelos;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import com.itextpdf.text.List;

import br.com.controle.dados.Dados;
import br.com.controle.dados.PersistenciaXML;

/**
 * 
 * @author gilvonaldo
 * @version 1.0
 */
public class EnvioDeObjetos implements ModelosIF, Serializable {

	private ArrayList<String> objeto = new ArrayList<String>();
	private Escola escola;
	private String data;
	private String descricao; 
	private PersistenciaXML persistencia;
	private static EnvioDeObjetos envioDeObjetos = null;
	
	public EnvioDeObjetos(){
		
	}
	
	/**
	 * 
	 * @return EnvioDeObjetos
	 */
	public static EnvioDeObjetos getInstance(){
		if (envioDeObjetos == null){
			System.out.println("GetInstance => Retornou um novo objeto.");
			return new EnvioDeObjetos();
		}else if (envioDeObjetos != null){
			System.out.println("GetInstance => Retornou o já existente.");
			return envioDeObjetos;
		}
		return envioDeObjetos;
	}
	
	
	/**
	 * Metodo que adiciona o dia a o mês o ano e a hora que esse envio de 
	 * objeto foi efetuado.
	 * 
	 * @return data
	 */
	@SuppressWarnings("deprecation")
	public void addData(){
		
		SimpleDateFormat formatoDaData = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date dataAtual = new java.util.Date();
		int hora = dataAtual.getHours();
		int minuto = dataAtual.getMinutes();
		int segundo = dataAtual.getSeconds();
		String data = formatoDaData.format(dataAtual)+" Hora: "+hora+":"+minuto+":"+segundo;
		this.data = data;
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
	 * @return
	 */
	public String getDescricao() {
		return descricao;
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
	 */
	public void setObjetos(Objeto objeto){
		
		System.out.println("Objetos passados no parâmetro: "+objeto.toString());
		this.objeto.add(objeto.getNome()+" Nº = "+objeto.getNumTombamento());
		
	}
	
	/**
	 * 
	 * @param escola
	 */
	public void setEscola(Escola escola){
		this.escola = escola;
	}
	
	/**
	 * 
	 * @return escola
	 */
	public Escola getEscola(){
		return this.escola;
	}
	
	/**
	 * 
	 * @return objeto
	 */
	public ArrayList<String> getObjeto(){
		return objeto;
	}
	
	
	@Override
	public String toString() {
		
		return "Escola"+getEscola()+" Objetos:"+getObjeto()+"Data:"+getData();
	}
	
}