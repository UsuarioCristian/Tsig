package controladores;

import java.util.List;

import javax.ejb.Local;

import dominio.Usuario;

@Local
public interface IUsuarioController {
	public boolean guardarUsuario(String nombre, String Password, String mail);
	
	public boolean existeUsuario(String nombre);
	
	public boolean autenticarUsuario(String nombre,String password);
	
	public Usuario buscarUsuario(String nombre);
	
	public void modificarUsuario(Usuario u);

	public List<String> getUsuarios();

	public void eliminarUsuario(String usuarioNombre);
	
}

