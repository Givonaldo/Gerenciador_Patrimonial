package br.com.controle.view;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.print.DocFlavor.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import com.jtattoo.plaf.JTattooUtilities;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.SystemColor;

/**
 * Janela principal do programa, essa Ã© a Janela "MÃ£e"
 * do programa.
 * @author gilvonaldo
 * @version 1.0
 */
public class JanelaPrincipal extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private final String CAMINHO = (System.getProperty("user.dir") + System.getProperty("file.separator")
			+ "/Img" + System.getProperty("file.separator"));
	
	/**
	 * Metodo construtor
	 */
	public JanelaPrincipal() {
		
		super("Gerenciador Patrimonial");
		getContentPane().setBackground(new Color(238, 238, 238));
		lookAndFeel();
		componentes();
				
	}

	/**
	 * 
	 */
	public void componentes(){
		
		Image icon = Toolkit.getDefaultToolkit().getImage(CAMINHO+"icon_frame.png");
		setIconImage(icon);
		
		setBackground(Color.WHITE);
		setSize(761, 455);
		setResizable(false);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(CAMINHO+"brasao.png"));
		label.setBounds(36, 174, 378, 241);
		getContentPane().add(label);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel.setBackground(SystemColor.windowBorder);
		panel.setBounds(0, 0, 761, 71);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnCadastrarObjetos = new JButton("Cadastrar Objetos");
		btnCadastrarObjetos.setBounds(12, 12, 189, 37);
		panel.add(btnCadastrarObjetos);
		
		//exibe uma mensagem de ajuda na hora que passar o ponteiro de mouse encima do botÃ£o.
		btnCadastrarObjetos.setToolTipText("cadastrar um novo objeto");
		
		btnCadastrarObjetos.addActionListener(new ActionListener() {
			//ActionPerformed do botÃ£o de cadastrar objetos.
			public void actionPerformed(ActionEvent e) {
				
				new JanelaCadastrarObjeto(null, true).setVisible(true);
				
			}
		});
		btnCadastrarObjetos.setIcon(new ImageIcon(CAMINHO+"enviar.jpeg"));
		
		JButton btnCadastrarEscola = new JButton("Cadastrar Escola");
		btnCadastrarEscola.setBounds(213, 12, 182, 37);
		panel.add(btnCadastrarEscola);
		
		//exibe uma mensagem de ajuda na hora que passar o ponteiro de mouse encima do botÃ£o.
		btnCadastrarEscola.setToolTipText("cadastrar uma nova escola");
		
		btnCadastrarEscola.addActionListener(new ActionListener() {
			//ActionPerformed do botÃ£o Cadastrar escola
			public void actionPerformed(ActionEvent e) {
				
				new JanelaCadastrarEscola(null, true).setVisible(true);
				
			}
		});
		btnCadastrarEscola.setIcon(new ImageIcon(CAMINHO+"icone-cadastro-curriculo.jpg"));
		
		JButton btnEnviarObjeto = new JButton("Enviar Objeto");
		btnEnviarObjeto.setBounds(407, 12, 168, 37);
		panel.add(btnEnviarObjeto);
		
		//exibe uma mensagem de ajuda na hora que passar o ponteiro de mouse encima do botÃ£o.
		btnEnviarObjeto.setToolTipText("Enviar um objeto já cadastrado");
		
		btnEnviarObjeto.addActionListener(new ActionListener() {
			//ActionPerformed do botÃ£o eviar objetos. 
			public void actionPerformed(ActionEvent e) {
				
				new JanelaEnviarObjeto(null, true).setVisible(true);
				
			}
		});
		btnEnviarObjeto.setIcon(new ImageIcon(CAMINHO+"enviar.jpeg"));
		
		JButton btnVerCadastros = new JButton("Ver Cadastros");
		btnVerCadastros.setBounds(587, 12, 160, 37);
		panel.add(btnVerCadastros);
		
		btnVerCadastros.setToolTipText("Ver dados já cadastrados no programa.");
		
		btnVerCadastros.addActionListener(new ActionListener() {
			//ActionPeformed do botÃ£o ver cadastro.
			public void actionPerformed(ActionEvent e) {
				
				new JanelaVerCadastros(null, true).setVisible(true);
				
			}
		});
		btnVerCadastros.setIcon(new ImageIcon(CAMINHO+"pesquisa.jpeg"));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(-11, 66, 772, 52);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblGernciaRegional = new JLabel("5ª Gerência Regional de Educação ");
		lblGernciaRegional.setBounds(203, 12, 426, 21);
		panel_1.add(lblGernciaRegional);
		lblGernciaRegional.setFont(new Font("Arial Black", Font.BOLD, 17));
		
		setVisible(true);

	}
	
	/**
	 * 
	 */
	public void lookAndFeel(){
			
			try {
				for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
					UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
				}
			} catch (Exception e) {
			    // If Nimbus is not available, you can set the GUI to another look and feel.
			}
	}
}