package controladores;



import javax.ejb.EJB;
import javax.ejb.Stateless;

import persistencia.IUsuarioDAO;
import dominio.Usuario;

@Stateless
public class UsuarioController implements IUsuarioController {

	@EJB
	private IUsuarioDAO UsuarioDAO;
	
	public boolean guardarUsuario(String nombre, String Password,String mail) {
		try{
				
			
			Usuario u = new Usuario(nombre,Password,mail);
			return UsuarioDAO.guardarUsuario(u);		
		
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
		
	}
	
	public boolean existeUsuario(String nombre) {
		
		try{
			return UsuarioDAO.existeUsuario(nombre);
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	


	




	public Usuario buscarUsuario(String nick) {
		
		try{
		return UsuarioDAO.getUsuario(nick);
		}catch(Exception e){
			e.printStackTrace();
			
		}
		return null;
	}

	





	public void modificarUsuario(Usuario u) {
		try{
	
			UsuarioDAO.modificarUsuario(u);
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
	}

	@Override
	public boolean autenticarUsuario(String nombre,String password) {
		try{
			return UsuarioDAO.autenticarUsuario(nombre,password);
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	








	
}

