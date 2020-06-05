package com.recurso.model;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.opencsv.CSVReader;
import com.recurso.model.entities.DummyProveedor;
import com.recurso.model.entities.DummyRecursoDisponible;
import com.recurso.model.entities.Proveedor;
import com.recurso.model.entities.ProveedorRecurso;
import com.recurso.model.entities.Recurso;
import com.recurso.model.entities.TipoProveedor;
import com.recurso.model.entities.TipoRecurso;

@Singleton
@LocalBean
@Startup
public class HandlerModel {

    public HandlerModel() {}
    
    private Map<String,TipoProveedor> tiposProveedor;
    private Map<String,Proveedor> proveedores;
    
    private Map<String,TipoRecurso> tiposRecursos;
    private Map<String,Recurso> recursos;
    
    
    @PostConstruct
    public void init() {
    	
    	iniciarTiposProveedor();
    	iniciarTiposRecursos();
    	iniciarRecursos();
    	iniciarProveedores();
    	
    	
    }
    
    public List<DummyRecursoDisponible> getRecursosDisponiblesPorProveedor(String codigoProveedor){
    	
    	Proveedor p = proveedores.get(codigoProveedor);
		
    	List<DummyRecursoDisponible> drrdd = new ArrayList<DummyRecursoDisponible>();
    	for (ProveedorRecurso pr : p.getRecursosDisponibles()) {
    		DummyRecursoDisponible drd = new DummyRecursoDisponible();
    		
    		drd.setCantidadDisponible(pr.getCantidadDisponible());
    		drd.setPrecio(pr.getPrecio());
    		drd.setRecurso(pr.getRecurso());
    		drrdd.add(drd);
		}
    	return drrdd;
    }
    
    public List<DummyRecursoDisponible> getRecursosDisponiblesPorCiudadBarrio(String ciudad, String barrio){
    	List<DummyRecursoDisponible> drrdd = new ArrayList<DummyRecursoDisponible>();
    	boolean fullCheck = false;
    	
	    for (Entry<String, Proveedor> it : proveedores.entrySet()) {
	    	fullCheck = (!ciudad.equals("") && !ciudad.equals("0") && !ciudad.equals("-") && !barrio.equals("") && !barrio.equals("0") && !barrio.equals("-"));
	    	
	    	if ((!fullCheck && ciudad.equals(it.getValue().getCiudad()) || barrio.equals(it.getValue().getBarrio())) ||
	    		 (fullCheck && ciudad.equals(it.getValue().getCiudad()) && barrio.equals(it.getValue().getBarrio()))) {
		    	for (ProveedorRecurso pr : it.getValue().getRecursosDisponibles()) {
		    		DummyRecursoDisponible drd = new DummyRecursoDisponible();
		    		
		    		drd.setCantidadDisponible(pr.getCantidadDisponible());
		    		drd.setPrecio(pr.getPrecio());
		    		drd.setRecurso(pr.getRecurso());
		    		if (!drrdd.contains(drd))
		    			drrdd.add(drd);
				}
	    	}
    	}
    	return drrdd;
    }
    
    public List<DummyProveedor> getProveedores(){
		List<Proveedor> proveedores = new ArrayList<Proveedor>();
		
		this.proveedores.forEach((k,v) -> proveedores.add(v));
		
		List<DummyProveedor> dummies = new ArrayList<DummyProveedor>();
		for (Proveedor proveedor : proveedores) {
			DummyProveedor dp = new DummyProveedor();
			dp.setCodigo(proveedor.getCodigo());
			dp.setNombre(proveedor.getNombre());
			dp.setBarrio(proveedor.getBarrio());
			dp.setDireccion(proveedor.getDireccion());
			dp.setHorarioAtencion(proveedor.getHorarioAtencion());
			dp.setCiudad(proveedor.getCiudad());
			dummies.add(dp);
		}
		
		return dummies;
    }
    
    public List<DummyProveedor> getProveedoresDeTipo(String codigoTipo){
		List<Proveedor> proveedores = new ArrayList<Proveedor>();
		
		this.proveedores.forEach((k,v) -> proveedores.add(v));
		
		List<DummyProveedor> dummies = new ArrayList<DummyProveedor>();
		for (Proveedor proveedor : proveedores) {
			
			if (proveedor.getTipoProveedor().equals(codigoTipo)) {
				DummyProveedor dp = new DummyProveedor();
				dp.setCodigo(proveedor.getCodigo());
				dp.setNombre(proveedor.getNombre());
				dp.setBarrio(proveedor.getBarrio());
				dp.setDireccion(proveedor.getDireccion());
				dp.setHorarioAtencion(proveedor.getHorarioAtencion());
				dp.setCiudad(proveedor.getCiudad());
				dummies.add(dp);
			}
		}
		
		return dummies;
    }
    
    public List<TipoProveedor> getTiposProveedores(){
    	List<TipoProveedor> tiposProveedor = new ArrayList<TipoProveedor>();
		
		this.tiposProveedor.forEach((k,v) -> tiposProveedor.add(v));
		
		return tiposProveedor;
    }
    
    public void agregarProveedor(Proveedor p) {
    	this.proveedores.put(p.getCodigo(),p);
    }
    public void agregarRecursoDisponible(ProveedorRecurso pr) {
    	Proveedor p = this.proveedores.get(pr.getProveedor().getCodigo());
    	p.addRecursoDisponible(pr);
    }
    public boolean adquirirRecursoDisponible(String codigoProveedor, String codigoRecurso, int cantidad) {
    	Proveedor p = this.proveedores.get(codigoProveedor);
    	
    	try {
    		ProveedorRecurso pr = p.findProveedorRecurso(codigoRecurso);
    		int stockActual = pr.getCantidadDisponible();
    		
    		int stockNuevo = stockActual - cantidad;
    		if (stockNuevo < 0)
    			return false;
    		else
    			pr.setCantidadDisponible(stockNuevo);
    		
    		p.updateRecursoDisponible(pr);
    		return true;
    		
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
    	
    	
    	
    	
    }
    
    
    
    
    /*************************** AUXILIARES ***************************/
    private void iniciarProveedores() {
    	proveedores = new HashMap<String,Proveedor>();
    	
    	readCsvProveedores();
    	readCsvRecursosDeProveedor();
    }
    
    private void iniciarTiposProveedor() {
    	tiposProveedor = new HashMap<String, TipoProveedor>();
    	
    	readCsvTipoProveedores();
    }

    private void iniciarTiposRecursos() {
    	tiposRecursos = new HashMap<String,TipoRecurso>();
    	
    	readCsvTipoRecursos();
    }
    
    private void iniciarRecursos() {
    	recursos = new HashMap<String, Recurso>();
    	
    	readCsvRecursos();
    }
    
    
    private void readCsvTipoProveedores() {
    	CSVReader reader = null;
    	try {
    		ClassLoader classLoader = this.getClass().getClassLoader();
    		FileReader file = new FileReader(classLoader.getResource("tipoProveedores.csv").getFile());
    		reader = new CSVReader(file,';');
    		
    		List<String[]> records = reader.readAll();
    		for (String[] record : records) {
    			TipoProveedor tp = new TipoProveedor();
    			tp.setCodigo(record[0]);
    			tp.setNombre(record[1]);
    			this.tiposProveedor.put(tp.getCodigo(), tp);
    		}
           
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		if (null != reader) {
    			try {
    				reader.close();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		} 
    	}
    }
    
    private void readCsvProveedores() {
    	CSVReader reader = null;
    	try {
    		ClassLoader classLoader = this.getClass().getClassLoader();
    		FileReader file = new FileReader(classLoader.getResource("proveedores.csv").getFile());
    		reader = new CSVReader(file,';');
    		
    		List<String[]> records = reader.readAll();
    		for (String[] record : records) {
    			Proveedor p = new Proveedor();
    			p.setCodigo(record[0]);
    			p.setNombre(record[1]);
    			p.setHorarioAtencion(record[2]);
    			p.setBarrio(record[3]);
    			p.setDireccion(record[4]);
    			
    			TipoProveedor tp = this.tiposProveedor.get(record[5]);
    			p.setTipoProveedor(tp);
    			p.setCiudad(record[6]);
    			
    			this.proveedores.put(p.getCodigo(), p);
    		}
           
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		if (null != reader) {
    			try {
    				reader.close();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		} 
    	}
    }
    
    private void readCsvTipoRecursos() {
    	CSVReader reader = null;
    	try {
    		ClassLoader classLoader = this.getClass().getClassLoader();
    		FileReader file = new FileReader(classLoader.getResource("tipoRecursos.csv").getFile());
    		reader = new CSVReader(file,';');
    		
    		List<String[]> records = reader.readAll();
    		for (String[] record : records) {
    			TipoRecurso tr = new TipoRecurso();
    			tr.setCodigo(record[0]);
    			tr.setNombre(record[1]);
    			tr.setPrecioReferencia(Double.valueOf(record[2]));
    			this.tiposRecursos.put(tr.getCodigo(), tr);
    		}
           
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		if (null != reader) {
    			try {
    				reader.close();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		} 
    	}
    }
    
    private void readCsvRecursos() {
    	CSVReader reader = null;
    	try {
    		ClassLoader classLoader = this.getClass().getClassLoader();
    		FileReader file = new FileReader(classLoader.getResource("recursos.csv").getFile());
    		reader = new CSVReader(file,';');
    		
    		List<String[]> records = reader.readAll();
    		for (String[] record : records) {
    			Recurso r = new Recurso();
    			r.setCodigo(record[0]);
    			r.setMarca(record[1]);
    			
    			TipoRecurso tr = this.tiposRecursos.get(record[2]);
    			r.setTipoRecurso(tr);
    			this.recursos.put(r.getCodigo(), r);
    		}
           
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		if (null != reader) {
    			try {
    				reader.close();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		} 
    	}
    }
    
    private void readCsvRecursosDeProveedor() {
    	CSVReader reader = null;
    	try {
    		ClassLoader classLoader = this.getClass().getClassLoader();
    		FileReader file = new FileReader(classLoader.getResource("recursosDeProveedores.csv").getFile());
    		reader = new CSVReader(file,';');
    		
    		List<String[]> records = reader.readAll();
    		Proveedor p = null;
    		for (String[] record : records) {
    			if (p == null || !p.getCodigo().equals(record[0]))
    				p = this.proveedores.get(record[0]);
    			
    			Recurso r = this.recursos.get(record[1]);
    			
    			ProveedorRecurso pr = new ProveedorRecurso();
    			pr.setProveedor(p);
    			pr.setRecurso(r);
    			pr.setCantidadDisponible(Integer.parseInt(record[2]));
    			pr.setPrecio(Double.parseDouble(record[3]));
    			
    			p.addRecursoDisponible(pr);
    		}
           
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		if (null != reader) {
    			try {
    				reader.close();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		} 
    	}
    }
}
