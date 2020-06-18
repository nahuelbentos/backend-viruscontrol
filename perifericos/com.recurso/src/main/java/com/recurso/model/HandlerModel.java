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
    
    public List<String> getAllBarrios(){
    	System.out.println("hola, dame bola.");
    	List<String> barrios = new ArrayList<String>();
    	for (Entry<String, Proveedor> it : proveedores.entrySet())
    		if (!barrios.contains(it.getValue().getBarrio()))
    			barrios.add(it.getValue().getBarrio());
    	
    	return barrios;
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
    
    public List<DummyProveedor> getRecursosDisponiblesPorCiudadBarrio(String ciudad, String barrio){
    	List<DummyProveedor> ret = new ArrayList<DummyProveedor>();
    	boolean fullCheck = false;
    	boolean listAll = false;
	    for (Entry<String, Proveedor> it : proveedores.entrySet()) {
	    	fullCheck = (!ciudad.equals("") && !ciudad.equals("0") && !ciudad.equals("-") && !barrio.equals("") && !barrio.equals("0") && !barrio.equals("-"));
	    	listAll = ((ciudad.equals("") || ciudad.equals("0") || ciudad.equals("-")) && (barrio.equals("") || barrio.equals("0") || barrio.equals("-")));
	    	
	    	if ((!fullCheck && (ciudad.equals(it.getValue().getCiudad()) || barrio.equals(it.getValue().getBarrio()))) ||
	    		 (fullCheck && ciudad.equals(it.getValue().getCiudad()) && barrio.equals(it.getValue().getBarrio())) || 
	    		 listAll) {
	    		DummyProveedor item = new DummyProveedor();
	    		item.setNombre(it.getValue().getNombre());
	    		item.setCodigo(it.getValue().getCodigo());
	    		item.setDireccion(it.getValue().getDireccion());
	    		item.setBarrio(it.getValue().getBarrio());
	    		item.setHorarioAtencion(it.getValue().getHorarioAtencion());
	    		item.setCiudad(it.getValue().getCiudad());
	    		
		    	for (ProveedorRecurso pr : it.getValue().getRecursosDisponibles()) {
		    		DummyRecursoDisponible drd = new DummyRecursoDisponible();
		    		
		    		drd.setCantidadDisponible(pr.getCantidadDisponible());
		    		drd.setPrecio(pr.getPrecio());
		    		drd.setRecurso(pr.getRecurso());
		    		if (!item.getRecursosDisponibles().contains(drd))
		    			item.addRecursoDisponible(drd);
				}
		    	ret.add(item);
	    	}
    	}
    	return ret;
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
    
	public int aumentarStockRecurso(String codigoProveedor, String codigoRecurso, int cantidad) {
		Proveedor p = this.proveedores.get(codigoProveedor);
		
		try {
			ProveedorRecurso pr = p.findProveedorRecurso(codigoRecurso);
			int nuevoStock = pr.getCantidadDisponible() + cantidad;
			pr.setCantidadDisponible(nuevoStock);
			
			return nuevoStock;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int consultarStockRecurso(String codigoProveedor, String codigoRecurso) {
		Proveedor p = this.proveedores.get(codigoProveedor);
		
		try {
			ProveedorRecurso pr = p.findProveedorRecurso(codigoRecurso);
			return pr.getCantidadDisponible();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
		
	}
    
    //Added by Naty
    public List<Recurso> getAllRecursos(){
    	List<Recurso> allRecursos = new ArrayList<Recurso>();
    	
    	this.recursos.forEach((k,v) -> allRecursos.add(v));
    	
    	return allRecursos;
    }
    
    //Added by Naty
    public List<TipoRecurso> getAllTiposDeRecursos(){
    	List<TipoRecurso> allTiposRecursos = new ArrayList<TipoRecurso>();
    	
    	this.tiposRecursos.forEach((k,v) -> allTiposRecursos.add(v));
    	
    	return allTiposRecursos;
    }
    
        
    /*************************** AUXILIARES ***************************/
    private void iniciarProveedores() {
    	proveedores = new HashMap<String,Proveedor>();
    	//holis
    	Proveedor p=new Proveedor();
    	p.setBarrio("Cerrito");
    	p.setCiudad("Montevideo");
    	p.setCodigo("1");
    	p.setDireccion("Jose Batlle y Ordoniez 1154");
    	p.setHorarioAtencion("lunes a viernes de 08:00 a 18:00");
    	p.setNombre("Juan Antonio Propio");
    	p.setTipoProveedor(tiposProveedor.get("COMERCIO"));
    	
    	ProveedorRecurso pr=new ProveedorRecurso();
    	pr.setRecurso(recursos.get("1"));
    	pr.setCantidadDisponible(22);
    	pr.setPrecio(400);
    	pr.setProveedor(p);
    	
    	ProveedorRecurso pr2=new ProveedorRecurso();
    	pr2.setRecurso(recursos.get("2"));
    	pr2.setCantidadDisponible(35);
    	pr2.setPrecio(400);
    	pr2.setProveedor(p);
    	
    	p.addRecursoDisponible(pr);
    	p.addRecursoDisponible(pr2);
    	
    	Proveedor p2=new Proveedor();
    	p2.setBarrio("Centro");
    	p2.setCiudad("Montevideo");
    	p2.setCodigo("2");
    	p2.setDireccion("18 y andes");
    	p2.setHorarioAtencion("24 hs");
    	p2.setNombre("Farmashop");
    	p2.setTipoProveedor(tiposProveedor.get("FARMACIA"));
    	
    	ProveedorRecurso pr3=new ProveedorRecurso();
    	pr3.setRecurso(recursos.get("3"));
    	pr3.setCantidadDisponible(35);
    	pr3.setPrecio(400);
    	pr3.setProveedor(p2);
    	
    	ProveedorRecurso pr4=new ProveedorRecurso();
    	pr4.setRecurso(recursos.get("4"));
    	pr4.setCantidadDisponible(22);
    	pr4.setPrecio(30);
    	pr4.setProveedor(p2);
    	
    	p2.addRecursoDisponible(pr4);
    	p2.addRecursoDisponible(pr3);
    	
    	Proveedor p3=new Proveedor();
    	p3.setBarrio("Centro");
    	p3.setCiudad("Montevideo");
    	p3.setCodigo("3");
    	p3.setDireccion("mercedes y convencion");
    	p3.setHorarioAtencion("10 a 18");
    	p3.setNombre("sanroque");
    	p3.setTipoProveedor(tiposProveedor.get("FARMACIA"));
    	
    	ProveedorRecurso pr5=new ProveedorRecurso();
    	pr5.setRecurso(recursos.get("4"));
    	pr5.setCantidadDisponible(60);
    	pr5.setPrecio(100);
    	pr5.setProveedor(p3);
    	
    	ProveedorRecurso pr6=new ProveedorRecurso();
    	pr6.setRecurso(recursos.get("5"));
    	pr6.setCantidadDisponible(55);
    	pr6.setPrecio(400);
    	pr6.setProveedor(p3);
    	
    	p3.addRecursoDisponible(pr5);
    	p3.addRecursoDisponible(pr6);
    	
    	
    	Proveedor p4=new Proveedor();
    	p4.setBarrio("pocitos");
    	p4.setCiudad("Montevideo");
    	p4.setCodigo("4");
    	p4.setDireccion("21 de setiembre y av brasil");
    	p4.setHorarioAtencion("24 hs");
    	p4.setNombre("PocitosFarm");
    	p4.setTipoProveedor(tiposProveedor.get("FARMACIA"));
    	
    	ProveedorRecurso pr7=new ProveedorRecurso();
    	pr7.setRecurso(recursos.get("6"));
    	pr7.setCantidadDisponible(105);
    	pr7.setPrecio(800);
    	pr7.setProveedor(p4);
    	
    	ProveedorRecurso pr8=new ProveedorRecurso();
    	pr8.setRecurso(recursos.get("7"));
    	pr8.setCantidadDisponible(105);
    	pr8.setPrecio(10);
    	pr8.setProveedor(p4);
    	
    	p4.addRecursoDisponible(pr8);
    	p4.addRecursoDisponible(pr7);
    	
    	Proveedor p5=new Proveedor();
    	p5.setBarrio("Aguada");
    	p5.setCiudad("Montevideo");
    	p5.setCodigo("5");
    	p5.setDireccion("av siempreviva 742");
    	p5.setHorarioAtencion("24 hs");
    	p5.setNombre("Apu");
    	p5.setTipoProveedor(tiposProveedor.get("COMERCIO"));
    	
    	ProveedorRecurso pr9=new ProveedorRecurso();
    	pr9.setRecurso(recursos.get("8"));
    	pr9.setCantidadDisponible(245);
    	pr9.setPrecio(67);
    	pr9.setProveedor(p5);
    	
    	p5.addRecursoDisponible(pr9);
    	
    	
    	proveedores.put(p.getCodigo(), p);
    	proveedores.put(p2.getCodigo(), p2);
    	proveedores.put(p3.getCodigo(), p3);
    	proveedores.put(p4.getCodigo(), p4);
    	proveedores.put(p5.getCodigo(), p5);
    	
    	//readCsvProveedores();
    	//readCsvRecursosDeProveedor();

    }
    
    private void iniciarTiposProveedor() {
    	tiposProveedor = new HashMap<String, TipoProveedor>();
    	
    	TipoProveedor tp= new TipoProveedor();
    	tp.setCodigo("FARMACIA");
    	tp.setNombre("Farmacia");
    	
    	TipoProveedor tp2= new TipoProveedor();
    	tp2.setCodigo("DROGUERIA");
    	tp2.setNombre("Drogueria");
    	
    	
    	TipoProveedor tp3= new TipoProveedor();
    	tp3.setCodigo("LABORATORIO");
    	tp3.setNombre("Laboratorio");
    	
    	
    	TipoProveedor tp4= new TipoProveedor();
    	tp4.setCodigo("COMERCIO");
    	tp4.setNombre("Comercio");
    	
    	TipoProveedor tp5= new TipoProveedor();
    	tp5.setCodigo("PARTICULAR");
    	tp5.setNombre("Persona particular");
    	
    	TipoProveedor tp6= new TipoProveedor();
    	tp6.setCodigo("OSC");
    	tp6.setNombre("Organizacion de la sociedad civil");
    	
    	
    	tiposProveedor.put(tp.getCodigo(), tp);
    	tiposProveedor.put(tp2.getCodigo(), tp2);
    	tiposProveedor.put(tp3.getCodigo(), tp3);
    	tiposProveedor.put(tp4.getCodigo(), tp4);
    	tiposProveedor.put(tp5.getCodigo(), tp5);
    	tiposProveedor.put(tp6.getCodigo(), tp6);
    	
    	//readCsvTipoProveedores();

    }

    private void iniciarTiposRecursos() {
tiposRecursos = new HashMap<String,TipoRecurso>();
    	
    	TipoRecurso tr=new TipoRecurso();
    	tr.setCodigo("1");
    	tr.setNombre("Desinfectante");
    	tr.setPrecioReferencia(250);
    	
    	TipoRecurso tr2=new TipoRecurso();
    	tr2.setCodigo("2");
    	tr2.setNombre("Alcohol");
    	tr2.setPrecioReferencia(450);
    	
    	TipoRecurso tr3=new TipoRecurso();
    	tr3.setCodigo("3");
    	tr3.setNombre("Repelente");
    	tr3.setPrecioReferencia(850);
    	
    	TipoRecurso tr4=new TipoRecurso();
    	tr4.setCodigo("4");
    	tr4.setNombre("Curitas");
    	tr4.setPrecioReferencia(10);
    	
    	TipoRecurso tr5=new TipoRecurso();
    	tr5.setCodigo("5");
    	tr5.setNombre("Analgesico");
    	tr5.setPrecioReferencia(50);
    	
    	
    	tiposRecursos.put(tr.getCodigo(), tr);
    	tiposRecursos.put(tr2.getCodigo(), tr2);
    	tiposRecursos.put(tr3.getCodigo(), tr3);
    	tiposRecursos.put(tr4.getCodigo(), tr4);
    	tiposRecursos.put(tr5.getCodigo(), tr5);
    	//readCsvTipoRecursos();

    }
    
    private void iniciarRecursos() {
    	recursos = new HashMap<String, Recurso>();
    	
    	Recurso r=new Recurso();
    	r.setCodigo("1");
    	r.setMarca("alcohol en gel");
    	r.setTipoRecurso(tiposRecursos.get("2"));
    	
    	Recurso r2=new Recurso();
    	r2.setCodigo("2");
    	r2.setMarca("alcohol isopropilico ");
    	r2.setTipoRecurso(tiposRecursos.get("2"));
    	
    	Recurso r3=new Recurso();
    	r3.setCodigo("3");
    	r3.setMarca("desinfectante lisofornm");
    	r3.setTipoRecurso(tiposRecursos.get("1"));
    	
    	Recurso r4=new Recurso();
    	r4.setCodigo("4");
    	r4.setMarca("Lavandina");
    	r4.setTipoRecurso(tiposRecursos.get("1"));
    	
    	Recurso r5=new Recurso();
    	r5.setCodigo("5");
    	r5.setMarca("Mr musculo");
    	r5.setTipoRecurso(tiposRecursos.get("1"));
    	
    	Recurso r6=new Recurso();
    	r6.setCodigo("6");
    	r6.setMarca("agua hane");
    	r6.setTipoRecurso(tiposRecursos.get("1"));
    	
    	Recurso r7=new Recurso();
    	r7.setCodigo("7");
    	r7.setMarca("repelente off");
    	r7.setTipoRecurso(tiposRecursos.get("3"));
    	
    	Recurso r8=new Recurso();
    	r8.setCodigo("8");
    	r8.setMarca("fuera mosquitos 3000");
    	r8.setTipoRecurso(tiposRecursos.get("3"));
    	
    	Recurso r9=new Recurso();
    	r9.setCodigo("9");
    	r9.setMarca("Livopen");
    	r9.setTipoRecurso(tiposRecursos.get("3"));
    	
    	Recurso r10=new Recurso();
    	r10.setCodigo("10");
    	r10.setMarca("Curaflex");
    	r10.setTipoRecurso(tiposRecursos.get("4"));
    
    	Recurso r11=new Recurso();
    	r11.setCodigo("11");
    	r11.setMarca("Sanasana");
    	r11.setTipoRecurso(tiposRecursos.get("4"));
    	
    	Recurso r12=new Recurso();
    	r12.setCodigo("12");
    	r12.setMarca("Aspirina");
    	r12.setTipoRecurso(tiposRecursos.get("5"));
    	
    	Recurso r13=new Recurso();
    	r13.setCodigo("13");
    	r13.setMarca("Ibuprofeno");
    	r13.setTipoRecurso(tiposRecursos.get("5"));
    	
    	Recurso r14=new Recurso();
    	r14.setCodigo("14");
    	r14.setMarca("Paracetamol");
    	r14.setTipoRecurso(tiposRecursos.get("5"));
    	
    	
    	recursos.put(r.getCodigo(), r);
    	recursos.put(r2.getCodigo(), r2);
    	recursos.put(r3.getCodigo(), r3);
    	recursos.put(r4.getCodigo(), r4);
    	recursos.put(r5.getCodigo(), r5);
    	recursos.put(r5.getCodigo(), r5);
    	recursos.put(r6.getCodigo(), r6);
    	recursos.put(r7.getCodigo(), r7);
    	recursos.put(r8.getCodigo(), r8);
    	recursos.put(r9.getCodigo(), r9);
    	recursos.put(r10.getCodigo(), r10);
    	recursos.put(r11.getCodigo(), r11);
    	recursos.put(r12.getCodigo(), r12);
    	recursos.put(r13.getCodigo(), r13);
    	recursos.put(r14.getCodigo(), r14);
    	//readCsvRecursos();

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
