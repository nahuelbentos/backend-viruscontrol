package uy.viruscontrol.bussines;

import java.util.List;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;

import uy.viruscontrol.bussines.interfaces.EnfermedadBeanLocal;
import uy.viruscontrol.model.dao.interfaces.EnfermedadDAOLocal;
import uy.viruscontrol.model.dao.interfaces.RecursoDAOLocal;
import uy.viruscontrol.model.dao.interfaces.RecursoEnfermedadDAOLocal;
import uy.viruscontrol.model.dao.interfaces.SintomaDAOLocal;
import uy.viruscontrol.model.dao.interfaces.TipoEnfermedadDAOLocal;
import uy.viruscontrol.model.entities.Enfermedad;
import uy.viruscontrol.model.entities.Recurso;
import uy.viruscontrol.model.entities.RecursoEnfermedad;
import uy.viruscontrol.model.entities.Sintoma;
import uy.viruscontrol.model.entities.TipoEnfermedad;

/**
 * Session Bean implementation class EnfermedadBean
 */
@Stateful
@LocalBean
public class EnfermedadBean implements EnfermedadBeanLocal {
	
	@EJB EnfermedadDAOLocal daoEnfermedadLocal;
	@EJB RecursoDAOLocal daoRecursoLocal;
	@EJB RecursoEnfermedadDAOLocal daoRecEnfLocal;
	@EJB TipoEnfermedadDAOLocal daoTipoEnfermedadLocal;
	@EJB SintomaDAOLocal daoSintomaLocal;
	
    /**
     * Default constructor. 
     */
    public EnfermedadBean() {
        // TODO Auto-generated constructor stub
    }
    
    
    //Caso de uso altaRecursoRecomendado
    
    //Funcion que da de alta un nuevo Recurso si este no existe
    //a su vez se asocia a una enfermedad si esta existe
    //de lo contrario solo se crea el recurso sin enfermedad
    
    @Override
    public boolean altaRecursoRecomendado(String nombreEnfermedad, String nombreRecurso, boolean recursoTrata, boolean recursoPreviene) {
    	
    	boolean altaOK = false;
    	Recurso r = new Recurso ();
    	Enfermedad e = new Enfermedad();
    	RecursoEnfermedad recEnf = new RecursoEnfermedad();
    	//Si no existe el recurso..
    	if(!daoRecursoLocal.exist(nombreRecurso)){
    		
    		r.setNombre(nombreRecurso);
	    	//Persisto el Recurso
    		
    		daoRecursoLocal.persist(r);
    		
    		altaOK=true;
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
            	//hay que validar si la clave compuesta existe en el sistema
            	daoRecEnfLocal.merge(recursoEnfermedad);
            	altaOK=true;
    		}else {
    			recEnf.setRecursoPreviene(recursoPreviene);
    			recEnf.setRecursoTrata(recursoTrata);
            	daoRecEnfLocal.merge(recEnf);
            	altaOK=true;
    		}
    		
    		
    		altaOK=true;
    		
    	}
    	
    	return altaOK;
    }
    
    
    //Caso de Uso crearEnfermedadInfecciosa
    public boolean crearEnfermedadInfecciosa(String nombreEnfermedad, String nombreTipoEnfermedad, 
    		String nombreAgente, List<Sintoma> sintomas, boolean aprobada) {
    	
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
	        	Enfermedad enfermedad = new Enfermedad(nombreEnfermedad, false, nombreAgente, null, null);
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
    	
    	boolean ok=false;
    	Enfermedad e = daoEnfermedadLocal.findById(idEnfermedad);
    	
    	if(e != null) {
    		e.setAprobada(true);
    		daoEnfermedadLocal.merge(e);
    		ok=true;
    	}
    	return ok;
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
    
}
