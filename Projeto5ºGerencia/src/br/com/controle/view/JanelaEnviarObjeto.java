package br.com.controle.view;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import br.com.controle.dados.Caminho;
import br.com.controle.dados.PersistenciaXML;
import br.com.controle.fachada.Fachada;
import br.com.controle.fachada.FachadaIF;
import br.com.controle.modelos.Escola;
import br.com.controle.modelos.OPCOES_MODELOS;
import br.com.controle.modelos.Objeto;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

/**
 * Essa classe representa uma Janela de envio de Objetos, essa janela vai conter 
 * dois escrollBar uma contendo todos os objetos cadastrados no programa e a 
 * outra com todas as escolas, isso irá caracterizar um envio de objetos, ou 
 * seja: o usuário deverar marcar todos os objetos desejados para o envio 
 * e marcar também a escola que deseja enviar esses objetos.
 * 
 * @author gilvonaldo
 * @version 1.0
 * @see JanelaVerCadastros
 * 
 */
public class JanelaEnviarObjeto extends JDialog {

	private static final long serialVersionUID = 1L;	
	private static ArrayList<Objeto> objetos;
	private Collection<Objeto> objetosSelecionados;
	private int escolasSelecionadas;
	private Collection<Escola> escolas;
	private Vector<JCheckBox> myChks1;
	private Vector<JRadioButton> myChks;
	private PersistenciaXML persistencia;
	private FachadaIF fachada;
	private final String caminho = (System.getProperty("user.dir") + System.getProperty("file.separator")
			+ "/Img" + System.getProperty("file.separator"));


	/**
	 * Metodo construtor da Classe.
	 */
	public JanelaEnviarObjeto(java.awt.Frame parent, boolean modal) {

		super(parent, modal);

		fachada = new Fachada();

		objetos = new ArrayList<>();
		escolas = new ArrayList<>();
		myChks = new Vector<JRadioButton>();
		myChks1 = new Vector<JCheckBox>();
		objetosSelecionados = new ArrayList<>();

		componentes();

	}

	/**
	 * Metodo que adiciona todos os componentes ao JDialog.
	 */
	public void componentes(){

		setBounds(100, 100, 713, 441);
		setTitle("Envio de Objetos.");

		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout());
		addComponentes(panel1, OPCOES_MODELOS.OBJETO);

		JScrollPane scrollPane = new JScrollPane(panel1);

		scrollPane.setBounds(215, 60, 210, 243);
		getContentPane().add(scrollPane);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout());
		addComponentes(panel, OPCOES_MODELOS.ESCOLA);

		JScrollPane scrollPane_1 = new JScrollPane(panel);
		scrollPane_1.setBounds(485, 60, 198, 243);

		getContentPane().add(scrollPane_1);

		JLabel label = new JLabel("");
		label.setBounds(30, 240, 185, 159);
		label.setIcon(new ImageIcon(caminho+"Cadastro.gif"));
		getContentPane().add(label);

		JButton btnNewButton = new JButton("Enviar");
		btnNewButton.setIcon(new ImageIcon(caminho+"icon_enviar.gif"));
		btnNewButton.addActionListener(new ActionListener() {
			//ActionPerformed do botão enviar objetos.
			public void actionPerformed(ActionEvent e) {

				enviarObjeto();

			}
		});
		btnNewButton.setBounds(525, 367, 158, 32);
		getContentPane().add(btnNewButton);

		JLabel lblObjetosDisponiveis = new JLabel("Objetos Disponiveis.");
		lblObjetosDisponiveis.setFont(new Font("Lato Black", Font.BOLD, 14));
		lblObjetosDisponiveis.setBounds(215, 23, 170, 25);
		getContentPane().add(lblObjetosDisponiveis);

		JLabel lblEscolasDisponveis = new JLabel("Escolas Disponíveis.");
		lblEscolasDisponveis.setFont(new Font("Lato Black", Font.BOLD, 14));
		lblEscolasDisponveis.setBounds(485, 23, 170, 25);
		getContentPane().add(lblEscolasDisponveis);

		JLabel lblQunatidadeDeObjetos = new JLabel("Quantidade de Objetos:\n");
		lblQunatidadeDeObjetos.setBounds(215, 349, 185, 24);
		getContentPane().add(lblQunatidadeDeObjetos);

		JLabel label_1 = new JLabel(getQuantidadeObjetos());
		label_1.setBounds(412, 354, 70, 15);
		getContentPane().add(label_1);
	}

	/**
	 * 
	 * @return quant
	 */
	public String getQuantidadeObjetos(){

		PersistenciaXML per = PersistenciaXML.getInstance();
		int quant = 0;
		Objeto obj = null;

		if (per.load(Caminho.ARQUIVO_UNICO.getTitulo()) != null){
			for (Object element : per.load(Caminho.ARQUIVO_UNICO.getTitulo())) {

				try{
					obj = (Objeto) element;
					quant++;

				}catch(Exception e){}
			}
		}
		String result = quant+"";
		return result;
	}

	/**
	 * Metodo que adiciona os objetos já cadastrados no 
	 * ArrayList de objetos disponiveis.
	 */
	public void objetosDisponiveis(){


		persistencia = PersistenciaXML.getInstance();

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

		persistencia = PersistenciaXML.getInstance();
		Escola escolasCadastradas = null;

		if(persistencia.load(Caminho.ARQUIVO_UNICO.getTitulo()) != null){
			//verifica se a quantidade de elementos no ArrayList de escolas é 0.
			//caso seja será adicionados os elementos, caso não, não será adicinado
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
	 * Metodo que adiciona os componentes no JPanel. Ele recebe como parâmetro 
	 * um JPanel que representa o painel onde será adicionado os objetos que 
	 * serão adicionados em um Box que acontecerar apois a chamada dos metodos: 
	 * <b>printCheckBox()</b> ou <b>printRadioButton()</b> e com isso o Box será 
	 * adicionado ao JPanel passado no parâmetro.
	 *   
	 * @param painel
	 * @param opc
	 */
	public void addComponentes(JPanel painel, OPCOES_MODELOS opc){

		objetosDisponiveis();
		escolasDisponiveis();

		if (opc.equals(OPCOES_MODELOS.OBJETO)){

			for (Objeto objeto : this.objetos) {
				String nome = objeto.getNome()+"   Nº = "+objeto.getNumTombamento();
				JCheckBox chk1 = new JCheckBox(nome);  
				this.myChks1.add(chk1);

			} printComboBoxObjetos(painel);
		} if (opc.equals(OPCOES_MODELOS.ESCOLA)){
			for (Escola escola : this.escolas) {

				JRadioButton chk = new JRadioButton(escola.getNome());  
				this.myChks.add(chk);
			} printRadioButtonEscolas(painel);
		}

	}

	/**
	 * Metodo que vai adicionar os combobox em um box que será adicionado
	 * em um JPanel, que foi passado no parametro do metodo.
	 * 
	 * @param painel
	 * @see addComponentes(Jpanel, OPCOES_MODELOS)
	 */
	public void printComboBoxObjetos(JPanel painel){

		Box box = Box.createVerticalBox();
		for (JCheckBox chb : this.myChks1){
			box.add(chb);
		}

		box.setVisible(true);
		painel.add(box);

	}

	/**
	 * Metodo que verifica quais checkBox está sendo selecionados. e 
	 * adiciona os objetos selecionados em um ArrayList de objetos 
	 * que será verificado através da posição do array de chackBox.
	 *  
	 */
	public void checkBoxSelecionado(){

		int cont = 0;

		for(int i = 0; i < this.myChks1.size(); i++){  
			JCheckBox temp = this.myChks1.get(i);  
			if(temp.isSelected()){
				objetosSelecionados.add(objetos.get(i));
			} 
			++cont;
		}
	}


	/**
	 * Metodo que adiciona os RadioButton em um Grupo de RadioButton 
	 * evitando assim que mais de um sejá selecionado, e adiciona 
	 * esses RadioButton em um Box que será adicionado em um JPanel.
	 * 
	 * @param painel
	 */
	public void printRadioButtonEscolas(JPanel painel){

		ButtonGroup grupo = new ButtonGroup();//Grupo que nÃ£o permite que mais de um botÃ£o seja selecionado.

		Box box = Box.createVerticalBox();

		Set<JRadioButton> lista = new HashSet<>(myChks);

		for (JRadioButton chb : lista){
			grupo.add(chb);//adiciona os JRadioButton em um grupo de botões
			box.add(chb);
		}

		box.setVisible(true);
		painel.add(box);

	}

	/**
	 * Metodo que verifica qual RadioButton está sendo selecionado, e retorna 
	 * a posição do RadioButton que foi selecionado.
	 * 
	 * @return index
	 */
	public int RadioButtonSelecionado(){

		int index = 0;

		for(int i = 0; i < this.myChks.size(); i++){  

			JRadioButton temp = this.myChks.get(i);   
			if(temp.isSelected()){  

				index = i;  
				escolasSelecionadas++;
			} 
		}

		return index;
	}

	/**
	 * Metodo que envia os objetos selecionados para a escola 
	 * selecionada, e faz isso chamando o metodo enviarObjetos 
	 * da fachada e passando como parâmetro um ArrayList de 
	 * Objetos selecionado e uma Escola.
	 * 
	 */
	public void enviarObjeto(){

		Escola esc = null;
		int cont = 0;

		checkBoxSelecionado();
		RadioButtonSelecionado();

		for (Escola escola : this.escolas){

			if (cont == RadioButtonSelecionado()){
				esc = escola;
			}
			++cont;
		}

		if (objetosSelecionados.size() <= 0 || getEscolasSelecionadas() < 1) {

			JOptionPane.showMessageDialog(null, "Dados não enviados !\n Tente Novamente");
			setVisible(false);
			new JanelaEnviarObjeto(null, true).setVisible(true);
			dispose();

		}else {
			setVisible(false);
			fachada.enviarObjetos(objetosSelecionados, esc);

			new JanelaNovoEnvio(null, true).setVisible(true);

			dispose();
		}
	}

	/**
	 * 
	 * @return escolasSelecionadas
	 */
	public Integer getEscolasSelecionadas() {
		return escolasSelecionadas;
	}
	
	
}