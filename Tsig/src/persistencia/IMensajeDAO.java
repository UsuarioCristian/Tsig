package persistencia;

import java.util.List;
import javax.ejb.Local;
import dominio.Mensaje;

@Local
public interface IMensajeDAO {
	
	public boolean altaMensaje(Mensaje mensaje);
	public boolean borrarMensaje(Mensaje mensaje);
	public List<Mensaje> getMensajes(String nombreUsuario);
	public Mensaje findById(int idMensaje);
	public boolean actualizarMensaje(Mensaje mensaje);
	
}
