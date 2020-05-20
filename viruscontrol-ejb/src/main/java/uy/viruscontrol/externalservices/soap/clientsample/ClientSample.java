package uy.viruscontrol.externalservices.soap.clientsample;

import uy.viruscontrol.externalservices.soap.*;

public class ClientSample {

	public static void main(String[] args) {
	        System.out.println("***********************");
	        System.out.println("Create Web Service Client...");
	        PerifericoPrestadoraSalud service1 = new PerifericoPrestadoraSalud();
	        System.out.println("Create Web Service...");
	        IWSPerifericoPrestadoraSalud port1 = service1.getWSPerifericoPrestadoraSaludPort();
	        System.out.println("Call Web Service Operation...");
	        System.out.println("Server said: " + port1.obtenerPrestadorDeSalud(Integer.parseInt(args[0])));
	        System.out.println("Server said: " + port1.estaDisponible(Integer.parseInt(args[1])));
	        System.out.println("Server said: " + port1.obtenerMedicoAsignado(Integer.parseInt(args[2])));
	        System.out.println("Server said: " + port1.obtenerMedicos(Integer.parseInt(args[3])));
	        System.out.println("Create Web Service...");
	        IWSPerifericoPrestadoraSalud port2 = service1.getWSPerifericoPrestadoraSaludPort();
	        System.out.println("Call Web Service Operation...");
	        System.out.println("Server said: " + port2.obtenerPrestadorDeSalud(Integer.parseInt(args[4])));
	        System.out.println("Server said: " + port2.estaDisponible(Integer.parseInt(args[5])));
	        System.out.println("Server said: " + port2.obtenerMedicoAsignado(Integer.parseInt(args[6])));
	        System.out.println("Server said: " + port2.obtenerMedicos(Integer.parseInt(args[7])));
	        System.out.println("***********************");
	        System.out.println("Call Over!");
	}
}
