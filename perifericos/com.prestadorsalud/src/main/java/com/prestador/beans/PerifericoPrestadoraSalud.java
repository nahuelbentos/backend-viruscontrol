package com.prestador.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import com.prestador.entities.Ciudadano;
import com.prestador.entities.Medico;
import com.prestador.entities.PrestadoraSalud;
import com.prestador.persistence.CiudadanoDAOLocal;
import com.prestador.persistence.MedicoDAOLocal;
import com.prestador.persistence.PrestadoraSaludDAOLocal;

/*
 * El fin de este driver es emular las respuestas que se necesitan de las prestadoras de salud
 */

@Stateless
@Local(PerifericoPrestadoraSaludLocal.class)
public class PerifericoPrestadoraSalud implements PerifericoPrestadoraSaludLocal {
	
	@EJB
	private PrestadoraSaludDAOLocal daoPrestadoraSalud;
	
	@EJB
	private MedicoDAOLocal daoMedico;
	
	@EJB
	private CiudadanoDAOLocal daoCiudadano;
	
	public PerifericoPrestadoraSalud() {
		super();
	}
	
	@Override
	public List<Medico> obtenerMedicos(int idPrestadora){
		PrestadoraSalud p = daoPrestadoraSalud.findById(idPrestadora);
		if (p != null)
			return daoMedico.findAllByPrestadoraSalud(p);
		else
			return new ArrayList<Medico>();
	}

	@Override
	public boolean estaDisponible(int idPrestadora) {
		PrestadoraSalud p = daoPrestadoraSalud.findById(idPrestadora);
		if (p != null) {
			Random rand = new Random();
			int n = rand.nextInt(2);
			return !(n == 0);
		} else
			return false;
	}

	@Override
	public int obtenerMedicoAsignado(int idPrestadora) {
		List<Medico> meds = this.obtenerMedicos(idPrestadora);
		if (meds.size() > 0) {
			// devuelvo un médico random
			Random rand = new Random();
			return meds.get(rand.nextInt(meds.size())).getIdUsuario();
		} else
			return 0;
	}

	@Override
	public PrestadoraSalud obtenerPrestadorDeSalud(int idUsuario) {
		Ciudadano user = daoCiudadano.findById(idUsuario);
		if (user != null) {
			if (user.getPrestadoraSalud() != null)
				return user.getPrestadoraSalud();
			else {
				// si el usuario no tiene una prestadora de salud, devuelvo una random
				List<PrestadoraSalud> ps = daoPrestadoraSalud.findAll();
				Random rand = new Random();
				if (ps != null)
					return ps.get(rand.nextInt(ps.size()));
				else
					return null;
			}	
		} else
			return null;
	}

	@Override
	public PrestadoraSalud obtenerPrestadorDeSaludAlternativo(int idUsuario) {
		Ciudadano user = daoCiudadano.findById(idUsuario);
		if (user != null) {
			// El método esta disponible devolvió false, por lo que este metodo emula conseguir otra disponible
			List<PrestadoraSalud> ps = daoPrestadoraSalud.findAll();
			Random rand = new Random();
			if (ps != null) {
				return ps.get(rand.nextInt(ps.size()));
			}
			else
				return null;	
		} else
			return null;
	}
}
