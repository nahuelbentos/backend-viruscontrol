package uy.viruscontrol.bussines.interfaces;

import javax.ejb.Local;

@Local
public interface FirmaDigitalLocal {
	public String firmarPdf(String pdfPath);
}
