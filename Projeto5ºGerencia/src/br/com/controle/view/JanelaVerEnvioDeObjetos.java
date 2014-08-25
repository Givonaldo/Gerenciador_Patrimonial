package br.com.controle.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.DocumentException;

import br.com.controle.PDF.ArvoreDeDiretorios;
import br.com.controle.PDF.GerarPDF;
import br.com.controle.dados.Caminho;
import br.com.controle.dados.PersistenciaSerializacao;
import br.com.controle.dados.PersistenciaXML;
import br.com.controle.fabrica.OPCOES;
import br.com.controle.modelos.EnvioDeObjetos;
import br.com.controle.modelos.Escola;
import br.com.controle.modelos.OPCOES_MODELOS;
import br.com.controle.modelos.Objeto;

/**
 * 
 * @author gilvonaldo
 * @version 1.0
 */
public class JanelaVerEnvioDeObjetos extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel modelo;
	private Vector<String> titulo = new Vector<String>();
	private JPanel contentPane;
	private EnvioDeObjetos objetos;
	private final String CAMINHO = (System.getProperty("user.dir")
			+ System.getProperty("file.separator") + "/Img" + System
			.getProperty("file.separator"));
	private ArrayList<String> objeto;

	/**
	 * Metodo construtor da classe.
	 */
	public JanelaVerEnvioDeObjetos(java.awt.Frame parent, boolean modal) {
		super(parent, modal);

		componentes();
	}

	/**
	 * Metodo que seta todos os componentes da janela.
	 */
	public void componentes() {

		setTitle("Envio de Objetos");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 698, 424);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 24, 625, 311);
		contentPane.add(scrollPane);

		setResizable(false);

		titulo.add("Nome do Objeto: ");
		titulo.add("Enviado Para: ");
		titulo.add("Data do Envio: ");

		setLocationRelativeTo(null);
		modelo = new DefaultTableModel(new Vector<Object>(), titulo);
		table = new JTable(modelo);
		scrollPane.setViewportView(table);

		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setIcon(new ImageIcon(CAMINHO + "update.png"));
		btnAtualizar.addActionListener(new ActionListener() {
			/**
			 * ActionPerformed do botão Atualizar.
			 */
			public void actionPerformed(ActionEvent e) {

				atualizar(table);

			}
		});
		btnAtualizar.setBounds(531, 347, 137, 25);
		contentPane.add(btnAtualizar);

		JButton btnRemoverEscola = new JButton("Remover Escola");
		btnRemoverEscola.setIcon(new ImageIcon(CAMINHO + "remove.png"));
		btnRemoverEscola.setBounds(43, 347, 173, 25);
		btnRemoverEscola.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new JanelaRemover(null, true, OPCOES_MODELOS.ENVIO_OBJETOS)
						.setVisible(true);

			}
		});
		contentPane.add(btnRemoverEscola);

		JButton btnNewButton = new JButton("Gerar PDF");
		btnNewButton.setIcon(new ImageIcon(CAMINHO + "pdf.gif"));
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				ArvoreDeDiretorios arvore = new ArvoreDeDiretorios(null, true);
				GerarPDF gerar = new GerarPDF();
				
				if (arvore.getCaminhoSelecionado() == null) {
					dispose();
				} else {
					try {
						String path = (arvore.getCaminhoSelecionado()+
								System.getProperty("file.separator")+"Envio_De_Objetos.pdf");
						gerar.createPdf(path, OPCOES.ENVIO_DE_OBJETOS);
					} catch (Exception erro) {
						erro.printStackTrace();
					}
					dispose();

				}
			}
		});
		btnNewButton.setBounds(284, 347, 166, 25);
		contentPane.add(btnNewButton);

	}

	/**
	 * Metodo que serve para atualizar os dados referentes aos objetos
	 * cadastrados no programa, e exibir esses dados em uma JTable.
	 * 
	 * @param tabela
	 */
	public void atualizar(JTable tabela) {

		PersistenciaSerializacao per = PersistenciaSerializacao.getInstance();
		int cont = 0;

		if (per.readObject(Caminho.ARQUIVO_ENVIO.getTitulo()) != (null)) {

			for (Object esc : per.readObject(Caminho.ARQUIVO_ENVIO.getTitulo())) {

				try {

					this.objetos = (EnvioDeObjetos) esc;

					for (String eo : objetos.getObjeto()) {

						DefaultTableModel modeloTabela = (DefaultTableModel) tabela
								.getModel();
						modeloTabela.addRow(new String[modeloTabela
								.getColumnCount()]);

						tabela.setValueAt(eo.toString(), cont, 0);
						tabela.setValueAt(objetos.getEscola().getNome(), cont,
								1);
						tabela.setValueAt(objetos.getData(), cont, 2);
						cont++;
					}

				} catch (Exception escept) {
				}

			}

		}

	}

	/**
	 * 
	 * @return objeto
	 */
	public ArrayList<String> getObjeto() {
		return objeto;
	}

}