package uy.viruscontrol.bussines.signing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.util.Calendar;

import javax.ejb.Local;
import javax.ejb.Stateless;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.ExternalSigningSupport;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.PDSignature;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.SignatureInterface;

import uy.viruscontrol.bussines.interfaces.FirmaDigitalLocal;

@Stateless
@Local(FirmaDigitalLocal.class)
public class FirmaDigital implements FirmaDigitalLocal {
	private static final String PFX_PATH = "/home/jhonatan/vcuy-certs/certificado.pfx";
	private static final char[] KEYSTORE_PASSWORD = "".toCharArray();
	
	public FirmaDigital() {
		super();
	}

	@Override
	public void firmarPdf(String pdfPath) {
		if (pdfPath != null && !pdfPath.equals("")) {
			File f = new File(pdfPath);
			String signedPdfName = f.getParent()+"/firmados/"+f.getName();
			
			try {
				FileOutputStream os = new FileOutputStream(signedPdfName);
				
				// Abrir un PDF existente
				PDDocument pdDocument = PDDocument.load(f);//new PDDocument();
				
				PDSignature pdSignature = new PDSignature();
				pdSignature.setFilter(PDSignature.FILTER_ADOBE_PPKLITE);
				pdSignature.setSubFilter(PDSignature.SUBFILTER_ADBE_PKCS7_DETACHED);
				pdSignature.setName("VirusControlUY");
				pdSignature.setLocation("Montevideo");
				pdSignature.setReason("Firma de autenticidad del documento");
				pdSignature.setSignDate(Calendar.getInstance());
				
				KeyStore ks = KeyStore.getInstance("pkcs12");
				ks.load(new FileInputStream(PFX_PATH), KEYSTORE_PASSWORD);
				String alias = (String)ks.aliases().nextElement();
				
				pdDocument.addSignature(pdSignature);
				
				ExternalSigningSupport externalSigning = pdDocument.saveIncrementalForExternalSigning(os);
				SignatureInterface sigInt = new MySignature(ks, KEYSTORE_PASSWORD, alias);
				externalSigning.setSignature(sigInt.sign(externalSigning.getContent()));
				
				pdDocument.close();
				System.out.println("PDF firmado y guardado como: "+signedPdfName);
	 		} catch (Exception e) {
				 e.printStackTrace();
//				System.out.println("["+getClass().getCanonicalName()+"] ERROR: No se pudo completar la firma del archivo. Para ver mas información habilitar la traza y replicar el error.");
			}
		}
	}	
}
