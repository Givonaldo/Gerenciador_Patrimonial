package br.com.controle.fachada;

import java.util.Collection;
import br.com.controle.fabrica.FabricaGerenciadores;
import br.com.controle.fabrica.OPCOES;
import br.com.controle.gerencia.GerenciaCadastro;
import br.com.controle.gerencia.GerenciaDados;
import br.com.controle.modelos.Escola;
import br.com.controle.modelos.Objeto;

/**
 * Fachada do programa, ela representa um ponto 
 * comum de acesso para a interface grafica.
 * @author gilvonaldo
 * @version 1.0
 */
public class Fachada implements FachadaIF {

	private GerenciaCadastro gerenciaCadastro;
	private GerenciaDados gerenciaDados;
	
	public Fachada(){
		gerenciaCadastro = (GerenciaCadastro) FabricaGerenciadores.getObject(OPCOES.GERENCIA_CADASTRO.getTitulo());
		gerenciaDados = (GerenciaDados) FabricaGerenciadores.getObject(OPCOES.GERENCIA_DADOS.getTitulo());
	}
	
	@Override
	public void addEscola(String nome, String cidade, String data) {
		gerenciaCadastro.addEscola(nome, cidade, data);
	}

	@Override
	public void auteraEscola(String nome, String atributo, String novoAtributo) {
		gerenciaCadastro.auteraEscola(nome, atributo, novoAtributo);
	}

	@Override
	public void removeEscola(String nome) {	
		gerenciaCadastro.removeEscola(nome);
	}

	@Override
	public void addObjeto(String nome, String descricao, String numTombamento) {
		gerenciaCadastro.addObjeto(nome, descricao, numTombamento);
	}

	@Override
	public void auteraObjeto(String numTombamento, String novoAtributo) {	
		gerenciaCadastro.auteraObjeto(numTombamento, novoAtributo);
	}

	@Override
	public void removeObjeto(String numTombamento) {		
		gerenciaCadastro.removeObjeto(numTombamento);
	}

	@Override
	public void enviarObjetos(Collection<Objeto> objeto, Escola escola) {
		gerenciaDados.enviarObjetos(objeto, escola);
		
	}

	@Override
	public void limparEnvios() {
		gerenciaDados.limparEnvios();
	}

}