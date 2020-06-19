package uy.viruscontrol.model.ldap;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import uy.viruscontrol.model.entities.Administrador;
import uy.viruscontrol.model.entities.Gerente;
import uy.viruscontrol.model.entities.Usuario;

public class LDAPConexion {

	private static LDAPConexion instancia = null;
	
	//private static final String servidor	= "ldap://localhost:389";
	private static final String servidor	= "ldap://179.27.96.131:389";
	
	private static final String usuario 	= "admin";
    private static final String clave 		= "grupo14tecno";
    
    private static final String dn			= "dc=viruscontrol,dc=uy";
	//private static final String dn			= "dc=viruscontrol,dc=local";
	private static final String dnUser			= "cn=" + usuario + "," + dn;
	
    private Hashtable<String, String> env;
    //private DirContext dc;
    
    
	private LDAPConexion() {
    	env = new Hashtable<String, String>();
    	env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
    	env.put(Context.PROVIDER_URL, servidor);
    	env.put(Context.SECURITY_PRINCIPAL, dnUser);
    	env.put(Context.SECURITY_CREDENTIALS, clave);
    }

    public static LDAPConexion getInstancia() {
    	if (instancia == null) {
    		instancia = new LDAPConexion();
    	}
    	return instancia;
    }
    
    public boolean insertarUsuario(Usuario user) {
    	try {
    		DirContext dctx = new InitialDirContext(this.env);
	    	
	    	Attributes matchAttrs = new BasicAttributes(true);
	    	
			matchAttrs.put(new BasicAttribute("uid", user.getUsername()));
			matchAttrs.put(new BasicAttribute("cn", user.getNombre()));
			//matchAttrs.put(new BasicAttribute("street", user.getDireccion()));
			matchAttrs.put(new BasicAttribute("sn", user.getApellido()));
			matchAttrs.put(new BasicAttribute("userPassword",user.getPassword()));
			//matchAttrs.put(new BasicAttribute("documentIdentifier",user.getDocumento()));
			
			
			matchAttrs.put(new BasicAttribute("objectclass", "top"));
			matchAttrs.put(new BasicAttribute("objectclass", "person"));
			matchAttrs.put(new BasicAttribute("objectclass", "organizationalPerson"));
			matchAttrs.put(new BasicAttribute("objectclass", "inetOrgPerson"));
			
			String dc = "uid=" + user.getUsername() + ",ou=%s," + dn;
			//dc = String.format(dc, "usuarios");
			/** Para produccion va esto **/
			if (user instanceof Administrador)
				dc = String.format(dc, "administradores");
			if (user instanceof Gerente)
				dc = String.format(dc, "DirContext dctxgerentes");
			
			
			InitialDirContext iniDirContext = (InitialDirContext) dctx;
			
			iniDirContext.bind(dc, dctx, matchAttrs);
			System.out.println("Se insertó correctamente el usuario " + user.getUsername() + " a openLDAP.");
			return true;
		} catch (NamingException e) {
			e.printStackTrace();
			return false;
		}    	
    }
    
	public boolean autenticarUsuario(Usuario user) {
		
		String dc = "uid=" + user.getUsername() + ",ou=%s," + dn;
		if (user instanceof Administrador)
			dc = String.format(dc, "administradores");
		if (user instanceof Gerente)
			dc = String.format(dc, "gerentes");
		
		Hashtable<String, String> localenv = new Hashtable<String, String>();
		localenv.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		localenv.put(Context.PROVIDER_URL, servidor);
		localenv.put(Context.SECURITY_PRINCIPAL, dc);
		localenv.put(Context.SECURITY_CREDENTIALS, user.getPassword());
		
		DirContext localUserCtx = null;
		try {
			localUserCtx = new InitialDirContext(this.env);
        } catch (NamingException ex) {
            System.out.println("Error Autenticando mediante LDAP, Error causado por : " + ex.toString());
        }
		
		return localUserCtx != null;
		
	}
    
}
