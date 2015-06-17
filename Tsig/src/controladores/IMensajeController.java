package controladores;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import dominio.Mensaje;

@Local
public interface IMensajeController {
	public boolean altaMensaje(String asunto, String contenido, Date fecha, int idInmueble,String tipoInmueble, String mail);
	public boolean borrarMensaje(int mensajeID);
	public boolean marcarComoLeido(int mensajeID);
	public List<Mensaje> getMensajes(String nombreUsuario);
}
