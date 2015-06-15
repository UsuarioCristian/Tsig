package dominio;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@NamedQueries({
	@NamedQuery(name= "Mensaje.getMensajes",
				query= "SELECT m FROM Mensaje m WHERE m.destinatario.nombre = :nombre"),
	@NamedQuery(name= "Mensaje.findById",
				query= "SELECT m FROM Mensaje m WHERE m.id = :idMensaje"),
})

@Entity
public class Mensaje implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private static final long serialVersionUID = 1L;
	
	private String asunto;
	private String contenido;
	private Date fecha;
	private boolean visto;
		
	@ManyToOne(cascade = CascadeType.REFRESH)
	private Usuario destinatario;

	public Mensaje() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Usuario getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Usuario destinatario) {
		this.destinatario = destinatario;
	}

	public boolean isVisto() {
		return visto;
	}

	public void setVisto(boolean visto) {
		this.visto = visto;
	}
		
	@Override
	public String toString() {		
		return "Mensaje[" + this.id + "]"+" Asunto: "+this.asunto;
	}

}
