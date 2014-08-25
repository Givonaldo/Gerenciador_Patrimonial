package br.com.controle.view;

import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.com.controle.PDF.ArvoreDeDiretorios;
import br.com.controle.PDF.GerarPDF;
import br.com.controle.dados.Caminho;
import br.com.controle.dados.PersistenciaXML;
import br.com.controle.fabrica.OPCOES;
import br.com.controle.modelos.Escola;
import br.com.controle.modelos.OPCOES_MODELOS;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.ImageIcon;

import com.itextpdf.text.DocumentException;

/**
 * 
 * @author gilvonaldo
 * @version 1.0
 */
public class JanelaEscolasCadastradas extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private JTable table;
	private DefaultTableModel modelo;
	private Vector<String> titulo = new Vector<String>();
	private JPanel contentPane;
	
	private final String  CAMINHO_PDF = (System.getProperty("user.dir") + System.getProperty("file.separator")
			+ System.getProperty("file.separator"));
	
	private final String CAMINHO = (System.getProperty("user.dir") + System.getProperty("file.separator")
			+ "/Img" + System.getProperty("file.separator"));
	
	
	/**
	 * Metodo construtor da classe.
	 */
	public JanelaEscolasCadastradas(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		componentes();
	}
	

	/**
	 * Metodo que seta todos os componentes da janela.
	 */
	public void componentes(){
		
		setTitle("Escolas Cadastradas.");
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
		titulo.add("Cidade: ");
		titulo.add("Data de Cadastro: ");
		
		setLocationRelativeTo(null);
		modelo = new DefaultTableModel(new Vector<Object>(), titulo);
		table = new JTable(modelo);
		scrollPane.setViewportView(table);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setIcon(new ImageIcon(CAMINHO+"update.png"));
		btnAtualizar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				atualizar(table);
			
			}
		});
		btnAtualizar.setBounds(531, 347, 137, 25);
		contentPane.add(btnAtualizar);
		
		JButton btnRemoverEscola = new JButton("Remover Escola");
		btnRemoverEscola.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				new JanelaRemover(null, true, OPCOES_MODELOS.ESCOLA).setVisible(true);
			}
		});
		btnRemoverEscola.setIcon(new ImageIcon(CAMINHO+"remove.png"));
		btnRemoverEscola.setBounds(43, 347, 173, 25);
		contentPane.add(btnRemoverEscola);
		
		JButton btnNewButton = new JButton("Gerar PDF");
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				GerarPDF pdf = new GerarPDF();
				ArvoreDeDiretorios arvore = new ArvoreDeDiretorios(null, true);
				String path = (arvore.getCaminhoSelecionado()+
						System.getProperty("file.separator")+"Escolas_Cadastradas.pdf");
				
				try {
					pdf.createPdf(path, OPCOES.ESCOLA);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				dispose();
			}
			
		});
		btnNewButton.setIcon(new ImageIcon(CAMINHO+"pdf.gif"));
		btnNewButton.setBounds(284, 347, 166, 25);
		contentPane.add(btnNewButton);
		
	}
	
	
	/**
	 * Metodo que serve para atualizar os dados referentes aos objetos
	 * cadastrados no programa, e exibir esses dados em uma JTable.
	 * @param tabela 
	 */
	public void atualizar(JTable tabela){
		
		PersistenciaXML per = new PersistenciaXML();
		Escola escola = null;
        int cont = 0;
        
        if (per.load(Caminho.ARQUIVO_UNICO.getTitulo()) != (null)){
        
        	for (Object esc : per.load(Caminho.ARQUIVO_UNICO.getTitulo())) {
            	
            	//verifica se o objeto é do tipo escola, e se for realiza o cach
            	if (esc.getClass().equals(Escola.class)){
            		escola = (Escola) esc;
    	            DefaultTableModel modeloTabela = (DefaultTableModel)tabela.getModel();  
    	            modeloTabela.addRow(new String[ modeloTabela.getColumnCount() ]);  
    	            tabela.setValueAt(escola.getNome(),cont, 0);  
    	            tabela.setValueAt(escola.getCidade(),cont, 1);
    	            tabela.setValueAt(escola.getData(), cont, 2);
    	            cont++;
            	}
            	
            }
        	
        }
        
        
	}
}