package br.com.controle.view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.text.SimpleDateFormat;

import javax.swing.JTextField;
import javax.swing.ImageIcon;

import br.com.controle.fachada.Fachada;
import br.com.controle.modelos.OPCOES_MODELOS;

/**
 * Janela de Cadastro de Objetos
 * @author gilvonaldo
 * @version 1.0
 */
public class JanelaCadastrarEscola extends javax.swing.JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField campoCidade;
	private JTextField campoNome;
	
	private final String CAMINHO = (System.getProperty("user.dir") + System.getProperty("file.separator")
			+ "/Img" + System.getProperty("file.separator"));
	

	/**
	 * Metodo construtor da classe
	 */
	public JanelaCadastrarEscola(java.awt.Frame parent, boolean modal) {
		
		super(parent, modal);
		
		componentes();
	}

	/**
	 * Metodo que seta todos os componentes referentes ao JDialog
	 */
	public void componentes(){

		setTitle("Cadastro de Escola");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 429, 257);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setIcon(new ImageIcon(CAMINHO+"icon_enviar.gif"));
		btnCadastrar.addActionListener(new ActionListener() {

			/**
			 * ActionPerformed do botão cadastrar, da janela de cadastro de escolas, nesse 
			 * trecho de código onde será verificado se os parametros passados nos TextField 
			 * são vazios ou não, caso seja será mostrado uma mensagem de entrada invalida 
			 * na tela atraves de um JOptionPane.
			 */
			public void actionPerformed(ActionEvent e) {
				
				if (campoNome.getText().equals("") || campoCidade.getText().equals("")){
					
					JOptionPane.showMessageDialog(null, "Entrada Invalida \n Tente Novamente");
				
				}else{
					
					cadastrar(campoNome.getText(), campoCidade.getText());
					setVisible(false);
					new JanelaNovoCadastro(null, true, OPCOES_MODELOS.JANELA_CADASTRAR_ESCOLAS).setVisible(true);
					dispose();
				}
				
				
			}
		});
		btnCadastrar.setBounds(283, 194, 130, 25);
		contentPane.add(btnCadastrar);
		
		setResizable(false);
		setLocationRelativeTo(null);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			/**
			 * ActionPerfomed do botão cancelar.
			 */
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(148, 194, 123, 25);
		contentPane.add(btnCancelar);
		
		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 13));
		lblNewLabel.setBounds(36, 41, 117, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblNumTombamento = new JLabel("Cidade:");
		lblNumTombamento.setFont(new Font("Dialog", Font.BOLD, 13));
		lblNumTombamento.setBounds(36, 82, 144, 25);
		contentPane.add(lblNumTombamento);
		
		campoCidade = new JTextField();
		campoCidade.setBounds(171, 79, 215, 28);
		contentPane.add(campoCidade);
		campoCidade.setColumns(10);
		
		campoNome = new JTextField();
		campoNome.setBounds(171, 38, 215, 28);
		contentPane.add(campoNome);
		campoNome.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(CAMINHO+"TextEdit.png"));
		lblNewLabel_1.setBounds(12, 91, 144, 137);
		contentPane.add(lblNewLabel_1);

	}
	
	/**
	 * Metodo que realiza o cadastro de uma escola através do acesso 
	 * a Fachada do programa.
	 * @param campoNome
	 * @param campoCidade
	 * @see Fachada
	 */
	public void cadastrar(String campoNome, String campoCidade){
		
		Fachada fachada = new Fachada();
		fachada.addEscola(campoNome, campoCidade, getData());
		
	}
	
	/**
	 * Metodo que retorna a data de cadastro da escola. 
	 * @return data
	 */
	@SuppressWarnings("deprecation")
	public String getData(){
		
		SimpleDateFormat formatoDaData = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date dataAtual = new java.util.Date();
		int hora = dataAtual.getHours();
		int minuto = dataAtual.getMinutes();
		int segundo = dataAtual.getSeconds();
		String data = formatoDaData.format(dataAtual)+" Hora: "+hora+":"+minuto+":"+segundo;
		return data;
		
	}
	
	
}