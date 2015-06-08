package presentacion;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import controladores.IUsuarioController;




@ManagedBean
@SessionScoped
public class UsuarioMB implements Serializable {
	
	 private static final long serialVersionUID = 1L;
	
	 @EJB
	 private IUsuarioController iuc;
	 
	 private String nombre;
	 
	 private String mail;
	 
	 private String password;
	 
	 private boolean log ;
	 
	 private String logo="resources/Images/logo.png";
	 
	
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



}
