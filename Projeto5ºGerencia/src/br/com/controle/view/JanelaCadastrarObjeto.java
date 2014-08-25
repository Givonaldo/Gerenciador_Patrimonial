package br.com.controle.view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.ImageIcon;

import br.com.controle.fachada.Fachada;
import br.com.controle.modelos.OPCOES_MODELOS;

/**
 * Janela de Cadastro de Objetos
 * @author gilvonaldo
 * @version 1.0
 */
public class JanelaCadastrarObjeto extends javax.swing.JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField campoNumTombamento;
	private JTextField campoNome;
	private JTextField campoDescricao;	
	private Fachada fachada;
	
	//Constante que retorna o caminho relativo do programa.
	private final String CAMINHO = (System.getProperty("user.dir") + System.getProperty("file.separator")
			+ "/Img" + System.getProperty("file.separator"));
	

	/**
	 * Metodo construtor da classe
	 */
	public JanelaCadastrarObjeto(java.awt.Frame parent, boolean modal) {
		
		super(parent, modal);		
		componentes();
	}
	
	/**
	 * Metodo que seta todos os componentes referentes ao 
	 * JDialog.
	 */
	public void componentes(){
		
		setTitle("Cadastro de Objetos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 440, 279);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setIcon(new ImageIcon(CAMINHO+"icon_enviar.gif"));
		
		btnCadastrar.addActionListener(new ActionListener() {
			
			/**
			 * ActionPerformed do botÃ£o cadastrar, da janela de cadastro de objetos, nesse 
			 * trecho de cÃ³digo onde serÃ¡ verificado se os parametros passados nos TextField 
			 * sÃ£o vazios ou nÃ£o, caso seja serÃ¡ mostrado uma mensagem de entrada invalida 
			 * na tela atraves de um JOptionPane.
			 */
			public void actionPerformed(ActionEvent e) {
				
				if (campoNome.getText().equals("") || campoNumTombamento.getText().equals("") 
						|| campoDescricao.getText().equals("")){
				
					JOptionPane.showMessageDialog(null, "Entrada Invalida \n Tente Novamente");
				
				}else{
					
					cadastrar(campoNome.getText(), campoDescricao.getText(), campoNumTombamento.getText());
					setVisible(false);
					new JanelaNovoCadastro(null, true, OPCOES_MODELOS.JANELA_CADASTRAR_OBJETOS).setVisible(true);
					dispose();
				}
			}
		});
		
		btnCadastrar.setBounds(283, 213, 133, 25);
		contentPane.add(btnCadastrar);
		
		setResizable(false);
		setLocationRelativeTo(null);
		
		JButton btnCancelar = new JButton("Cancelar");
		
		btnCancelar.addActionListener(new ActionListener() {
			/**
			 * ActionPerformed do botÃ£o cancelar.
			 */
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(154, 213, 117, 25);
		contentPane.add(btnCancelar);
		
		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 13));
		lblNewLabel.setBounds(36, 41, 117, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblNumTombamento = new JLabel("Num. Tombamento:");
		lblNumTombamento.setFont(new Font("Dialog", Font.BOLD, 13));
		lblNumTombamento.setBounds(36, 82, 157, 25);
		contentPane.add(lblNumTombamento);
		
		campoNumTombamento = new JTextField();
		campoNumTombamento.setBounds(198, 75, 215, 29);
		contentPane.add(campoNumTombamento);
		campoNumTombamento.setColumns(10);
		
		campoNome = new JTextField();
		campoNome.setBounds(198, 41, 215, 29);
		contentPane.add(campoNome);
		campoNome.setColumns(10);
		
		JLabel lblDescrio = new JLabel("Descrição: ");
		lblDescrio.setFont(new Font("Dialog", Font.BOLD, 13));
		lblDescrio.setBounds(36, 119, 144, 25);
		contentPane.add(lblDescrio);
		
		campoDescricao = new JTextField();
		campoDescricao.setBounds(198, 122, 218, 63);
		contentPane.add(campoDescricao);
		campoDescricao.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(CAMINHO+"TextEdit.png"));
		lblNewLabel_1.setBounds(12, 119, 144, 137);
		contentPane.add(lblNewLabel_1);
		
	}
	
	/**
	 * Metodo que realiza o cadastro de uma escola atravÃ©s do acesso 
	 * a Fachada do programa.
	 * 
	 * @param nome
	 * @param descricao
	 * @param numTombamento
	 * 
	 * @see Fachada
	 */
	public void cadastrar(String nome, String descricao, String numTombamento){
		
		fachada = new Fachada();
		fachada.addObjeto(nome, descricao, numTombamento);
		
	}
	
}