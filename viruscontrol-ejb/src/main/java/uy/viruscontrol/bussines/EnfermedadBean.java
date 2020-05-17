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
import uy.viruscontrol.model.entities.Enfermedad;
import uy.viruscontrol.model.entities.Recurso;
import uy.viruscontrol.model.entities.RecursoEnfermedad;

/**
 * Session Bean implementation class EnfermedadBean
 */
@Stateful
@LocalBean
public class EnfermedadBean implements EnfermedadBeanLocal {
	
	@EJB EnfermedadDAOLocal daoEnfermedadLocal;
	@EJB RecursoDAOLocal daoRecursoLocal;
	@EJB RecursoEnfermedadDAOLocal daoRecEnfLocal;
	
    /**
     * Default constructor. 
     */
    public EnfermedadBean() {
        // TODO Auto-generated constructor stub
    }
    
    
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
    
    //Auxiliar que busca un recurso dado su nombre
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
    
}
