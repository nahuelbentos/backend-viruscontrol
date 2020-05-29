package com.recurso.beans;

import javax.ejb.Local;
import javax.ejb.Stateless;

@Stateless
@Local(PerifericoProveedorRecursoLocal.class)
public class PerifericoProveedorRecurso implements PerifericoProveedorRecursoLocal {
	
	public PerifericoProveedorRecurso() {
		super();
	}

	

}
