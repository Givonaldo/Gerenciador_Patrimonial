package br.com.controle.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.GroupLayout.Group;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import br.com.controle.dados.Caminho;
import br.com.controle.dados.PersistenciaXML;
import br.com.controle.fachada.Fachada;
import br.com.controle.modelos.Escola;
import br.com.controle.modelos.OPCOES_MODELOS;
import br.com.controle.modelos.Objeto;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JanelaRemover extends JDialog {

	private JPanel contentPane;
	private ArrayList<Escola> escolas;
	private ArrayList<Objeto> objetos;
	private ArrayList<JRadioButton> myChks;
	private JPanel panel;
	private ArrayList<Object> objetosSelecionados;
	private OPCOES_MODELOS opcoes;


	/**
	 * Create the frame.
	 */
	public JanelaRemover(java.awt.Frame parent, boolean modal, OPCOES_MODELOS opc) {

		super(parent, modal);
		opcoes = opc;
		componentes(opc);

	}

	/**
	 * 
	 */
	public void componentes(OPCOES_MODELOS opc){

		if (opc.equals(OPCOES_MODELOS.ESCOLA)){
			setTitle("Remover Escola.");
		}else if (opc.equals(OPCOES_MODELOS.OBJETO)){
			setTitle("Remover Objeto.");
		}else if (opc.equals(OPCOES_MODELOS.ENVIO_OBJETOS)){
			setTitle("Limpar Envio.");
			limparEnvio();
		}

		escolas = new ArrayList<>();
		objetos = new ArrayList<>();
		myChks = new ArrayList<>();
		objetosSelecionados = new ArrayList<>();

		panel = new JPanel();

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 361, 421);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		panel.setLayout(new GridLayout());

		addComponentes(panel);

		contentPane.setLayout(null);

		setLocationRelativeTo(null);
		setResizable(false);

		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setBounds(37, 12, 285, 325);
		contentPane.add(scrollPane);

		JButton btnNewButton = new JButton("Remover");
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				removerObjeto(opcoes);
				dispose();
			}

		});
		btnNewButton.setBounds(199, 356, 123, 25);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			//
			public void actionPerformed(ActionEvent e) {
				dispose();
			}

		});
		btnNewButton_1.setBounds(37, 356, 127, 25);
		contentPane.add(btnNewButton_1);

	}


	/**
	 * Metodo que adiciona os objetos já cadastrados no 
	 * ArrayList de objetos disponiveis.
	 */
	public void objetosDisponiveis(){


		PersistenciaXML persistencia = PersistenciaXML.getInstance();

		Objeto objetoCadastrado = null;
		if (persistencia.load(Caminho.ARQUIVO_UNICO.getTitulo()) != null){
			for (Object objeto : persistencia.load(Caminho.ARQUIVO_UNICO.getTitulo())) {

				try{

					objetoCadastrado = (Objeto) objeto;  
					objetos.add(objetoCadastrado);

				}catch(Exception e){}
			}
		}
	}

	/**
	 * Metodo que adiciona todas as escolas cadastradas no ArrayList
	 * de escolas.
	 */
	public void escolasDisponiveis(){

		PersistenciaXML persistencia = PersistenciaXML.getInstance();
		Escola escolasCadastradas = null;

		if (persistencia.load(Caminho.ARQUIVO_UNICO.getTitulo()) != null){

			//verifica se a quantidade de elementos no ArrayList de escolas Ã© 0.
			//caso seja serÃ¡ adicionados os elementos, caso nÃ£o, nÃ£o serÃ¡ adicinado
			//evitando assim elementos repetidos.
			if (this.escolas.size() == 0){

				for (Object escola : persistencia.load(Caminho.ARQUIVO_UNICO.getTitulo())){

					try{

						escolasCadastradas = (Escola) escola;
						escolas.add(escolasCadastradas);

					}catch (Exception e){}

				}
			}
		}

	}

	/**
	 * Metodo que adiciona os componentes no JPanel. Ele recebe como parÃ¢metro 
	 * um JPanel que representa o painel onde serÃ¡ adicionado os objetos que 
	 * serÃ£o adicionados em um Box que acontecerar apois a chamada dos metodos: 
	 * <b>printCheckBox()</b> ou <b>printRadioButton()</b> e com isso o Box serÃ¡ 
	 * adicionado ao JPanel passado no parÃ¢metro.
	 *   
	 * @param painel
	 * @param opc
	 */
	public void addComponentes(JPanel painel){

		objetosDisponiveis();
		escolasDisponiveis();

		if (opcoes.equals(OPCOES_MODELOS.OBJETO)){

			for (Objeto objeto : this.objetos) {

				String nome = objeto.getNome()+"   NÂº = "+objeto.getNumTombamento();
				JRadioButton chk1 = new JRadioButton(nome);  
				this.myChks.add(chk1);

			} printComboBoxObjetos(painel);
		} if (opcoes.equals(OPCOES_MODELOS.ESCOLA)){
			for (Escola escola : this.escolas) {

				JRadioButton chk2 = new JRadioButton(escola.getNome()+" - "+escola.getCidade());  
				this.myChks.add(chk2);

			}printComboBoxObjetos(painel);
		}

	}

	/**
	 * Metodo que vai adicionar os combobox em um box que serÃ¡ adicionado
	 * em um JPanel, que foi passado no parametro do metodo.
	 * 
	 * @param painel
	 * @see addComponentes(Jpanel, OPCOES_MODELOS)
	 */
	public void printComboBoxObjetos(JPanel painel){

		ButtonGroup grupo = new ButtonGroup();//Grupo que nÃÂ£o permite que mais de um botÃÂ£o seja selecionado.

		Box box = Box.createVerticalBox();

		for (JRadioButton chb : this.myChks){
			grupo.add(chb);
			box.add(chb);
		}

		box.setVisible(true);
		painel.add(box);

	}


	/**
	 * Metodo que verifica quais checkBox estÃ¡ sendo selecionados. e 
	 * adiciona os objetos selecionados em um ArrayList de objetos 
	 * que serÃ¡ verificado atravÃ©s da posiÃ§Ã£o do array de chackBox.
	 *  
	 */
	public void checkBoxSelecionado(OPCOES_MODELOS opc){

		int cont = 0;

		if (opc.equals(OPCOES_MODELOS.OBJETO)){
			for(int i = 0; i < this.myChks.size(); i++){  
				JRadioButton temp = this.myChks.get(i);  
				if(temp.isSelected()){
					objetosSelecionados.add(objetos.get(i));
				} 
				++cont;
			}
		}else if (opc.equals(OPCOES_MODELOS.ESCOLA)){

			for(int i = 0; i < this.myChks.size(); i++){  
				JRadioButton temp = this.myChks.get(i);  
				if(temp.isSelected()){
					objetosSelecionados.add(escolas.get(i));
				} 
				++cont;
			}
		}
	}

	/**
	 * 
	 */
	public void removerObjeto(OPCOES_MODELOS opc){

		Objeto o = null;
		Escola e = null;

		checkBoxSelecionado(opc);

		Fachada fac = new Fachada();

		if (opc.equals(OPCOES_MODELOS.OBJETO)){		

			for (Object element : this.objetosSelecionados) {

				o = (Objeto) element;
				fac.removeObjeto(o.getNumTombamento());

			}

		}else if (opc.equals(OPCOES_MODELOS.ESCOLA)){

			for (Object element : this.objetosSelecionados) {

				e = (Escola) element;
				fac.removeEscola(e.getNome().toString());

			}

		}
	}

	/**
	 * 
	 */
	public void limparEnvio(){
		Fachada fac = new Fachada();
		fac.limparEnvios();
	}

}