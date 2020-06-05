package uy.viruscontrol.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;

import javax.ejb.Local;
import javax.ejb.Stateless;

import uy.viruscontrol.model.entities.Usuario;

@Stateless
@Local(TokensBeanLocal.class)
public class TokensBean implements TokensBeanLocal {
	
	public TokensBean() {
		super();
	}

//	@Override
//	public DtSessionToken getNewToken(Usuario usuario) {
//		Calendar ts = Calendar.getInstance();
//		String plain = ts.get(Calendar.MONTH) +
//						ts.get(Calendar.HOUR_OF_DAY) + 
//						usuario.getUsername() + 
//						ts.get(Calendar.DAY_OF_MONTH) +
//						ts.get(Calendar.MINUTE) + 
//						usuario.getCorreo() + 
//						ts.get(Calendar.YEAR);
//		try {
//			MessageDigest md = MessageDigest.getInstance("SHA3-256");
//			byte[] hashBytes = md.digest(plain.getBytes(StandardCharsets.UTF_8));
//			String token = bytesToHex(hashBytes); // hash utilizando SHA3-256
//			
//			return new DtSessionToken(usuario.getUsername(), token, Calendar.getInstance());
//		} catch (NoSuchAlgorithmException | NullPointerException e) {
//			e.printStackTrace();
//			return new DtSessionToken();
//		}
//	}
	
	@Override
	public String getToken(Usuario usuario) {
		Calendar ts = Calendar.getInstance();
		String plain = ts.get(Calendar.MONTH) +
						//ts.get(Calendar.HOUR_OF_DAY) + 
						usuario.getUsername() + 
						ts.get(Calendar.DAY_OF_MONTH) +
						//ts.get(Calendar.MINUTE) + 
						usuario.getCorreo() + 
						ts.get(Calendar.YEAR);
		try {
			MessageDigest md = MessageDigest.getInstance("SHA3-256");
			byte[] hashBytes = md.digest(plain.getBytes(StandardCharsets.UTF_8));
			String token = bytesToHex(hashBytes); // hash utilizando SHA3-256
			
			return token;
		} catch (NoSuchAlgorithmException | NullPointerException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	/*
	 *  Usé esté método para obtener el String desde el array de bytes, porque 
	 *  no quiero importar una librería para usar una sola función
	 */
	private String bytesToHex(byte[] hashBytes) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < hashBytes.length; i++) {
			String hex = Integer.toHexString(0xff & hashBytes[i]);
			if(hex.length() == 1) 
				hexString.append('0');
			
			hexString.append(hex);
		}
		
		return hexString.toString();
	}

}
