package com.examen.beans;

import java.io.File;
import java.util.List;
import java.util.Random;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import com.examen.persistence.CasoDAOLocal;
import com.examen.persistence.EnfermedadDAOLocal;
import com.examen.persistence.ExamenDAOLocal;
import com.examen.persistence.ProveedorExamenDAOLocal;
import com.examen.reporte.PDFGenerator;
import com.examen.utils.ResultadoExamen;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.examen.entities.Caso;
import com.examen.entities.Enfermedad;
import com.examen.entities.EstadoExamen;
import com.examen.entities.Examen;
import com.examen.entities.ProveedorExamen;

@Stateless
@Local(PerifericoProveedorExamenLocal.class)
public class PerifericoProveedorExamen implements PerifericoProveedorExamenLocal {
	
	@EJB private EnfermedadDAOLocal daoEnfermedad;
	@EJB private ExamenDAOLocal daoExamen;
	@EJB private ProveedorExamenDAOLocal daoProvEx;
	@EJB private CasoDAOLocal daoCaso;
	
	public PerifericoProveedorExamen() {
		super();
	}

	@Override
	public ProveedorExamen find(int idEnfermedad) {
		List<ProveedorExamen> pe = daoProvEx.findAllByEnfermedadId(idEnfermedad);
		Random rand = new Random();
		if ((pe != null) && pe.size() > 0)
			return pe.get(rand.nextInt(pe.size()));
		else
			return new ProveedorExamen();
	}
	
	@Override
	public List<ProveedorExamen> findAllByEnfermedad(int idEnfermedad) {
		return daoProvEx.findAllByEnfermedadId(idEnfermedad);
	}
	
	@Override
	public List<Examen> listaExamenesParaEnfermedad(int idEnfermedad) {
		return daoExamen.findAllByEnfermedad(idEnfermedad);
	}

	@Override
	public Examen solicitarAltaExamen(int idPaciente, int idExamen, int idMedico) {
		// como se está emulando ser un proveedor de examen, devuelvo una instancia del examen solicitado, pero no la persisto
		Examen ex = daoExamen.findById(idExamen);
		if (ex != null)
			return ex;
		else
			return new Examen(new Enfermedad());
	}

	@Override
	public ResultadoExamen resultadoExamen(int idCaso) {
		// emulo estar dando el resultado de un examen desde un prestador. En nuestra estructura, para devolver esa info devuelvo el estado del caso
		Random rand = new Random();
		Caso caso = daoCaso.findById(idCaso);
		ResultadoExamen ret = new ResultadoExamen();
		String path;
		if (caso != null) {
			switch (caso.getTipoCaso()) {
				case SOSPECHOSO:
					EstadoExamen resultado = EstadoExamen.values()[rand.nextInt(EstadoExamen.values().length)];		
					path = generarReporte(caso,resultado);
					ret.setResultado(resultado);
					ret.setPathPdf(path);
					break;		
				case DESCARTADO:
					ret.setResultado(EstadoExamen.NEGATIVO);
					path = generarReporte(caso,EstadoExamen.NEGATIVO);
					ret.setPathPdf(path);
					break;
				default: // los casos CONFIRMADO y RECUPERADO corresponden a casos cuyo examen ha dado positivo, por ahora incluyo también en el default EXPOSICION, hay que ver si se necesita moverlo
					ret.setResultado(EstadoExamen.POSITIVO);
					path = generarReporte(caso,EstadoExamen.POSITIVO);
					ret.setPathPdf(path);
					break;
			}
		} else {
			ret.setPathPdf(null);
			ret.setResultado(EstadoExamen.PENDIENTE);
		}
		return ret;
	}
	
	private static final String pathPdfExamen = "/tmp/reportes/";
	private static final Font chapterFont = FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLDITALIC);
	private static final Font paragraphFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL);
	private String generarReporte(Caso caso, EstadoExamen resultado ) {
		
		Examen ex = caso.getExamen();
		
		PDFGenerator pdf = new PDFGenerator();
		String pathFile = pathPdfExamen + "resultado_" + caso.getId() + ".pdf";
		
		pdf.createPDF(new File(pathFile));
		
		pdf.setTitulo("Resultado examen " + ex.getNombre());
	
		Chunk chunk = new Chunk("Resultado de examen " + ex.getNombre(), chapterFont);
		Chapter page = new Chapter(new Paragraph(chunk), 1);
		
		page.setNumberDepth(0);
		page.add(new Paragraph("Examen para detectar confirmación de posible " + caso.getEnfermedad().getNombre(), paragraphFont));
		page.add(new Paragraph("El resultado del examen es: " + resultado, paragraphFont));
		pdf.agregarPagina(page);
		
		pdf.cerrarPDF();
		
		return pathFile;
	}
	
	

}
