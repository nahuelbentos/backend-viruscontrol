package uy.viruscontrol.bussines;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.xml.bind.DatatypeConverter;

import uy.viruscontrol.bussines.enumerated.TipoUsuario;
import uy.viruscontrol.bussines.interfaces.UsuarioBeanLocal;
import uy.viruscontrol.bussines.interfaces.UsuarioBeanRemote;
import uy.viruscontrol.model.dao.interfaces.AdministradorDAOLocal;
import uy.viruscontrol.model.dao.interfaces.CiudadanoDAOLocal;
import uy.viruscontrol.model.dao.interfaces.GerenteDAOLocal;
import uy.viruscontrol.model.dao.interfaces.MedicoDAOLocal;
import uy.viruscontrol.model.dao.interfaces.UsuarioDAOLocal;
import uy.viruscontrol.model.entities.Administrador;
import uy.viruscontrol.model.entities.Ciudadano;
import uy.viruscontrol.model.entities.Gerente;
import uy.viruscontrol.model.entities.Medico;
import uy.viruscontrol.model.entities.Usuario;

@Stateless
@LocalBean
public class UsuarioBean implements UsuarioBeanRemote, UsuarioBeanLocal {

    public UsuarioBean() {}

    @EJB
    private UsuarioDAOLocal usuarioDAO;
    @EJB
    private CiudadanoDAOLocal ciudadanoDAO;
    @EJB
    private MedicoDAOLocal medicoDAO;
    @EJB
    private AdministradorDAOLocal adminDAO;
    @EJB
    private GerenteDAOLocal gteDAO;
    
	@Override
	public boolean autenticarUsuario(String username, String password) {
		Usuario user = usuarioDAO.findByUsername(username);
		if (user == null)
			return false; // El usuario no existe en la base de datos.
		
		try {
			return (user.getPassword().equals(hashPassword(password)));
		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
			System.out.println("ERROR ["+UsuarioBean.class.getName()+"]: No se pudo obtener el hash MD5 para la password.");
			return false;
		}
	}

	@Override
	public Usuario getUsuario(String username) {
		return usuarioDAO.findByUsername(username);
	}


	@Override
	public boolean registrarPrimerIngreso(Usuario user, TipoUsuario tipoUser) {
		if (usuarioDAO.existUserByUsername(user.getUsername())) {
			return false;
		} else {
			user.setPrimerIngreso(true);
			
			// Si la password es null, el usuario es de redes sociales, por lo que no la guardo. de lo contrario, guardo el hash md5 de la misma
			if (user.getPassword() != null) {
				try {
					user.setPassword(hashPassword(user.getPassword()));
				} catch (NoSuchAlgorithmException e) {
//					e.printStackTrace();
					System.out.println("ERROR ["+UsuarioBean.class.getName()+"]: No se pudo obtener el hash MD5 para la password.");
				}
			}
			
			switch (tipoUser){
				case CIUDADANO:
					ciudadanoDAO.persist((Ciudadano)user);
					break;
				
				case MEDICO:
					medicoDAO.persist((Medico)user);
					break;
				default:
					break;
					
			}
			return user.isPrimerIngreso();
		}
			
	}
	
	@Override
	public boolean registrarUsuario(Usuario user) {
		
		if (!usuarioDAO.existUserByUsername(user.getUsername())) {
			
			try {
				user.setPassword(hashPassword(user.getPassword()));
			} catch (NoSuchAlgorithmException e2) {
				System.out.println("ERROR ["+UsuarioBean.class.getName()+"]: No se pudo obtener el hash MD5 para la password.");
				return false;
			}
			
			try {
				gteDAO.persist((Gerente)user);
			} catch(Exception e) {
				try {
					adminDAO.persist((Administrador)user);
				} catch(Exception e1) {
					e.printStackTrace();
					e1.printStackTrace();
					return false;
				}
			}
			return true;
		}else {
			return false;
		}
	}


	@Override
	public void actualizarDatos(Usuario user) {
		Usuario u = usuarioDAO.findByUsername(user.getUsername());
		user.setIdUsuario(u.getIdUsuario());
		try {
			ciudadanoDAO.merge((Ciudadano)user);
		} catch (Exception e) {
			try {
				medicoDAO.merge((Medico)user);
			} catch (Exception e1) {
				e.printStackTrace();
				e1.printStackTrace();
			}
		}
	}

	/* AUXILIAR */
	private String hashPassword(String clave) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(clave.getBytes());
		byte[] digest = md.digest();
		String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
		
		return myHash;
	}   
	
	 @Override
	    public List<Ciudadano> mostrarCiudadanos(){
		 	List<Ciudadano> ciudadanos = ciudadanoDAO.findAll();
		 	List<Ciudadano> ciudadanosActivos = new ArrayList<Ciudadano>();
		 	
		 	for(Ciudadano c : ciudadanos) 
		 		if(!c.isDeleted())
		 		ciudadanosActivos.add(c);
		 	
		 	return (ciudadanosActivos != null) ? ciudadanosActivos : new ArrayList<Ciudadano>();
		}
	 
	 /* @Override
	    public List<PrestadoraSalud> obtenerPrestadorasSalud(){
	    	
	    	 List<PrestadoraSalud> prestadorasSalud = daoprestadorlocal.findAll();
	    	 List<PrestadoraSalud> prestadorasSaludExamenNoEliminadas = new ArrayList<PrestadoraSalud>();
	    	for(PrestadoraSalud ps : prestadorasSalud)
	    		if(!ps.isDeleted())
	    			prestadorasSaludExamenNoEliminadas.add(ps);
	    	
	    	return (prestadorasSaludExamenNoEliminadas != null) ? prestadorasSaludExamenNoEliminadas : new ArrayList<PrestadoraSalud>();
		 }*/
	 
	 @Override
	    public List<Medico> mostrarMedicos(){
		 	List<Medico> medicos = medicoDAO.findAll();
		 	List<Medico> medicosActivos = new ArrayList<Medico>();
		 	
		 	for(Medico m : medicos) 
		 		if(!m.isDeleted())
		 			medicosActivos.add(m);
		 	
		 	return (medicosActivos != null) ? medicosActivos : new ArrayList<Medico>();
		}
	    
	 
	 @Override
	    public List<Gerente> mostrarGerentes(){
		 	List<Gerente> gerentes = gteDAO.findAll();
		 	List<Gerente> gerentesActivos = new ArrayList<Gerente>();
		 	
		 	for(Gerente g : gerentes) 
		 		if(!g.isDeleted())
		 			gerentesActivos.add(g);
		 	
		 	return (gerentesActivos != null) ? gerentesActivos : new ArrayList<Gerente>();
		}
	 
	 @Override
	    public List<Administrador> mostrarAdministradores(){
		 	List<Administrador> administradores = adminDAO.findAll();
		 	List<Administrador> administradoresActivos = new ArrayList<Administrador>();
		 	
		 	for(Administrador a : administradores) 
		 		if(!a.isDeleted())
		 			administradoresActivos.add(a);
		 	
		 	return (administradoresActivos != null) ? administradoresActivos : new ArrayList<Administrador>();
		}
	 
	 @Override
	 public void editarCiudadano(int ciudadanoId,String nombre,String apellido, String correo,String direccion,String nacionalidad,String userName,Calendar fecha) {
		 
		 Ciudadano c=ciudadanoDAO.findById(ciudadanoId);
		if (c!=null) {
			
		
		 if(!( nombre==null  || nombre.isEmpty() ) ) {
			 c.setNombre(nombre);
		 }
		 if(!( apellido==null  || apellido.isEmpty() ) ) {
			 c.setApellido(apellido);
		 }
		 if(!( correo==null  || correo.isEmpty() ) ) {
			 c.setCorreo(correo);
		 }
		 if(!( direccion==null  || direccion.isEmpty() ) ) {
			 c.setDireccion(direccion);
		 }
		 if(!( nacionalidad==null  || nacionalidad.isEmpty() ) ) {
			 c.setNacionalidad(nacionalidad);
		 }
		 if(!( userName==null  || userName.isEmpty() ) ) {
			 c.setUsername(userName);
		 }
		 if( fecha!=null   ) {
			c.setFechaNacimiento(fecha);
		 }
		 
		 
		 
		 ciudadanoDAO.merge(c);
		}
	 }
	 
	 @Override
	 public void editarMedico(int medicoId,String nombre,String apellido, String correo,String direccion,String nacionalidad,String userName,Calendar fecha) {
		 
		Medico c=medicoDAO.findById(medicoId);
		 
		if (c!=null) {
			
			
			 if(!( nombre==null  || nombre.isEmpty() ) ) {
				 c.setNombre(nombre);
			 }
			 if(!( apellido==null  || apellido.isEmpty() ) ) {
				 c.setApellido(apellido);
			 }
			 if(!( correo==null  || correo.isEmpty() ) ) {
				 c.setCorreo(correo);
			 }
			 if(!( direccion==null  || direccion.isEmpty() ) ) {
				 c.setDireccion(direccion);
			 }
			 if(!( nacionalidad==null  || nacionalidad.isEmpty() ) ) {
				 c.setNacionalidad(nacionalidad);
			 }
			 if(!( userName==null  || userName.isEmpty() ) ) {
				 c.setUsername(userName);
			 }
			 if( fecha!=null   ) {
				c.setFechaNacimiento(fecha);
			 }
			 
			 
			 
			 medicoDAO.merge(c);
			}
		 
	 }
	 
	 @Override
	 public void editarGerente(int gerenteId,String nombre,String apellido, String correo,String direccion,String nacionalidad,String userName,Calendar fecha,String password) {
		 
		 Gerente c=gteDAO.findById(gerenteId);
		 
		 if (c!=null) {
				
				
			 if(!( nombre==null  || nombre.isEmpty() ) ) {
				 c.setNombre(nombre);
			 }
			 if(!( apellido==null  || apellido.isEmpty() ) ) {
				 c.setApellido(apellido);
			 }
			 if(!( correo==null  || correo.isEmpty() ) ) {
				 c.setCorreo(correo);
			 }
			 if(!( direccion==null  || direccion.isEmpty() ) ) {
				 c.setDireccion(direccion);
			 }
			 if(!( nacionalidad==null  || nacionalidad.isEmpty() ) ) {
				 c.setNacionalidad(nacionalidad);
			 }
			 if(!( userName==null  || userName.isEmpty() ) ) {
				 c.setUsername(userName);
			 }
			 if( fecha!=null   ) {
				c.setFechaNacimiento(fecha);
			 }
			 if( password!=null   ) {
					try {
						String hash=hashPassword(password);
						c.setPassword(hash);
					} catch (NoSuchAlgorithmException e) {
						// TODO Auto-generated catch block
						System.out.println("error al hashear password");
						e.printStackTrace();
					}
				 }
			 
			 
			 
			gteDAO.merge(c);
			}
		 

		 
	 }
	 
	 
@Override	 
 public void editarAdmin(int adminId,String nombre,String apellido, String correo,String direccion,String nacionalidad,String userName,Calendar fecha,String password) {
		 
		 Administrador c=adminDAO.findById(adminId);
		 
		 if (c!=null) {
				
				
			 if(!( nombre==null  || nombre.isEmpty() ) ) {
				 c.setNombre(nombre);
			 }
			 if(!( apellido==null  || apellido.isEmpty() ) ) {
				 c.setApellido(apellido);
			 }
			 if(!( correo==null  || correo.isEmpty() ) ) {
				 c.setCorreo(correo);
			 }
			 if(!( direccion==null  || direccion.isEmpty() ) ) {
				 c.setDireccion(direccion);
			 }
			 if(!( nacionalidad==null  || nacionalidad.isEmpty() ) ) {
				 c.setNacionalidad(nacionalidad);
			 }
			 if(!( userName==null  || userName.isEmpty() ) ) {
				 c.setUsername(userName);
			 }
			 if( fecha!=null   ) {
				c.setFechaNacimiento(fecha);
			 }
			 if( password!=null   ) {
					try {
						String hash=hashPassword(password);
						c.setPassword(hash);
					} catch (NoSuchAlgorithmException e) {
						// TODO Auto-generated catch block
						System.out.println("error al hashear password");
						e.printStackTrace();
					}
				 }
			 
			 
			 
		adminDAO.merge(c);
		}
	 
    }


 	
	@Override
	public boolean eliminarUsuario(Usuario user) {
		
		if(user instanceof Administrador) {
			
			Administrador adminAux = new Administrador();
			adminAux=adminDAO.findById(user.getIdUsuario());
			
			if(adminAux != null) {
				adminAux.setDeleted(true);
				adminDAO.merge(adminAux);
				System.out.println("Usuario Administrador eliminado.");
				return true;
			}else {
				System.out.println("Error, el usuario Administrador no existe.");
				return false;
			}
			
		}
		
		if(user instanceof Medico) {
			Medico medicoAux = new Medico();
			medicoAux=medicoDAO.findById(user.getIdUsuario());
				
			if(medicoAux != null) {
				medicoAux.setDeleted(true);
				medicoDAO.merge(medicoAux);
				System.out.println("Usuario Medico eliminado.");
				return true;
			}else {
				System.out.println("Error, el usuario Medico no existe.");
				return false;
			}
		}
			
			
		
		if(user instanceof Gerente) {
		
			Gerente gerenteAux = new Gerente();
			gerenteAux=gteDAO.findById(user.getIdUsuario());
			
			if(gerenteAux != null) {
				gerenteAux.setDeleted(true);
				gteDAO.merge(gerenteAux);
				System.out.println("Usuario Gerente eliminado.");
				return true;
			}else {
				System.out.println("Error, el usuario Gerente no existe.");
				return false;
			}
		}
		
		if(user instanceof Ciudadano) {
			
			Ciudadano ciudadanoAux = new Ciudadano();
			ciudadanoAux=ciudadanoDAO.findById(user.getIdUsuario());
			
			if(ciudadanoAux != null) {
				ciudadanoAux.setDeleted(true);
				ciudadanoDAO.merge(ciudadanoAux);
				System.out.println("Usuario Ciudadano eliminado.");
				return true;
			}else {
				System.out.println("Error, el usuario Ciudadano no existe.");
				return false;
			}
		}
		return false;
		
	}	
		
		
		
		
	
 
}
