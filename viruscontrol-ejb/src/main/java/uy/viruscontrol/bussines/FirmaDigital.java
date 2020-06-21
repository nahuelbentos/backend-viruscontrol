package uy.viruscontrol.bussines;

import javax.ejb.Local;
import javax.ejb.Stateless;

import uy.viruscontrol.bussines.interfaces.FirmaDigitalLocal;

@Stateless
@Local(FirmaDigitalLocal.class)
public class FirmaDigital implements FirmaDigitalLocal {

	public FirmaDigital() {
		super();
	}

	@Override
	public void firmar(String pdfPath) {
		
	}

}
