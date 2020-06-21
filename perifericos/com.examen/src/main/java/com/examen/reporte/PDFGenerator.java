package com.examen.reporte;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFGenerator {
	
//	private static final Font chapterFont = FontFactory.getFont(FontFactory.HELVETICA, 26, Font.BOLDITALIC);
//	private static final Font paragraphFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL);
//	     
//	private static final Font categoryFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
//	private static final Font subcategoryFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
//	private static final Font blueFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);    
//	private static final Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	
	private Document pdf;
	public Document getPdf() {
		return pdf;
	}
	
	
	/**
	 * Creamos un documento PDF con iText usando diferentes elementos para aprender 
	 * a usar esta librería.
	 * @param pdfNewFile  <code>String</code> 
	 *      Fichero pdf en el que vamos a escribir. 
	 */
	public void createPDF(File pdfNewFile) {
		// Creamos el documento e indicamos el nombre del fichero.
		try {
			this.pdf = new Document();
		    try {
		    	PdfWriter.getInstance(this.pdf, new FileOutputStream(pdfNewFile));
		    } catch (FileNotFoundException fileNotFoundException) {
		    	System.out.println("No such file was found to generate the PDF (No se encontró el fichero para generar el pdf)" + fileNotFoundException);
		    }
		    this.pdf.open();
	
		    System.out.println("Your PDF file has been generated!(¡Se ha generado tu hoja PDF!");
		} catch (DocumentException documentException) {
			System.out.println("The file not exists (Se ha producido un error al generar un documento): " + documentException);
		}
	}
	public void setTitulo(String titulo) {
		this.pdf.addTitle(titulo);
	}
	public void cerrarPDF() {
		this.pdf.close();
	}
	/*	
	// Añadimos los metadatos del PDF
		//document.addTitle("Table export to PDF (Exportamos la tabla a PDF)");
		document.addSubject("Using iText (usando iText)");
		document.addKeywords("Java, PDF, iText");
		document.addAuthor("Código Xules");
		document.addCreator("Código Xules");
	*/
	public void agregarPagina(Chapter page) {	
		// First page
		// Primera página 
		/*
		Chunk chunk = new Chunk("This is the title", chapterFont);
		chunk.setBackground(BaseColor.GRAY);
		// Let's create de first Chapter (Creemos el primer capítulo)
		Chapter chapter = new Chapter(new Paragraph(chunk), 1);
		
		chapter.setNumberDepth(0);
		chapter.add(new Paragraph("This is the paragraph", paragraphFont));
		*/
		try {
			this.pdf.add(page);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
		
}
