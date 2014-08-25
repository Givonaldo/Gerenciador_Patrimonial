package br.com.controle.fabrica;

import br.com.controle.modelos.EnvioDeObjetos;
import br.com.controle.modelos.Escola;
import br.com.controle.modelos.ModelosIF;
import br.com.controle.modelos.Objeto;

public abstract class FabricaModelos{

	public FabricaModelos(){
		
	}
	
	private static ModelosIF modelo;
	
	/**
	 * 
	 * @param objeto
	 * @return modelo
	 */
	public static Object getObject(String objeto) {
		if (objeto.equals(OPCOES.OBJETO.getTitulo())){
			modelo = new Objeto();
		}else if (objeto.equals(OPCOES.ESCOLA.getTitulo())){
			modelo = new Escola();
		}
		return modelo;
	}

}