package uy.viruscontrol.bussines;

import java.util.List;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;


import uy.viruscontrol.bussines.interfaces.EnfermedadBeanLocal;
import uy.viruscontrol.bussines.interfaces.EnfermedadBeanRemote;
import uy.viruscontrol.model.dao.interfaces.EnfermedadDAOLocal;
import uy.viruscontrol.model.dao.interfaces.RecursoDAOLocal;
import uy.viruscontrol.model.dao.interfaces.RecursoEnfermedadDAOLocal;
import uy.viruscontrol.model.dao.interfaces.SintomaDAOLocal;
import uy.viruscontrol.model.dao.interfaces.TipoEnfermedadDAOLocal;
import uy.viruscontrol.model.dao.interfaces.TipoRecursoDAOLocal;
import uy.viruscontrol.model.entities.Enfermedad;
import uy.viruscontrol.model.entities.Recurso;
import uy.viruscontrol.model.entities.RecursoEnfermedad;
import uy.viruscontrol.model.entities.Sintoma;
import uy.viruscontrol.model.entities.TipoEnfermedad;
import uy.viruscontrol.model.entities.TipoRecurso;

@Stateful
@LocalBean
public class EnfermedadBean implements EnfermedadBeanLocal, EnfermedadBeanRemote {
	
	@EJB EnfermedadDAOLocal daoEnfermedadLocal;
	@EJB RecursoDAOLocal daoRecursoLocal;
	@EJB RecursoEnfermedadDAOLocal daoRecEnfLocal;
	@EJB TipoEnfermedadDAOLocal daoTipoEnfermedadLocal;
	@EJB SintomaDAOLocal daoSintomaLocal;
	@EJB TipoRecursoDAOLocal daoTipoRecursoLocal;
	
    public EnfermedadBean() {
    	super();
    }
    
    
    //Caso de uso altaRecursoRecomendado
    
    //Funcion que da de alta un nuevo Recurso si este no existe
    //a su vez se asocia a una enfermedad si esta existe
    //de lo contrario solo se crea el recurso sin enfermedad
    
    @Override
    public boolean asociarRecursoRecomendado(String nombreEnfermedad, String nombreRecurso, boolean recursoTrata, boolean recursoPreviene) {
    	
    	
    	Recurso r = new Recurso ();
    	Enfermedad e = new Enfermedad();
    	RecursoEnfermedad recEnf = new RecursoEnfermedad();
    	//Si no existe el recurso..
    	if(!daoRecursoLocal.exist(nombreRecurso)){
    		
    		System.out.println("El Recurso no existe");
    		return false;
    	}	
    	
    	//Si existe la enfermedad le asocio el recurso si aun no esta asociado  y viceversa
    	if(daoEnfermedadLocal.exist(nombreEnfermedad)) {
    		
    		//Obtengo el recurso
    		r = daoRecursoLocal.findById(getIdRecursoByName(nombreRecurso));
    		//Obtengo la enfermedad
    		e= daoEnfermedadLocal.findById(getIdEnfermedadByName(nombreEnfermedad));
    		
    		//Verifico si el recurso y la enfermedad ya estan asociados
    		recEnf = daoRecEnfLocal.findById(r, e);
    		//Si no lo estan
    		if(recEnf == null) {
    			//Lo asocio con la enfermedad
        		r.asociarEnfermedad(e);
        		//Actualizo el recurso
        		daoRecursoLocal.persist(r);
        		
        		//Persisto en recurso_enfermedad
            	RecursoEnfermedad recursoEnfermedad = new RecursoEnfermedad(r,daoEnfermedadLocal.findById(getIdEnfermedadByName(nombreEnfermedad)));
            	daoRecEnfLocal.persist(recursoEnfermedad);
            	recursoEnfermedad.setRecursoPreviene(recursoPreviene);
            	recursoEnfermedad.setRecursoTrata(recursoTrata);
            	
            	daoRecEnfLocal.merge(recursoEnfermedad);
            	System.out.println("El Recurso fue asociado a la Enfermedad");
            	return true;
    		}else {
    			System.out.println("El Recurso ya se encuentra asociado a la Enfermedad");
    			return false;
    		}
    		
    	 }else {
 			System.out.println("La enfermedad no existe");
 			return false;
 		}
    
    }
    //Caso de Uso crearEnfermedadInfecciosa
    public boolean crearEnfermedadInfecciosa(String nombreEnfermedad, String nombreTipoEnfermedad, 
    		String nombreAgente, List<Sintoma> sintomas, boolean aprobada, float distanciaContagio) {
    	
    	boolean altaOK=false;
    	
    	//Si la enfermedad NO existe, la creo
    	if(!daoEnfermedadLocal.exist(nombreEnfermedad)) {
	    		
	    		//Si tipoEnfermedad no existe, lo persisto
	    		TipoEnfermedad tipoEnfermedad = new TipoEnfermedad();
	    		tipoEnfermedad.setNombre(nombreTipoEnfermedad);
	    		//tipoEnfermedad = daoTipoEnfermedadLocal.findById(getIdTipoByName(nombreTipoEnfermedad));
	        	if(!daoTipoEnfermedadLocal.exist(nombreTipoEnfermedad)) {
		        		
	        		daoTipoEnfermedadLocal.persist(tipoEnfermedad);
	        	}
	        	
	        	//Si el sintoma no existe, lo persisto
	        	for(Sintoma sintoma : sintomas) {
	        		
	        		if(!daoSintomaLocal.exist(sintoma.getNombre())) {
	        			Sintoma sintomaNuevo = new Sintoma(sintoma.getNombre());
	        			daoSintomaLocal.persist(sintomaNuevo);
	        		}
	        	}
	        	
	        	
	        	//persisto enfermedad sin Sintomas y sin Tipo
	        	Enfermedad enfermedad = new Enfermedad(nombreEnfermedad, false, nombreAgente, null, null,false, distanciaContagio);
	        	daoEnfermedadLocal.persist(enfermedad);
	        	
	        	//Asocio la enfermedad con los sintomas
	        	enfermedad=daoEnfermedadLocal.findById(getIdEnfermedadByName(nombreEnfermedad));
	        	List<Sintoma> sintomasAux = new ArrayList<Sintoma>();
	        	Sintoma s = new Sintoma();
	        	for(Sintoma sintoma : sintomas) {
	        		
	        		s=daoSintomaLocal.findById(getIdSintomaByName(sintoma.getNombre()));
	        		sintomasAux.add(s);
	        	}
	        	
	        	enfermedad.setSintomas(sintomasAux);
	        	daoEnfermedadLocal.merge(enfermedad);
	        	
	        	//Asocio la enfermedad con el Tipo
	        	tipoEnfermedad = daoTipoEnfermedadLocal.findById(getIdTipoByName(nombreTipoEnfermedad));
	        	enfermedad.setTipoEnfermedad(tipoEnfermedad);
	        	daoEnfermedadLocal.merge(enfermedad);
	        	
	        	altaOK=true;
        }else {
    		return altaOK;
    	}
    		
    	return altaOK;
    }
    
    
    //Aprobar Enfermedad Infecciosa
    public boolean aprobarEnfermedadInfecciosa(int idEnfermedad) {
    	
    	
    	Enfermedad e = daoEnfermedadLocal.findById(idEnfermedad);
    	
    	if(e != null) {
    		e.setAprobada(true);
    		daoEnfermedadLocal.merge(e);
    		System.out.println("Enfermedad aprobada");
    		return true;
    	}
    	return false;
    }
    
  //Rechazar Enfermedad Infecciosa
    public boolean rechazarEnfermedadInfecciosa(int idEnfermedad) {
    	
    	
    	Enfermedad e = daoEnfermedadLocal.findById(idEnfermedad);
    	
    	if(e != null) {
    		e.setRechazada(true);
    		daoEnfermedadLocal.merge(e);
    		System.out.println("Enfermedad rechazada");
    		return true;
    	}
    	return false;
    }
    
    
    @Override
    //Agregar un Recurso de un determinado tipo
    public boolean altaRecursoDeUnDeterminadoTipo(String nombre,int idTipoRecurso) {
		
    	
    	
    	if(!daoRecursoLocal.exist(nombre)) {
    		Recurso recurso = new Recurso();
        	recurso.setNombre(nombre);
        	recurso.setTipoRecurso(daoTipoRecursoLocal.findById(idTipoRecurso));
        	daoRecursoLocal.persist(recurso);
        	System.out.println("Recurso creado");
        	
        	return true;
    	}else {
    		System.out.println("El Recurso ya existe");
    		return false;
    	}
    	
    }
    
    //Agregar Tipo de Recurso
    public boolean altaTipoRecurso (String nombre, String descripcion, String codigoPeriferico) {
    	if(!daoTipoRecursoLocal.exist(nombre)) {
    		TipoRecurso tipoRecurso = new TipoRecurso(nombre, descripcion);
    		tipoRecurso.setCodigoPeriferico(codigoPeriferico);
    		daoTipoRecursoLocal.persist(tipoRecurso);
    		return true;
    	}else {
    		System.out.println("El Tipo de Recurso ya existe");
    		return false;
    	}
    }
    
    @Override
    //Obtener recursos de un tipo de recurso
    public List<Recurso> obtenerRecursosPorTipoRecurso(TipoRecurso tipoRecurso) { 
    	
    	return daoRecursoLocal.findRecursoByTipoRecurso(tipoRecurso);
    }
    
    @Override
    //Obtener todos los recursos
    public List<Recurso> obtenerRecursosDisponibles(){
    	return daoRecursoLocal.getAllRecursos();
    	
    }
    
    @Override
    public List<Recurso> obtenerRecursoPorEnfermedad(int idEnfermedad){
    	return daoRecursoLocal.findAllByEnfermedad(idEnfermedad);
    }
  
   
    //*/*/*/*/*/*/*/*/*/AUXILIARES*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/
    
    //Auxiliar que retorna el Id de una enfermedad dado su nombre
    
    public int getIdEnfermedadByName(String nombreEnfermedad) {
    	
    	int id=0;
    	List<Enfermedad> enfermedades = new ArrayList<Enfermedad>(); 
    	enfermedades=daoEnfermedadLocal.findAll();
    	
    	for(Enfermedad enfermedad : enfermedades) {
    		if(enfermedad.getNombre().equals(nombreEnfermedad)) {
    			id=enfermedad.getId();
    			break;
    		}
    	}
    	
    	return id;
    }
    
    
    //Auxiliar que retorna el id de un recurso dado su nombre
    public int getIdRecursoByName(String nombreRecurso) {
    	
			int id = 0;
	    	List<Recurso> recursos = new ArrayList<Recurso>(); 
	    	recursos=daoRecursoLocal.findAll();
	    	
	    	for(Recurso recurso : recursos) {
	    		if(recurso.getNombre().equals(nombreRecurso)) {
	    			id=recurso.getId();
	    			break;
	    		}
	    	}
	    	
	    	return id;
    }
    
  
    
  //Auxiliar que retorna el id de un tipo de enfermedad dado su nombre
    public int getIdTipoByName(String nombreTipoEnfermedad) {
    	
			int id = 0;
	    	List<TipoEnfermedad> tipos = new ArrayList<TipoEnfermedad>(); 
	    	tipos=daoTipoEnfermedadLocal.findAll();
	    	
	    	for(TipoEnfermedad tipo : tipos) {
	    		if(tipo.getNombre().equals(nombreTipoEnfermedad)) {
	    			id=tipo.getid();
	    			break;
	    		}
	    	}
	    	
	    	return id;
    }
    
    @Override
  //Auxiliar que retorna el id de un sintoma dado su nombre
    public int getIdSintomaByName(String nombreSintoma) {
    	
			int id = 0;
	    	List<Sintoma> sintomas = new ArrayList<Sintoma>(); 
	    	sintomas=daoSintomaLocal.findAll();
	    	
	    	for(Sintoma sintoma : sintomas) {
	    		if(sintoma.getNombre().equals(nombreSintoma)) {
	    			id=sintoma.getid();
	    			break;
	    		}
	    	}
	    	
	    	return id;
    }
    
    @Override
    //Auxiliar que retorna el id de un tipo de recurso dado su nombre
    public int getIdTipoRecursoByName(String nombreTipoRecurso) {
    	
			int id = 0;
	    	List<TipoRecurso> tipoRecursos = new ArrayList<TipoRecurso>(); 
	    	tipoRecursos=daoTipoRecursoLocal.findAll();
	    	
	    	for(TipoRecurso tipoRecurso : tipoRecursos) {
	    		if(tipoRecurso.getNombre().equals(nombreTipoRecurso)) {
	    			id=tipoRecurso.getId();
	    			break;
	    		}
	    	}
	    	
	    	return id;
    }
    
    @Override
    public List<Sintoma> obtenerListaSintomas() {
    	List<Sintoma> sintomas = daoSintomaLocal.findAll();
    	return (sintomas != null) ? sintomas : new ArrayList<Sintoma>();
    }
    
    @Override
    public List<Enfermedad> obtenerListaEnfermedadesNoAprobadas() {
    	
    	List<Enfermedad> enfermedadesAuxiliar=new ArrayList<Enfermedad>();
    	List<Enfermedad> enfermedades = daoEnfermedadLocal.findAll();
    	
    	for(Enfermedad enfermedad : enfermedades) {
    		if((!enfermedad.isAprobada()) &&(!enfermedad.isRechazada())) {
    			enfermedadesAuxiliar.add(enfermedad);
    		}
    	}
    	return enfermedadesAuxiliar;
    	
    	
    }
    
    @Override
    public List<TipoRecurso> obtenerTiposDeRecursos(){
    	
    	List<TipoRecurso> tiposDeRecursos = daoTipoRecursoLocal.findAll();
    	return (tiposDeRecursos != null) ? tiposDeRecursos : new ArrayList<TipoRecurso>();
    	
    }
    
    @Override
    public List<Recurso> obtenerRecursos(){
    	
    	List<Recurso> recursos = daoRecursoLocal.findAll();
    	return (recursos != null) ? recursos : new ArrayList<Recurso>();
    	
    }
    
    @Override
    public List<Enfermedad> obtenerEnfermedades(){
    	
    	List<Enfermedad> enfermedades = daoEnfermedadLocal.findAll();
    	return (enfermedades != null) ? enfermedades : new ArrayList<Enfermedad>();
    	
    }
}
