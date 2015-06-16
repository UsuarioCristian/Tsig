package presentacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import controladores.IMensajeController;
import dominio.Mensaje;

@ManagedBean
@SessionScoped
public class MensajeMB implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<Mensaje> mensajes = new ArrayList();
	private Mensaje msg ;
	@EJB
	IMensajeController imc;
	
	private String asunto;
	private String contenido;
	private Date fecha;
	private String usuario;
	private String mail;
	
	@PostConstruct
	public void loadMensajes(){
		
		String usuario =(String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		this.mensajes =imc.getMensajes(usuario);
		
		
	}
	
	public void altaMensaje(){
		
		
		
		imc.altaMensaje(asunto, contenido, new Date(),"admin",mail);
		
	}
	
	
	public String getAsunto() {
		return asunto;
	}


	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public List<Mensaje> getMensajes() {
		return mensajes;
	}
	public void setMensajes(List<Mensaje> mensajes) {
		this.mensajes = mensajes;
	}
	public Mensaje getMsg() {
		return msg;
	}
	public void setMsg(Mensaje msg) {
		this.msg = msg;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
}
