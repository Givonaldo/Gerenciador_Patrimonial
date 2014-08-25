package br.com.controle.gerencia;

import java.util.ArrayList;

import br.com.controle.dados.Caminho;
import br.com.controle.dados.Dados;
import br.com.controle.dados.PersistenciaXML;
import br.com.controle.fabrica.FabricaModelos;
import br.com.controle.fabrica.OPCOES;
import br.com.controle.modelos.Escola;
import br.com.controle.modelos.Objeto;

/**
 * Classe que realiza o cadastro e as auterações dos 
 * dados.
 * @author gilvonaldo
 * @version 1.0
 * 
 * @see Fabrica, Fachada
 */
public class GerenciaCadastro extends GerenciadorAbstrato {
	
	private Escola escola;
	private Objeto objeto;
	private Dados dados;
	private PersistenciaXML pesquisa;
	private ArrayList<Object> objetos;
	
	public GerenciaCadastro(){
		
		dados = new Dados();
		objetos = new ArrayList<>();
	}
	
	/**
	 * Metodo que cria uma nova escola e atribui os dados 
	 * necessários a ela.
	 * @param nome
	 * @param cidade
	 */
	public void addEscola(String nome, String cidade, String data) {
		
		escola = (Escola) FabricaModelos.getObject(OPCOES.ESCOLA.getTitulo());
		escola.setNome(nome);
		escola.setCidade(cidade);
		escola.setData(data);
		salvaEscola(escola);
	
	}

	/**
	 * Metodo que realiza a auteração de uma escola.
	 * @param nome
	 * @param atributo
	 * @param novoAtributo
	 */
	public void auteraEscola(String nome, String atributo, String novoAtributo) {
		
		escola = pesquisaEscola(nome);
		
		if (atributo.equals(escola.getNome())){
			escola.setNome(novoAtributo);
		}else if (atributo.equals(escola.getCidade())){
			escola.setCidade(novoAtributo);
		}
		salvaEscola(escola);
	}

	/**
	 * Metodo que realiza a remoção de uma escola da lista 
	 * de cadastro.
	 * @param nome
	 */
	public void removeEscola(String nome) {
		
		pesquisa = PersistenciaXML.getInstance();
		objetos.addAll(pesquisa.load(Caminho.ARQUIVO_UNICO.getTitulo()));
		
		Escola e = null;
		
		for (Object obj : this.objetos) {
			try {
				e = (Escola) obj;
				
				if (e.getNome().toString().equals(nome.toString())){
					objetos.remove(e);
					break;
				}
			}catch (Exception erro){}
		}
		
		PersistenciaXML per = new PersistenciaXML();
		per.salvar(this.objetos, Caminho.ARQUIVO_UNICO.getTitulo());
		
	}

	/**
	 * 
	 * @param nome
	 * @param descricao
	 * @param numTombamento
	 */
	public void addObjeto(String nome, String descricao, String numTombamento) {
		
		objeto = (Objeto) FabricaModelos.getObject(OPCOES.OBJETO.getTitulo());
		objeto.setNome(nome);
		objeto.setDescricao(descricao);
		objeto.setNumTombamento(numTombamento);
		salvaObjeto(objeto);
		
	}

	/**
	 * Metodo que realiza a auteração de um Objeto.
	 * @param numTombamento
	 * @param novoAtributo
	 */
	public void auteraObjeto(String numTombamento, String novoAtributo) {
		
		objeto = pesquisarObjeto(numTombamento);
		
	}

	/**
	 * Metodo que realiza a remoção de um objeto através 
	 * do número do tombamento passado no parâmetro do 
	 * método. 
	 * @param numTombamento
	 */
	public void removeObjeto(String numTombamento) {
		
		pesquisa = PersistenciaXML.getInstance();
		
		objetos.addAll(pesquisa.load(Caminho.ARQUIVO_UNICO.getTitulo()));
		
		Dados dados = new Dados();
		
		for (Object objeto : objetos) {
			
			try{
				Objeto obj = (Objeto) objeto;
			
				if (obj.getNumTombamento().toString().equals(numTombamento.toString())){
				
					objetos.remove(obj);
					break;
				}
			}catch (Exception e){}
		}
		
		PersistenciaXML per = new PersistenciaXML();
		
		per.salvar(this.objetos, Caminho.ARQUIVO_UNICO.getTitulo());
		
	}
	
	/**
	 * 
	 * @param nome
	 * @return
	 */
	public Escola pesquisaEscola(String nome){
		return null;
	}
	
	/**
	 * Metodo que realiza o salvamento da escola.
	 * @param escola
	 */
	public void salvaEscola(Escola escola){
		dados.addEscola(escola);
	}
	
	/**
	 * 
	 * @param numTombamento
	 * @return object
	 */
	public Objeto pesquisarObjeto(String numTombamento){
		
		pesquisa = PersistenciaXML.getInstance();
		objetos = pesquisa.load(Caminho.ARQUIVO_UNICO.getTitulo());
		Objeto object = null;
		
		for (Object objeto : objetos) {
			Objeto obj = (Objeto) objeto;
			if (obj.getNumTombamento() == numTombamento){
				object = obj;
				break;
			}
		}
		return object;
	}
	
	/**
	 * Metodo que realiza o salvamento dos Objetos.
	 * @param objeto
	 */
	public void salvaObjeto(Objeto objeto){
		
		dados.addObjeto(objeto);
		
	}
	
}