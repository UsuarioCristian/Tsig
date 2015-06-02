package persistencia;



import javax.ejb.Stateless;

import dominio.Usuario;

@Stateless
public class UsuarioDAO implements IUsuarioDAO {

	@javax.persistence.PersistenceContext(unitName = "TsigWeb")
	private javax.persistence.EntityManager em;

	public boolean guardarUsuario(Usuario usuario) {

		try {
			em.persist(usuario);
			return true;

		} catch (Exception e) {

			e.printStackTrace();

		}

		return false;

	}
	
	
	public void modificarUsuario(Usuario u) {
		
		try {
			em.merge(u);			

		} catch (Exception e) {

			e.printStackTrace();

		}
		
	}


	public boolean existeUsuario(String nombre) {

		Usuario u = em.find(Usuario.class, nombre); // Si no se	encuentra a	la persona se retorna NULL

		if (u != null)
			return true;
		else
			return false;
	}

	

	public Usuario getUsuario(String nombre) {

		
		
		return em.find(Usuario.class, nombre);
	}
	
	public boolean autenticarUsuario(String nombre,String password) {

		Usuario u = em.find(Usuario.class, nombre); // Si no se
																// encuentra a
																// la persona,
																// se retorna
																// NULL

		if (u != null && u.getPassword().equals(password))
			return true;
		else
			return false;
	}



	
}
