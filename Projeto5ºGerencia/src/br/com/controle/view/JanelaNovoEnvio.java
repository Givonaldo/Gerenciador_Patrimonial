package br.com.controle.view;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JanelaNovoEnvio extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private final JLabel label;
	private final String caminho = (System.getProperty("user.dir") + System.getProperty("file.separator")
			+ "/Img" + System.getProperty("file.separator"));

	/**
	 * Metodo construtor da classe.
	 */
	public JanelaNovoEnvio(java.awt.Frame parent, boolean modal) {
		
		super(parent, modal);
		
		label = new JLabel("");
		
		componentes();
	}
	
	/**
	 * Metodo que adiciona todos os componentes referentes ao JDialog
	 */
	public void componentes(){
		setBounds(100, 100, 442, 203);
		getContentPane().setLayout(null);
		setTitle("Mensagem");
		JLabel lblDesejaRealizarUm = new JLabel("Deseja Realizar um Novo Envio de Objetos ");
		lblDesejaRealizarUm.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 13));
		setLocationRelativeTo(null);
		lblDesejaRealizarUm.setBounds(12, 56, 309, 41);
		getContentPane().add(lblDesejaRealizarUm);
		label.setBounds(346, 46, 52, 59);
		getContentPane().add(label);
		label.setIcon(new ImageIcon(caminho+"pergunta.gif"));
		
		JButton btnSim = new JButton("Sim");
		btnSim.addActionListener(new ActionListener() {
			//ActionPerformed do botÃ£o sim
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new JanelaEnviarObjeto(null, true).setVisible(true);
				dispose();
			}
		});
		btnSim.setBounds(198, 136, 112, 25);
		getContentPane().add(btnSim);
		
		JButton btnNewButton = new JButton("Não");
		btnNewButton.addActionListener(new ActionListener() {
			//ActionPerformed do botÃ£o nÃ£o
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(318, 136, 112, 25);
		getContentPane().add(btnNewButton);
	}
	
}