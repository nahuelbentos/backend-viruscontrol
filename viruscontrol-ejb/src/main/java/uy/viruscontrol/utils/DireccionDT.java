package uy.viruscontrol.utils;

import java.io.Serializable;

public class DireccionDT implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String calle;
	private int numero;
	private String esquina1;
	private String esquina2;
	
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getEsquina1() {
		return esquina1;
	}
	public void setEsquina1(String esquina1) {
		this.esquina1 = esquina1;
	}
	public String getEsquina2() {
		return esquina2;
	}
	public void setEsquina2(String esquina2) {
		this.esquina2 = esquina2;
	}
	public DireccionDT() {
		super();
	}
	
	
}
