package uy.salud.services.soap.rucaf.clientsample;

import uy.salud.services.soap.rucaf.*;

public class ClientSample {

	public static void main(String[] args) {
	        System.out.println("***********************");
	        System.out.println("Create Web Service Client...");
	        RucafService service1 = new RucafService();
	        System.out.println("Create Web Service...");
	        IRucafService port1 = service1.getRucafServicePort();
	        System.out.println("Call Web Service Operation...");
	        System.out.println("Server said: " + port1.obtenerPrestadoraUsuario(Integer.parseInt(args[0])));
	        System.out.println("Server said: " + port1.obtenerPrestadoras());
	        System.out.println("Create Web Service...");
	        IRucafService port2 = service1.getRucafServicePort();
	        System.out.println("Call Web Service Operation...");
	        System.out.println("Server said: " + port2.obtenerPrestadoraUsuario(Integer.parseInt(args[1])));
	        System.out.println("Server said: " + port2.obtenerPrestadoras());
	        System.out.println("***********************");
	        System.out.println("Call Over!");
	}
}
