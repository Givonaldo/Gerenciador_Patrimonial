package br.com.controle.view;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import br.com.controle.PDF.ArvoreDeDiretorios;
import br.com.controle.PDF.GerarPDF;
import br.com.controle.dados.Caminho;
import br.com.controle.dados.PersistenciaXML;
import br.com.controle.fabrica.OPCOES;
import br.com.controle.modelos.OPCOES_MODELOS;
import br.com.controle.modelos.Objeto;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

/**
 * Classe que exibe uma JTable dentro de uma JDialog com todos os objetos
 * cadastrados no programa, e seus respectivos dados.
 * 
 * @author gilvonaldo
 * @version 1.0
 */
public class JanelaObjetosCadastrados extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTable table;
	private DefaultTableModel modelo;
	private Vector<String> titulo = new Vector<String>();

	private final String CAMINHO_PDF = (System.getProperty("user.dir") + System
			.getProperty("file.separator"));

	private final String CAMINHO = ((System.getProperty("user.dir")
			+ System.getProperty("file.separator") + "/Img" + System
			.getProperty("file.separator")));

	/**
	 * Metodo construtor da classe.
	 */
	public JanelaObjetosCadastrados(java.awt.Frame parent, boolean modal) {

		super(parent, modal);

		componentes();

	}

	/**
	 * Metodo que seta todos os componentes da janela.
	 */
	public void componentes() {

		setTitle("Objetos Cadastrados.");
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

		titulo.add("Nome: ");
		titulo.add("Num. Tombamento: ");
		titulo.add("Descrição: ");

		setLocationRelativeTo(null);
		modelo = new DefaultTableModel(new Vector<Object>(), titulo);
		table = new JTable(modelo);
		scrollPane.setViewportView(table);
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setIcon(new ImageIcon(CAMINHO + "update.png"));
		btnAtualizar.addActionListener(new ActionListener() {
			// ActionPerformed do botÃ£o atualizar.
			public void actionPerformed(ActionEvent e) {

				atualizar(table);

			}
		});
		btnAtualizar.setBounds(531, 357, 137, 25);
		contentPane.add(btnAtualizar);

		JButton btnRemoverObeto = new JButton("Remover Objeto");
		btnRemoverObeto.setIcon(new ImageIcon(CAMINHO + "remove.png"));
		btnRemoverObeto.addActionListener(new ActionListener() {
			// ActionPerformed do botÃ£o remover objetos.
			public void actionPerformed(ActionEvent e) {

				new JanelaRemover(null, true, OPCOES_MODELOS.OBJETO)
						.setVisible(true);

			}
		});
		btnRemoverObeto.setBounds(43, 357, 172, 25);
		contentPane.add(btnRemoverObeto);

		JButton btnGerarPdf = new JButton("Gerar PDF");
		btnGerarPdf.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				ArvoreDeDiretorios arvore = new ArvoreDeDiretorios(null, true);
				GerarPDF gpdf = new GerarPDF();
				String path = (arvore.getCaminhoSelecionado()
						+ System.getProperty("file.separator") + "Objetos_Cadastrados.pdf");
				if (table.equals(null)) {
					atualizar(table);
				}
				try {
					gpdf.createPdf(path, OPCOES.OBJETO);
				} catch (Exception ea) {
					ea.printStackTrace();
				}
				dispose();

			}
		});
		btnGerarPdf.setIcon(new ImageIcon(CAMINHO + "pdf.gif"));
		btnGerarPdf.setBounds(298, 357, 166, 25);
		contentPane.add(btnGerarPdf);
	}

	/**
	 * Metodo que serve para atualizar os dados referentes aos objetos
	 * cadastrados no programa, e exibir esses dados em uma JTable.
	 */
	public void atualizar(JTable tabela) {

		PersistenciaXML per = new PersistenciaXML();
		Objeto o = null;
		int cont = 0;

		if (per.load(Caminho.ARQUIVO_UNICO.getTitulo()) != (null)) {

			for (Object turma : per.load(Caminho.ARQUIVO_UNICO.getTitulo())) {

				if (turma.getClass().equals(Objeto.class)) {
					o = (Objeto) turma;
					DefaultTableModel modeloTabela = (DefaultTableModel) tabela
							.getModel();
					modeloTabela.addRow(new String[modeloTabela
							.getColumnCount()]);
					tabela.setValueAt(o.getNome(), cont, 0);
					tabela.setValueAt(o.getNumTombamento(), cont, 1);
					tabela.setValueAt(o.getDescricao(), cont, 2);
					cont++;
				}

			}

		}

	}
}