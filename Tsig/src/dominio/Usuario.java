package dominio;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L; // Mapping JPA

	
	@Id
	@Column(name = "nombre", nullable = false)
	private String nombre;

	@Column(name = "mail", nullable = false)
	private String mail;

	@Column(name = "password", nullable = false)
	// @NotNull
	private String password;
	
	@OneToMany(mappedBy="encargado", cascade=CascadeType.ALL)  
	private Set<Casa> casas;
	
	@OneToMany(mappedBy = "destinatario", cascade = CascadeType.ALL)
	private Set<Mensaje> mensajes;

	public Usuario() {
		
		this.mensajes = new LinkedHashSet<Mensaje>();
		
	}

	public Usuario(String nombre, String password, String mail) {
		this.password = password;
		this.mail = mail;
		this.nombre = nombre;
	}


	public Usuario(Usuario p) {
		this.password = p.getPassword();
		this.mail = p.getMail();
		this.nombre = p.getNombre();
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

	public Set<Casa> getCasas() {
		return casas;
	}

	public void setCasas(Set<Casa> casas) {
		this.casas = casas;
	}
	public void addCasa(Casa c){
		
		this.casas.add(c);
		
	}
	public void removeCasa(Casa c){
		
		this.casas.remove(c);
		
	}

	public Set<Mensaje> getMensajes() {
		return mensajes;
	}

	public void setMensajes(Set<Mensaje> mensajes) {
		this.mensajes = mensajes;
	}
	
	public void removeMensaje(Mensaje m) {
		if (this.mensajes != null) {
			this.mensajes.remove(m);
		}
	}

}