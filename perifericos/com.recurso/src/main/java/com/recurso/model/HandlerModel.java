package com.recurso.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;

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
    	iniciarProveedores();
    	iniciarTiposRecursos();
    	inicarRecursos();
    	
    }
    
    public List<ProveedorRecurso> getRecursosDisponiblesPorProveedor(String codigoProveedor){
    	
    	Proveedor p = proveedores.get(codigoProveedor);
    	return p.getRecursosDisponibles();
    	
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
    	
    	Proveedor p = new Proveedor();
    	p.setTipoProveedor(tiposProveedor.get("FARMACIA"));
    	p.setCodigo("FDSM");
    	p.setNombre("Farmashop Devoto San Martin");
    	p.setBarrio("Brazo Oriental");
    	p.setRecursosDisponibles(new ArrayList<ProveedorRecurso>());
    	proveedores.put(p.getCodigo(), p);
    	
    	Proveedor p1 = new Proveedor();
    	p1.setTipoProveedor(tiposProveedor.get("COMERCIO"));
    	p1.setCodigo("DRWW");
    	p1.setNombre("Drogueria Walter White");
    	p1.setBarrio("Brazo Oriental");
    	p1.setRecursosDisponibles(new ArrayList<ProveedorRecurso>());
    	proveedores.put(p1.getCodigo(), p1);
    	
    	Proveedor p2 = new Proveedor();
    	p2.setTipoProveedor(tiposProveedor.get("ORGCIVIL"));
    	p2.setCodigo("CELA");
    	p2.setNombre("Comunidad El abrazo");
    	p2.setBarrio("Brazo Oriental");
    	p2.setRecursosDisponibles(new ArrayList<ProveedorRecurso>());
    	proveedores.put(p2.getCodigo(), p2);
    	
    	setRecursosDisponibles();
    }
    
    private void iniciarTiposProveedor() {
    	tiposProveedor = new HashMap<String,TipoProveedor>();
    	
    	TipoProveedor tp = new TipoProveedor();
    	tp.setCodigo("COMERCIO");
    	tp.setNombre("Comercio");
    	tiposProveedor.put(tp.getCodigo(), tp);
    	
    	TipoProveedor tp1 = new TipoProveedor();
    	tp.setCodigo("FARMACIA");
    	tp.setNombre("Farmacia");
    	tiposProveedor.put(tp1.getCodigo(), tp1);
    	
    	TipoProveedor tp2 = new TipoProveedor();
    	tp.setCodigo("ORGCIVIL");
    	tp.setNombre("Organizaciones de la sociedad civil");
    	tiposProveedor.put(tp2.getCodigo(), tp2);
    	
    	
    	
    }

    private void iniciarTiposRecursos() {
    	tiposRecursos = new HashMap<String,TipoRecurso>();
    	
    	TipoRecurso tr = new TipoRecurso();
    	tr.setCodigo("ALGEL");
    	tr.setNombre("Alcohol en gel 250gr");
    	tr.setPrecioReferencia(199.00);
    	tiposRecursos.put(tr.getCodigo(), tr);
    	
    	TipoRecurso tr1 = new TipoRecurso();
    	tr1.setCodigo("GASAS");
    	tr1.setNombre("Gasas x20");
    	tr1.setPrecioReferencia(100.00);
    	tiposRecursos.put(tr1.getCodigo(), tr1);
    }
    
    private void inicarRecursos() {
    	recursos = new HashMap<String, Recurso>();
    	
    	Recurso r = new Recurso();
    	r.setCodigo("ALGELAGA");
    	r.setMarca("Alcohol en gel - Laboratorio Agapito");
    	r.setTipoRecurso(tiposRecursos.get("ALGEL"));
    	recursos.put(r.getCodigo(),r);
    	
    	Recurso r1 = new Recurso();
    	r1.setCodigo("GASASAGA");
    	r1.setMarca("Gasas - Laboratorio Agapito");
    	r1.setTipoRecurso(tiposRecursos.get("GASAS"));
    	recursos.put(r1.getCodigo(),r1);
    	
    	Recurso r2 = new Recurso();
    	r2.setCodigo("ALGELRICK");
    	r2.setMarca("Alcohol en gel - Laboratorio No lo se Rick");
    	r2.setTipoRecurso(tiposRecursos.get("ALGEL"));
    	recursos.put(r2.getCodigo(),r2);
    }
    
    
    private void setRecursosDisponibles() {
    	
    	
    	Proveedor p = this.proveedores.get("DRWW");
    	Recurso r = this.recursos.get("ALGELAGA");
    	
    	ProveedorRecurso pr = new ProveedorRecurso();
    	pr.setProveedor(p);
    	pr.setRecurso(r);
    	pr.setCantidadDisponible(2000);
    	pr.setPrecio(160.00);
    	
    	Recurso r1 = this.recursos.get("GASASAGA");
    	ProveedorRecurso pr1 = new ProveedorRecurso();
    	pr1.setProveedor(p);
    	pr1.setRecurso(r1);
    	pr1.setCantidadDisponible(2000);
    	pr1.setPrecio(160.00);
    	
    	p.addRecursoDisponible(pr);
    	p.addRecursoDisponible(pr1);
    }
    
}
