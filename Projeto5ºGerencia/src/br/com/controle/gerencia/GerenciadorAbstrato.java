package br.com.controle.gerencia;

public abstract class GerenciadorAbstrato {

	public boolean verificaNuloOuVazio(String string){
		if (string == null || string.equals("")){
			return false;
		}else {
			return true;
		}
	}
	
	
}
