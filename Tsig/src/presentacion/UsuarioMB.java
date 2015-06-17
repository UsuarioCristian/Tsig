package presentacion;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import controladores.IPropiedadController;
import controladores.IUsuarioController;




@ManagedBean
@SessionScoped
public class UsuarioMB implements Serializable {
	
	 private static final long serialVersionUID = 1L;
	
	 @EJB
	 private IUsuarioController iuc;
	 
	 @EJB
	 private IPropiedadController ipc;
	 
	 private String nombre;
	 
	 private String mail;
	 
	 private String password;
	 
	 private boolean log ;
	 
	 private String logo="resources/Images/logo.png";
	 
	 private boolean admin = false;
	 
	 private List<String> usuarios;
	 private String usuarioNombre;
	
	public void altaUsuario()
	{	
		System.out.println("alta usuario");
		
		System.out.println(nombre + mail + password);
		try {
			
			if(iuc.existeUsuario(this.nombre)){
				System.out.println("entre if");
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("El usuario ya existe"));
				
			}else{
				iuc.guardarUsuario(this.nombre, this.password, this.mail);
				this.nombre="";
				this.password="";
						
			}
			
				
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		
		
		
	}

	public String Login(){
		
		System.out.println("login");
		  
		try {
			this.setLog(true);
			this.log=true;
			if (iuc.autenticarUsuario(this.nombre, this.password) ) {	
				
				ipc.actualizarZonas();
				if("admin".compareTo(this.nombre)==0){
					this.admin=true;
				}
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", this.nombre);
				FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
				
			}
			else{				
				
				this.log=false;
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Credenciales incorrectas o Perfil deshabilitado"));
				FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
				
				
			}		
			} catch (Exception e) {
				this.log=true;
			e.printStackTrace();
		}
		
	return null;

	}

	public String logOut() {


		try {
			this.log=false;
			FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
			
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
		
		return null;
	}
	
	public void getUsuariosNombre()
	{	
		
		
		System.out.println("entre get ususarios");
		try {
			
			usuarios =iuc.getUsuarios();
			System.out.println("estos son los usuarios.." + usuarios);

		
				
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		
		
		
	}
	
	public void eliminarUsuario()
	{	
		
		
		System.out.println("entre eliminar ususario");
		System.out.println("con ususario.."+ usuarioNombre);

		try {
			
			iuc.eliminarUsuario(usuarioNombre);
			System.out.println("usuarios eliminado ..");

		
				
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		
		
		
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLog() {
		return log;
	}

	public void setLog(boolean log) {
		this.log = log;
	}
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String getUsuarioNombre() {
		return usuarioNombre;
	}

	public void setUsuarioNombre(String usuarioNombre) {
		this.usuarioNombre = usuarioNombre;
	}

	public List<String> getUsuarios(){
		return usuarios;
	}
	
	public void setUsuarios(List<String> usuarios) {
		this.usuarios = usuarios;
	}



}
