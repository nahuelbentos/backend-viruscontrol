package uy.salud.beans;

import java.util.List;
import java.util.Random;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import uy.salud.entities.PrestadoraSalud;
import uy.salud.entities.Usuario;
import uy.salud.persistence.PersistenciaLocal;

@Stateless
@Local(RucafLocal.class)
public class Rucaf implements RucafLocal {
	
	@EJB private PersistenciaLocal persistencia;
	
	public Rucaf() {
		super();
	}

	@Override
	public List<PrestadoraSalud> obtenerPrestadoras() {
		return persistencia.getPrestadoras();
	}
	
	private PrestadoraSalud obtenerPrestadoraRandom() {
		Random rand = new Random();
		// la traigo y no uso la operación en el return para no afectar la performance consultando dos veces
		List<PrestadoraSalud> list = this.obtenerPrestadoras();
		return list.get(rand.nextInt(list.size()));
	}

	@Override
	public PrestadoraSalud obtenerPrestadoraUsuario(int documento) {
		Usuario user = persistencia.findUsuario(documento);
		if(user.getDocumento() == 0) {
			// todos los usuarios deben existir en rucaf, por lo que si no existe lo creo y asigno prestadora random
			user.setDocumento(documento);
			user.setNombreCompleto("");
			user.setPrestadora(this.obtenerPrestadoraRandom());
			persistencia.addUsuario(user);
		}
		
		//System.out.println("Datos del usuario solicitado: "+user.toString()+" - Prestadora: "+user.getPrestadora().toString()); //DEBUG
		return user.getPrestadora();
	}

}
