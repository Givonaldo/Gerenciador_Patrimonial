package br.com.controle.PDF;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import br.com.controle.dados.Caminho;
import br.com.controle.dados.PersistenciaSerializacao;
import br.com.controle.dados.PersistenciaXML;
import br.com.controle.fabrica.OPCOES;
import br.com.controle.modelos.EnvioDeObjetos;
import br.com.controle.modelos.Escola;
import br.com.controle.modelos.Objeto;
import com.adobe.acrobat.Viewer;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Classe que gera o PDF.
 * 
 * @author gilvonaldo
 * @version 1.0
 */
public class GerarPDF {
	/** The resulting PDF file. */
	public static final String CAMINHO = (System.getProperty("user.dir")
			+ System.getProperty("file.separator") + "Img" + System
			.getProperty("file.separator"));

	/**
	 * Creates a PDF with five tables.
	 * 
	 * @param filename
	 *            the name of the PDF file that will be created.
	 * @throws DocumentException
	 * @throws IOException
	 */
	public void createPdf(String filename, OPCOES opcoes) throws IOException,
			DocumentException {
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream(filename));
		document.open();
		Image imagem = Image.getInstance(CAMINHO + "indice.jpeg");
		document.add(imagem);
		Paragraph paragrafo = new Paragraph(
				"5ª Gerência Regional de Ensino. Monteiro - PB");
		paragrafo.setAlignment(Element.ALIGN_CENTER);
		document.add(paragrafo);
		PdfPTable table = escolhePDF(document, opcoes);
		document.add(table);
		document.close();
		JOptionPane.showMessageDialog(null, "PDF Gerado com sucesso.");
		// Chamada do metodo que exibe o PDF.
		exibirPDF(filename);
	}

	/**
	 * Metodo que retorna uma Tabela de acordo com o parametros passados.
	 *
	 * @param doc
	 * @param opcoes
	 * @return
	 */
	public PdfPTable escolhePDF(Document doc, OPCOES opcoes) {
		try {
			if (opcoes.equals(OPCOES.OBJETO)) {
				return createTableObjetos(doc);
			} else if (opcoes.equals(OPCOES.ESCOLA)) {
				return createTableEscola(doc);
			} else if (opcoes.equals(OPCOES.ENVIO_DE_OBJETOS)) {
				return createTableEnvio(doc);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Metodo que cria a tabela de objetos e adiciona os objetos no arquivo PDF.
	 *
	 * @return a PdfPTable
	 * @throws DocumentException
	 */
	public static PdfPTable createTableObjetos(Document doc)
			throws DocumentException {
		
		Paragraph paragrafo = new Paragraph("Objetos Cadastrados: ");
		paragrafo.setAlignment(Element.ALIGN_CENTER);
		doc.add(paragrafo);
		Paragraph paragrafo1 = new Paragraph(" ");
		doc.add(paragrafo1);
		Objeto objeto = null;
		int cont = 0;
		PersistenciaXML persistencia = PersistenciaXML.getInstance();
		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(500 / 5.23f);
		table.setWidths(new int[] { 1, 1, 1 });
		PdfPCell cell;
		cell = new PdfPCell(new Phrase("Nome: "));
		cell.setColspan(cont);
		table.addCell(cell);
		table.addCell("Num. Tombamento: ");
		table.addCell("Descrição:");
		if (persistencia.load(Caminho.ARQUIVO_UNICO.getTitulo()) != null) {
			for (Object element : persistencia.load(Caminho.ARQUIVO_UNICO
					.getTitulo())) {
				try {
					objeto = (Objeto) element;
					table.addCell(objeto.getNome());
					table.addCell(objeto.getNumTombamento());
					table.addCell(objeto.getDescricao());
				} catch (Exception e) {
				}
			}
		}
		return table;
	}

	/**
	 * Metodo que cria a tabela de Escolas cadastradas e adiciona no arquivo
	 * PDF.
	 *
	 * @return a PdfPTable
	 * @throws DocumentException
	 */
	public static PdfPTable createTableEscola(Document doc)
			throws DocumentException {
		
		Escola objeto = null;
		int cont = 0;
		PersistenciaXML persistencia = PersistenciaXML.getInstance();
		Paragraph paragrafo = new Paragraph("Escolas Cadastradas: ");
		paragrafo.setAlignment(Element.ALIGN_CENTER);
		doc.add(paragrafo);
		Paragraph paragrafo1 = new Paragraph(" ");
		doc.add(paragrafo1);
		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(500 / 5.23f);
		table.setWidths(new int[] { 1, 1, 1 });
		PdfPCell cell;
		cell = new PdfPCell(new Phrase("Nome: "));
		cell.setColspan(cont);
		table.addCell(cell);
		table.addCell("Cidade: ");
		table.addCell("Data de Cadastro:");
		if (persistencia.load(Caminho.ARQUIVO_UNICO.getTitulo()) != null) {
			for (Object element : persistencia.load(Caminho.ARQUIVO_UNICO
					.getTitulo())) {
				try {
					objeto = (Escola) element;
					table.addCell(objeto.getNome());
					table.addCell(objeto.getCidade());
					table.addCell(objeto.getData());
				} catch (Exception e) {
				}
			}
		}
		return table;
	}

	/**
	 * Metodo que cria a tabela de envio de objetos.
	 *
	 * @return a PdfPTable
	 * @throws DocumentException
	 */
	public static PdfPTable createTableEnvio(Document doc)
			throws DocumentException {
		EnvioDeObjetos objeto = null;
		int cont = 0;
		PersistenciaXML persistencia = PersistenciaXML.getInstance();
		Paragraph paragrafo = new Paragraph("Envio de Objetos Realizados: ");
		paragrafo.setAlignment(Element.ALIGN_CENTER);
		doc.add(paragrafo);
		Paragraph paragrafo1 = new Paragraph(" ");
		doc.add(paragrafo1);
		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(500 / 5.23f);
		table.setWidths(new int[] { 1, 1, 1 });
		PdfPCell cell;
		cell = new PdfPCell(new Phrase("Objeto: "));
		cell.setColspan(cont);
		table.addCell(cell);
		table.addCell("Enviado para: ");
		table.addCell("Data do Envio:");
		PersistenciaSerializacao persistenciaSerializacao = PersistenciaSerializacao
				.getInstance();
		EnvioDeObjetos objetoEnvio = null;
		// Interação que adiciona os dados necessários para o preenchimento da
		// tabela de Envio de Objetos.
		if (persistenciaSerializacao.readObject(Caminho.ARQUIVO_ENVIO
				.getTitulo()) != null) {
			for (Object obj : persistenciaSerializacao
					.readObject(Caminho.ARQUIVO_ENVIO.getTitulo())) {
				try {
					objetoEnvio = (EnvioDeObjetos) obj;
					for (String eo : objetoEnvio.getObjeto()) {
						table.addCell(eo.toString());
						table.addCell(objetoEnvio.getEscola().getNome());
						table.addCell(objetoEnvio.getData());
					}
				} catch (Exception e) {
				}
			}
		}
		return table;
	}

	/**
	 * Metodo que exibe o PDF atraves da biblioteca Acrobat do Java beans.
	 *
	 * @param filename
	 */
	public void exibirPDF(String filename) {
		try {
			
			JFrame frame = new JFrame("Documento Gerado");
			frame.setLayout(new BorderLayout());
			Viewer viewer = new Viewer();
			frame.add(viewer, BorderLayout.CENTER);
			FileInputStream input = new FileInputStream(new File(filename));
			viewer.setDocumentInputStream(input);
			viewer.setProperty("Default_Zoom_Type", "FitPage");
			viewer.setProperty("Default_Magnification", "100");
			viewer.zoomTo(2.0);
			viewer.activate();
			frame.setSize(700, 500);
			frame.pack();
			frame.setDefaultCloseOperation(1);
			frame.setVisible(true);
			Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
			frame.setSize(tela.width, tela.height);
			
		} catch (Exception e) {
			// erro
		}
	}
}