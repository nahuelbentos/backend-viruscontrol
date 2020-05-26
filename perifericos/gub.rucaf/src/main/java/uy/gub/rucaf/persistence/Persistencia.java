package uy.gub.rucaf.persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import uy.gub.rucaf.entities.PrestadoraSalud;
import uy.gub.rucaf.entities.Usuario;

@Startup
@Singleton
@Local(PersistenciaLocal.class)
public class Persistencia implements PersistenciaLocal {
	// base de datos
	private File filePrestadoras = null;;
	private File fileUsuarios = null;
	
	// colecciones y atributos auxiliares
	private List<PrestadoraSalud> prestadoras;
	
	private FileReader fr = null;
	private BufferedReader br = null;
	private String enter;
	
	public Persistencia() {
		super();
		prestadoras = new ArrayList<PrestadoraSalud>();
		filePrestadoras = new File("prestadoras");
		fileUsuarios = new File("usuarios");
		enter = System.getProperty("line.separator");
		
		try {
//			fileUsuarios.delete();
//			filePrestadoras.delete();
			if (filePrestadoras.createNewFile())
				System.out.println("Se ha creado el archivo prestadoras para persistencia rucaf");
//			else
//				System.out.println("Utilizando el archivo prestadoras ya existente para rucaf");
			
			if (fileUsuarios.createNewFile())
				System.out.println("se ha creado el archivo usuarios para persistencia rucaf");
//			else
//				System.out.println("Utilizando el archivo usuarios ya existente para rucaf");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@PostConstruct
	private void cargarPrestadoras() {
		prestadoras = new ArrayList<PrestadoraSalud>();
		try {
			fr = new FileReader(filePrestadoras);
			br = new BufferedReader(fr);
			
			String registro;
			String[] splitter;
			while ((registro=br.readLine()) != null) {
				splitter = registro.split(Pattern.quote(";"));
				PrestadoraSalud ps = new PrestadoraSalud(Integer.parseInt(splitter[0]), splitter[1]);
				prestadoras.add(ps);
			}

			fr.close();
			
		} catch (IOException e) {
//			e.printStackTrace();
			System.out.println("[uy.gub.rucaf.persistence"+Persistencia.class.getName()+"] ERROR: No se pudo leer el archivo prestadoras");
		}
		
		this.initData();
	}
	
	private void initData() {
		// Agregar prestadoras de salud
		PrestadoraSalud ps = new PrestadoraSalud(1, "SMI - Servicio Medico Integral");
		if (!prestadoras.contains(ps))
			addPrestadora(ps);
		ps = new PrestadoraSalud(2, "Medica Uruguaya");
		if (!prestadoras.contains(ps))
			addPrestadora(ps);
		ps = new PrestadoraSalud(3, "La española");
		if (!prestadoras.contains(ps))
			addPrestadora(ps);
		
		// Agregar usuarios
		Usuario us = new Usuario(11111111, "Jhonny Bravo", prestadoras.get(0));
		if (this.findUsuario(11111111).getDocumento() == 0) {
			addUsuario(us);
		}
		us = new Usuario(22222222, "Jhonny Tolengo", prestadoras.get(1));
		if (this.findUsuario(22222222).getDocumento() == 0) {
			addUsuario(us);
		}
		us = new Usuario(33333333, "Maxi Farcilli", prestadoras.get(1));
		if (this.findUsuario(33333333).getDocumento() == 0) {
			addUsuario(us);
		}
	}

	@Override
	public List<PrestadoraSalud> getPrestadoras() {
		return this.prestadoras;
	}

	@Override
	public boolean addPrestadora(PrestadoraSalud prestadora) {
		if ((prestadora != null) && (!prestadoras.contains(prestadora))) {
			try {
				filePrestadoras = new File("prestadoras");
				FileWriter fw = new FileWriter(filePrestadoras, true);
				fw.write(prestadora.toString()+enter);
				fw.close();
				prestadoras.add(prestadora);
				return prestadoras.contains(prestadora);
			} catch (IOException e) {
				//e.printStackTrace();
				System.out.println("[uy.gub.rucaf.persistence"+Persistencia.class.getName()+"] ERROR: No se pudo escribir en el archivo prestadoras");
				return false;
			}
		} else
			return false;
	}

	/*@Override
	public PrestadoraSalud findPrestadoraUsuario(int documento) {
		return findUsuario(documento).getPrestadora();
	}*/
	
	@Override
	public Usuario findUsuario(int documento) {
		Usuario usu = new Usuario();
		try {
			fr = new FileReader(fileUsuarios);
			br = new BufferedReader(fr);
			
			String registro;
			String[] splitter;
			boolean exit = false;
			while ((!exit) && ((registro=br.readLine()) != null)) {
				splitter = registro.split(Pattern.quote(";"));
				if (Integer.parseInt(splitter[0]) == documento) {
					usu.setDocumento(Integer.parseInt(splitter[0]));
					usu.setNombreCompleto(splitter[1]);
					usu.setPrestadora(prestadoras.get(Integer.parseInt(splitter[2])));
					exit = true;
				}
			}

			fr.close();
			
		} catch (IOException e) {
//			e.printStackTrace();
			System.out.println("[uy.gub.rucaf.persistence"+Persistencia.class.getName()+"] ERROR: No se pudo leer el archivo usuarios");
		}
		return usu;
	}

	@Override
	public boolean addUsuario(Usuario usuario) {
		if ((usuario != null) && (usuario != this.findUsuario(usuario.getDocumento()))) {
			try {
				FileWriter fw = new FileWriter(fileUsuarios, true);
				fw.write(usuario.toString()+enter);
				fw.close();
				return true;
			} catch (IOException e) {
				//e.printStackTrace();
				System.out.println("[uy.gub.rucaf.persistence"+Persistencia.class.getName()+"] ERROR: No se pudo grabar en el archivo usuarios");
				return false;
			}
		} else
			return false;
	}

	@Override
	public boolean updateUsuario(Usuario usuario) {
		try {
			File tempFile = new File("tempUsuarios");
			
			fr = new FileReader(fileUsuarios);
			br = new BufferedReader(fr);
			
			FileWriter fw = new FileWriter(tempFile, true);
			
			String registro;
			String targetStr = "^("+usuario.getDocumento()+";).*$";
			String replaceStr = "";
			while ((registro=br.readLine()) != null) {
				replaceStr = registro.trim();
				if (replaceStr.matches(targetStr)) {
					fw.write(usuario.toString()+enter);
				} else {
					fw.write(registro+enter);
				}
			}
			
			fw.close();
			fr.close();
			return tempFile.renameTo(fileUsuarios);
		} catch (IOException e) {
			//e.printStackTrace();
			System.out.println("[uy.gub.rucaf.persistence"+Persistencia.class.getName()+"] ERROR: No se pudo actualizar el archivo usuarios");
			return false;
		}
	}

	@Override
	public boolean deleteUsuario(int documento) {
		// ESTE METODO NO ESTA TESTEADO
		try {
			File tempFile = new File("tempUsuarios");
			
			fr = new FileReader(fileUsuarios);
			br = new BufferedReader(fr);
			
			FileWriter fw = new FileWriter(tempFile, true);
			
			String registro;
			String targetStr = "^("+documento+";).*$";
			String replaceStr = "";
			while ((registro=br.readLine()) != null) {
				replaceStr = registro.trim();
				if (!replaceStr.matches(targetStr)) 
					fw.write(registro+enter);
			}
			
			fw.close();
			fr.close();
			return tempFile.renameTo(fileUsuarios);
		} catch (IOException e) {
			//e.printStackTrace();
			System.out.println("[uy.gub.rucaf.persistence"+Persistencia.class.getName()+"] ERROR: No se pudo eliminar el usuario del archivo");
			return false;
		}
	}
}
