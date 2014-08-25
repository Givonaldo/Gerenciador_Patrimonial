package br.com.controle.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import br.com.controle.modelos.OPCOES_MODELOS;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Classe que representa uma pequena janela com o questionÃ¡rio de 
 * um novo cadastro.
 * @author gilvonaldo
 * @version 1.0
 */
public class JanelaNovoCadastro extends javax.swing.JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Object janela;
	
	//Constante que retorna o caminho relativo do programa.
	private final String CAMINHO = (System.getProperty("user.dir") + System.getProperty("file.separator")
			+ "/Img" + System.getProperty("file.separator"));
	
	
	public JanelaNovoCadastro(java.awt.Frame parent, boolean modal, OPCOES_MODELOS janela) {
		
		super(parent, modal);		
		this.janela = janela;
		
		componentes();
	}
	
	/**
	 * Metodo que seta todos os componentes referentes ao JDialog.
	 */
	public void componentes(){
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 384, 158);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		JButton btnSim = new JButton("Sim");
		btnSim.addActionListener(new ActionListener() {
			//ActionPerformed do botÃ£o sim
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
				escolheJanela(janela);
				dispose();
			}
		});
		btnSim.setBounds(263, 99, 107, 25);
		contentPane.add(btnSim);
		JButton btnNo = new JButton("Não");
		btnNo.addActionListener(new ActionListener() {
			//ActionPerformed do botÃ£o nÃ£o
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNo.setBounds(136, 99, 107, 25);
		contentPane.add(btnNo);
		JLabel lblNewLabel = new JLabel("Deseja realizar outro cadastro ");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel.setBounds(24, 23, 251, 44);
		contentPane.add(lblNewLabel);
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(CAMINHO+"pergunta.gif"));
		lblNewLabel_1.setBounds(298, 20, 50, 47);
		contentPane.add(lblNewLabel_1);
	}
	
	/**
	 * Metodo que verifica qual classe estÃ¡ chamando essa janela e cria 
	 * uma outra janela adequada referente a janela chamadora. Esse metodo 
	 * faz com que essa classe seja generica e possa criar uma nova instancia
	 * adequada a "classe chamadora".
	 * @param janela
	 * @see JanelaCadastrarEscola, JanelaCadastrarObjeto
	 */
	public void escolheJanela(Object janela){
		
		if (janela.equals(OPCOES_MODELOS.JANELA_CADASTRAR_OBJETOS)){
			new JanelaCadastrarObjeto(null, true).setVisible(true);
		}else if (janela.equals(OPCOES_MODELOS.JANELA_CADASTRAR_ESCOLAS)){
			new JanelaCadastrarEscola(null, true).setVisible(true);
		}
	}
	
	
	
	
}