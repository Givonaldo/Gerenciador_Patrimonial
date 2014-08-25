package br.com.controle.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JProgressBar;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import java.awt.Dialog.ModalExclusionType;

/**
 * Janela Splash screen do programa, ela serÃ¡ a primeira
 * janela a ser inicializada, serve como uma introduÃ§Ã£o ao
 * programa e para carregar ou fazer testes de conexÃ£o com 
 * o Banco de Dados.
 * @author gilvonaldo
 * @version 1.0
 */
public class Splash extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JProgressBar progressBar;
	private static JLabel lblNewLabel_1;
	private final String caminho = (System.getProperty("user.dir") + System.getProperty("file.separator")
			+ "/Img" + System.getProperty("file.separator"));

	/**
	 * Metodo Construtor do programa.
	 */
	public Splash() {

		lookAndFeel();
		componentes();

	}

	/**
	 * Metodo que seta todos os componentes do Splash.
	 */
	public void componentes(){

		setType(Type.UTILITY);
		setModalExclusionType(ModalExclusionType.NO_EXCLUDE);
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 570, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setToolTipText("");
		progressBar.setForeground(new Color(0, 0, 0));

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setIcon(new ImageIcon(caminho+"governo.jpg"));

		setLocationRelativeTo(null);
		
		setResizable(false);

		lblNewLabel_1 = new JLabel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(progressBar, GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
										.addContainerGap())
										.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
												.addContainerGap())
												.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
														.addComponent(lblNewLabel)
														.addGap(10))))
				);
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 12, GroupLayout.PREFERRED_SIZE)
						.addContainerGap())
				);

		setVisible(true);

		contentPane.setLayout(gl_contentPane);
		carregamento();

		setVisible(false);

		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {

				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}

			}
		} catch (Exception e) {

		}

		dispose();
	}


	/**
	 * Metodo que realiza o carregamento do ProgressBar e a exibiÃ§Ã£o 
	 * de mensagens.
	 */
	@SuppressWarnings("deprecation")
	public void carregamento(){

		Thread thread = new Thread();
		thread.start();				
		for (int i = 0; i < 101; i++) {
			try {
				Thread.sleep(8);
				
				progressBar.setValue(i);
				textCarregamento(progressBar.getValue());
				Thread.sleep(55);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}			
		thread.stop();

	}

	/**
	 * 
	 */
	public void lookAndFeel(){

		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
			}
		} catch (Exception e) {

		}
	}

	/**
	 * Metodo que exibe as mensagens durante o carregamento 
	 * da barra de progresso.
	 * 
	 * @param value
	 */
	public void textCarregamento(int value){

		if (value <= 10){
			lblNewLabel_1.setText("Executando Inicialização");
		}else if (value >10 && value <= 15){
			lblNewLabel_1.setText("Carregando dados");
		}else if (value > 15 && value <= 20){
			lblNewLabel_1.setText("Verificando Consistencia dos dados");
		}else if (value > 20 && value <= 25){
			lblNewLabel_1.setText("Executando Mecanismos de busca");
		}else if (value > 25 && value <= 30){
			lblNewLabel_1.setText("Verificando Banco de Dados");
		}else if (value > 35 && value <= 40){
			lblNewLabel_1.setText("Procurando por dados");
		}else if (value > 40 && value <= 45){
			lblNewLabel_1.setText("Executando...");
		}else if (value > 45 && value <= 50){
			lblNewLabel_1.setText("Carregando Modulos");
		}else if (value > 50 && value <= 55){
			lblNewLabel_1.setText("Verificando Consistencia dos dados...");
		}else if (value > 55 && value <= 60){
			lblNewLabel_1.setText("Iniciando gatilhos de Inicialização");
		}else if (value > 60 && value <= 65){
			lblNewLabel_1.setText("Dados encontrados com sucesso");
		}else if (value > 65 && value <= 70){
			lblNewLabel_1.setText("Executando... ");
		}else if (value > 70 && value <= 75){
			lblNewLabel_1.setText("Executando Mecanismos de busca");
		}else if (value > 75 && value <= 80){
			lblNewLabel_1.setText("Dados encontrados com sucesso");
		}else if (value > 80 && value <= 90){
			lblNewLabel_1.setText("Executando programa.");
		}

	}

	public static void main(String[] args) {
		
		new Splash();
		new JanelaPrincipal();
		
	}


}