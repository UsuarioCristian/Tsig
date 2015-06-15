package controladores;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import persistencia.IMensajeDAO;
import persistencia.IUsuarioDAO;
import dominio.Mensaje;
import dominio.Usuario;

@Stateless
public class MensajeController implements IMensajeController {

	@EJB
	IMensajeDAO mensajeDAO;
	
	@EJB
	IUsuarioDAO usuarioDAO;
	
	@Override
	public boolean altaMensaje(String asunto, String contenido, Date fecha, String nombreUsuario) {
		
		Mensaje mensaje = new Mensaje();
		mensaje.setAsunto(asunto);
		mensaje.setContenido(contenido);
		mensaje.setVisto(false);
		mensaje.setFecha(fecha);
		
		Usuario usuario = usuarioDAO.getUsuario(nombreUsuario);
		mensaje.setDestinatario(usuario);
		
		Set<Mensaje> mensajesUsuario = usuario.getMensajes();
		mensajesUsuario.add(mensaje);
		
		try {
			usuarioDAO.modificarUsuario(usuario);
			// Sera suficiente? o habra que crear el mensaje por el MensajeDAO?
			return true;
		} catch (Exception e) {
			return false;
		}
		
		
		
	}

	@Override
	public boolean borrarMensaje(int mensajeID) {
		
		try {
			// Se utilizan 2 funciones de mensajeDAO: mensajeDAO.borrarMensaje() y mensajeDAO.findByID()
			mensajeDAO.borrarMensaje(mensajeDAO.findById(mensajeID));
			
			return true;
		} catch (Exception e) {
			System.out.println("Error borrar mensaje en (MensajeController)");
			return false;
		}
		
		
	}

	@Override
	public boolean marcarComoLeido(int mensajeID) {
		try {
			Mensaje mensaje = mensajeDAO.findById(mensajeID);
			mensaje.setVisto(true);
			mensajeDAO.actualizarMensaje(mensaje);			
			return true;
		} catch (Exception e) {
			System.out.println("Error en marcarComoLeido en (MensajeController)");
			return false;
		}
		
	}

	@Override
	public List<Mensaje> getMensajes(String nombreUsuario) {
		try {
			return mensajeDAO.getMensajes(nombreUsuario);
		} catch (Exception e) {
			System.out.println("Error en getMensajes en (MensajeController)");
			return null;
		}
		
	}

}
