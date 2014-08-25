package br.com.controle.gerencia;

import java.util.ArrayList;
import java.util.Collection;

import br.com.controle.dados.Caminho;
import br.com.controle.dados.Dados;
import br.com.controle.dados.PersistenciaSerializacao;
import br.com.controle.dados.PersistenciaXML;
import br.com.controle.modelos.EnvioDeObjetos;
import br.com.controle.modelos.Escola;
import br.com.controle.modelos.Objeto;

/**
 * 
 * @author gilvonaldo
 * @version 1.0
 */ 
public class GerenciaDados extends GerenciadorAbstrato{

	private EnvioDeObjetos envioDeObjetos;
	private ArrayList<Objeto> objetosEnviados = new ArrayList<>();
	private Dados dados;
	private ArrayList<String> numTombamentos;

	public GerenciaDados(){

		envioDeObjetos = EnvioDeObjetos.getInstance();
		dados = new Dados();
		numTombamentos = new ArrayList<>();
	}

	/**
	 * 
	 * @param objeto
	 * @param escola
	 * @param data
	 */
	public void enviarObjetos(Collection<Objeto> objetos, Escola escola){

		envioDeObjetos.setEscola(escola);
		envioDeObjetos.addData();
		objetosEnviados.addAll(objetos);

		for (Objeto obj : objetosEnviados){
			envioDeObjetos.setObjetos(obj);
		}
		dados.addEnvio(envioDeObjetos);
		removeObjeto(objetos);
	}

	/**
	 * Metodo que realiza a remoção dos objetos que foram enviados 
	 * para as escolas.
	 *  
	 * @param numTombamentos
	 */
	public void removeObjeto(Collection<Objeto> objetos){

		ArrayList<Objeto> object = new ArrayList<>();
		object.addAll(objetos);
		ArrayList<Object> objetosCadastrados = new ArrayList<>();
		PersistenciaXML per = PersistenciaXML.getInstance();
		Objeto objeto = null;
		int index = 0;

		for (Object ob : per.load(Caminho.ARQUIVO_UNICO.getTitulo())) {
			objetosCadastrados.add(ob);
		}
		if (per.load(Caminho.ARQUIVO_UNICO.getTitulo()) != null){
			try{
				for (Object obj : objetosCadastrados) {
					try{
						objeto = (Objeto) obj;
						objetos.add(objeto);
						if (object != null){
							for (Objeto object2 : object) {
								if (objeto.getNumTombamento().equals(object2.getNumTombamento())){
									objetosCadastrados.remove(index);
								}
							}
						}
					}catch (Exception e){}
					++index;
				}
			}catch (Exception g){}
		}
		per.salvar(objetosCadastrados, "Arquivo_Unico.xml");
	}

	/**
	 * 
	 * @param numTombamentos
	 */
	public void setNumTombamentos(ArrayList<String> numTombamentos) {
		this.numTombamentos = numTombamentos;
	}

	/**
	 * 
	 * @return numTombamento
	 */
	public ArrayList<String> getNumTombamentos() {
		return numTombamentos;
	}

	/**
	 * Metodo que subistitui todos os dados salvos no arquivo de 
	 * envio por um arrayList vazio, realizando assim 
	 */
	public void limparEnvios(){

		ArrayList<EnvioDeObjetos> limpar = new ArrayList<>();
		PersistenciaSerializacao per = PersistenciaSerializacao.getInstance();
		per.writeObject(Caminho.ARQUIVO_ENVIO.getTitulo(), limpar);

	}


}