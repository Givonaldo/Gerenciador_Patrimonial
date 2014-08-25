package br.com.controle.view;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 * @author gilvonaldo
 * @version 1.0
 */
public class JanelaVerCadastros extends javax.swing.JDialog {

	private static final long serialVersionUID = 1L;
	
	private final String CAMINHO = (System.getProperty("user.dir") + System.getProperty("file.separator")
			+ "/Img" + System.getProperty("file.separator"));
	
	
	public JanelaVerCadastros(java.awt.Frame parent, boolean modal) {
		
		super(parent, modal);
		componentes();
		
	}
	
	/**
	 * Metodo que seta todos os componentes referentes ao JDialog. 
	 */
	public void componentes(){
		
		setTitle("Cadastros");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 667, 369);
		
		setLocationRelativeTo(null);
		setResizable(false);
		
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Objetos Cadastrados");
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				new JanelaObjetosCadastrados(null, true).setVisible(true);
				
			}
		});
		btnNewButton.setBounds(29, 34, 185, 41);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Escolas Cadastradas");
		btnNewButton_1.addActionListener(new ActionListener() {
			/**
			 * 
			 */
			public void actionPerformed(ActionEvent e) {
				new JanelaEscolasCadastradas(null, true).setVisible(true);
				
			}
		});
		btnNewButton_1.setBounds(226, 34, 185, 41);
		getContentPane().add(btnNewButton_1);
		
		JButton btnEnvioDeObjetos = new JButton("Ver Envio de objetos");
		btnEnvioDeObjetos.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
			
				new JanelaVerEnvioDeObjetos(null, true).setVisible(true);
			
			}
		});
		btnEnvioDeObjetos.setBounds(430, 34, 190, 41);
		getContentPane().add(btnEnvioDeObjetos);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(CAMINHO+"lupa.png"));
		lblNewLabel.setBounds(40, 178, 206, 137);
		getContentPane().add(lblNewLabel);
	
		
	}
	
}